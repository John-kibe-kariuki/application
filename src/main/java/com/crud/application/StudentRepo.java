package com.crud.application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {



//    static List<Student> findAll() {
//
//    }
//
//    static Optional<Student> findById(Long id) {
//    }

    Optional<Object> findByStudentName(String name);
//
//
//
//    Student get();
//
//    Student save(Student studentData);
}
