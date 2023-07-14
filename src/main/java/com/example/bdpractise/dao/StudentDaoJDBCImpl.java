package com.example.bdpractise.dao;

import com.example.bdpractise.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentDaoJDBCImpl implements StudentDao {
    private final DataSource dataSource;
    @Override
    public Student findStudentById(Long id) {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select * from exerciseTest.students where id = ?");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                var student = CreateStudent(resultSet);
                return student;
            }
        }
        catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        finally {
            try {
                closeConnection(resultSet, preparedStatement, connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    @Override
    public List<Student> findStudentsByName(String name) {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        List<Student> table = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select exerciseTest.students.id, exerciseTest.students.first_name, exerciseTest.students.last_name from exerciseTest.students where exerciseTest.students.first_name = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                var student = CreateStudent(resultSet);
                table.add(student);
            }
            return table;
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage() + " " + ex.toString());
        } finally {
            try {
              closeConnection(resultSet, preparedStatement, connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public Student saveNewStudent(Student student) {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Statement statement;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("insert into students(first_name, last_name) VALUES (?,?)");
            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2, student.getLastname());
            preparedStatement.execute();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()){
                Long getId = resultSet.getLong(1);
                return this.findStudentById(getId);
            }
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage() + " " + ex.toString());
        } finally {
            try {
                closeConnection(resultSet, preparedStatement, connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public Student updateStudent(Student student) {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("update students set last_name = ?, first_name =? where id = ?");
            preparedStatement.setString(2,student.getName());
            preparedStatement.setString(1, student.getLastname());
            preparedStatement.setLong(3, student.getId());
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage() + " " + ex.toString());
        } finally {
            try {
                closeConnection(resultSet, preparedStatement, connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return this.findStudentById(student.getId());
    }

    @Override
    public void deleteStudent(Student student) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("delete from students where id = ?");
            preparedStatement.setLong(1, student.getId());
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage() + " " + ex.toString());
        } finally {
            try {
                closeConnection(null, preparedStatement, connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public List<Student> findListPaging(Long limit, Long offset) {
        return null;
    }

    @Override
    public List<Student> findAllStudent(Pageable pageable) {
        return null;
    }

    @Override
    public List<Student> findAllSortByFirstName(Pageable pageable) {
        return null;
    }

    private Student CreateStudent(ResultSet resultSet) throws SQLException {
        return Student.builder()
                .id(Long.parseLong(resultSet.getString("id")))
                .name(resultSet.getString("first_name"))
                .lastname(resultSet.getString("last_name"))
                .build();

    }
    private void closeConnection(ResultSet resultSet, PreparedStatement statement, Connection connection) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

}
