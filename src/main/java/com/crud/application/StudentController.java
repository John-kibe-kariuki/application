package com.crud.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private  StudentService studentService;

    @PostMapping("add")
    public ResponseEntity<EntityResponse>addNewStudent(@RequestBody Student student){
        EntityResponse response=studentService.addNewStudent(student);
        return ResponseEntity.status(response.getStatusCode()).body(response);}
    @GetMapping("get-all")
    public ResponseEntity<EntityResponse>getStudents(){
        EntityResponse response=studentService.getSudents();
        return  ResponseEntity.status(response.getStatusCode()).body(response);}
    @GetMapping("Your-student")
    public ResponseEntity<EntityResponse>getStudent(@RequestParam Long id){
        EntityResponse response=studentService.getStudent(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<EntityResponse>updateStudent(@RequestBody Student request,@RequestParam Long id){
        EntityResponse response=studentService.UpdateStudent(request,id);
        return ResponseEntity.status(response.getStatusCode()).body(response);}
    @DeleteMapping("delete")
    public ResponseEntity<EntityResponse>deleteStudent(@RequestParam Long id){
        EntityResponse response=studentService.deleteStudent(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);}




    }


