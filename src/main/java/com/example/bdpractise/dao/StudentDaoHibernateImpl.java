package com.example.bdpractise.dao;

import com.example.bdpractise.entity.Student;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
@RequiredArgsConstructor
@Component
public class StudentDaoHibernateImpl implements StudentDao{

    private final EntityManagerFactory entityManagerFactory;

    private EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }
    @Override
    public Student findStudentById(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Student> findStudentsByName(String name) {
        return null;
    }

    @Override
    public Student saveNewStudent(Student student) {
        return null;
    }

    @Override
    public Student updateStudent(Student student) {
        return null;
    }

    @Override
    public void deleteStudent(Student student) {

    }

    @Override
    public List<Student> findAll() {
        EntityManager entityManager = null;
        EntityTransaction txn = null;
        try {
            entityManager = getEntityManager();
            txn = entityManager.getTransaction();
            txn.begin();
            TypedQuery<Student> query = entityManager.createQuery("select st from Student st", Student.class);
            txn.commit();
            return query.getResultList();
        }
        catch (Exception ex)
        {
           if(txn != null && txn.isActive())
               txn.rollback();
        }
        finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public List<Student> findListPaging(Long limit, Long offset) {
        var em = getEntityManager();
        try {
            Query query = em.createNativeQuery( "select * from students limit ? offset ?",Student.class);
            query.setParameter(1, limit);
            query.setParameter(2,offset);
            return query.getResultList();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage() + "  " + ex.toString());
        }
        finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<Student> findAllStudent(Pageable pageable) {
        var em = getEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery( "select st from Student st",Student.class);
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));
            query.setMaxResults(pageable.getPageSize());
            return query.getResultList();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage() + "  " + ex.toString());
        }
        finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<Student> findAllSortByFirstName(Pageable pageable) {
        var em = getEntityManager();
        try{
            String hql = "SELECT st from Student st order by st.name " + pageable.getSort()
                    .getOrderFor("name")
                    .getDirection()
                    .name();
            TypedQuery<Student> query = em.createQuery(hql,Student.class);
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));
            query.setMaxResults(pageable.getPageSize());
            return query.getResultList();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage()+ "   " + ex.toString());
        }
        finally {
            em.close();
        }
        return null;
    }
}
