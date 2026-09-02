package com.info.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.dto.StudentDTO;
import com.info.entity.Student;
import com.info.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
  private StudentService service;
  public StudentController(StudentService service) {
	  this.service = service;
  }
  @PostMapping()
  public ResponseEntity<Student> saveStudent(@RequestBody StudentDTO studentDto){
	  return ResponseEntity.ok().body(service.saveStudent(studentDto));
  }
  @GetMapping("/{id}")
  public ResponseEntity<Student> findStudentById( @PathVariable int id){
	  return ResponseEntity.ok().body(service.getStudentById(id));
  }
  @GetMapping()
  public ResponseEntity<List<Student>> findAll(){
	  return ResponseEntity.ok().body(service.getAll());
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<Student> deleteStudent( @PathVariable int id){
	  return ResponseEntity.ok().body(service.deleteStudentById(id));
  }
  @PutMapping("/{id}")
  public ResponseEntity<Student> updateStudent( @PathVariable int id, @RequestBody StudentDTO studentDto){
	  return ResponseEntity.ok().body(service.updateStudentById(id, studentDto));
  }
}
