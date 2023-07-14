package com.example.bdpractise.functional_Interface;

import com.example.bdpractise.dao.StudentJDBCTemplateImpl;
import com.example.bdpractise.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.*;

@RequiredArgsConstructor
public class FunctionalInterface implements CommandLineRunner {
    private final StudentJDBCTemplateImpl studentTemplate;
    private final StudentRepository studentRepository;
    @Override
    public void run(String... args) throws Exception {

        var value = studentRepository.findAvgMarkOfStudentByName("Friedman");
        System.out.println(value);
        var list = studentRepository.findStudentsByLastnameOrderByName("Muller");
        /*studentTemplate.saveNewStudent(Student.builder()
                .name("Artem")
                .lastname("Friedman")
                .build());*/
        String hello = "Hello";
        Function<String, Integer> integerFunctionalInterface = String::length;
        var a = integerFunctionalInterface.apply(hello);
        var ab = new int[]{1, 2, 5, 3, 6};
        Arrays.stream(ab)
                .boxed()
                .sorted(Integer::compareTo)
                        .toList()
                .forEach(System.out::println);
        Function<String, Integer> biFunction = String::length;
        System.out.println(biFunction.apply("artem"));
    }
}
