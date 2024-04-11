package com.crud.application;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.LongFunction;


@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    public EntityResponse addNewStudent(Student student) {
        EntityResponse response = new EntityResponse<>();

        try {
            if (studentRepo.findByStudentName(student.getStudentName()).isPresent()
            ) {
                response.setEntity(null);
                response.setMessage("Student already present");
                response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            } else {
                response.setEntity(student);
                response.setMessage("Student added successfully");
                response.setStatusCode(HttpStatus.OK.value());
            }

        } catch (Exception e) {
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("An error occurred" + e.getMessage());
        }
        return response;
    }

    public EntityResponse getSudents() {
        EntityResponse response = new EntityResponse<>();
        List<Student> student = studentRepo.findAll();
        try {
            if (!student.isEmpty()) {
                response.setEntity(student);
                response.setMessage("Students are found");
                response.setStatusCode(HttpStatus.OK.value());

            } else {
                response.setEntity(null);
                response.setMessage("list not found");
                response.setStatusCode(HttpStatus.NO_CONTENT.value());
            }
        } catch (Exception e) {
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("an error occured" + e.getMessage());
        }
        return response;
    }

    public EntityResponse UpdateStudent(Student request) {
        EntityResponse response = new EntityResponse<>();
        try {
            Optional<Student> student = studentRepo.findById(request.getId());
            if (student.isPresent()) {
                Student studentData = student.get();
                if (request.getStudentName() != null) {
                    studentData.setStudentName(request.getStudentName());
                }
                if (request.getRegistrationNumber() != null) {
                    studentData.setRegistrationNumber(request.getRegistrationNumber());
                }
                if (request.getStudentEmailAddress() != null) {
                    studentData.setRegistrationNumber(request.getRegistrationNumber());
                }
                Student updateStude = studentRepo.save(studentData);
                response.setEntity(student);
                response.setMessage("student is updated successfully");
                response.setStatusCode(HttpStatus.ACCEPTED.value());
            } else {
                response.setEntity(null);
                response.setMessage("The student is not available");
                response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            }
        } catch (Exception e) {
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("an error occurred" + e.getMessage());
        }
        return response;
    }


    public EntityResponse deleteStudent(Long id) {
        EntityResponse response = new EntityResponse<>();
        try {

            Optional<Student> student = studentRepo.findById(id);
            if (student.isPresent()) {
                studentRepo.deleteById(id);
                response.setEntity(student);
                response.setMessage("deleted successfully");
                response.setStatusCode(HttpStatus.OK.value());
            } else {
                response.setEntity(null);
                response.setMessage("not deleted");
                response.setStatusCode(HttpStatus.NO_CONTENT.value());
            }
        } catch (Exception e) {
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("an error occurred" + e.getMessage());
        }
        return response;
    }
}









