package com.example.bdpractise.repository;

import com.example.bdpractise.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
     @Query(value = "select avg(m.value) from students inner join marks m on m.student_id = students.id where last_name = ? group by last_name",nativeQuery = true)
     double findAvgMarkOfStudentByName(String surname);

     List<Student> findStudentsByLastnameOrderByName(String LastName);

     @Query("select b from Student b where b.lastname=:lastname")
     Page<Student> findAllStudentsByLastName(String lastname, Pageable pageable);

     @Query(value = "select first_name from students where last_name=:lastname", nativeQuery = true)
     List<String> findAllByLastnameNatQue(String lastname, Pageable pageable);
}
