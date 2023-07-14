package com.example.bdpractise.jdbcMapper;

import com.example.bdpractise.entity.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapperC implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Student.builder()
                .id(rs.getLong("id"))
                .lastname(rs.getString("last_name"))
                .name(rs.getString("first_name"))
                .build();
    }
}
