package com.info.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.info.dto.StudentDTO;
import com.info.entity.Student;
import com.info.repo.StudentRepository;

@Service
public class StudentService {
 private StudentRepository repo;
 
 public StudentService(StudentRepository repo) {
	 this.repo = repo;
 }
 public  Student saveStudent(StudentDTO studentDto) {
	 Student student  = new Student();
	 student.setName(studentDto.getName());
	 student.setRollNo(studentDto.getRollNo());
	 student.setEmail(studentDto.getEmail());
	 
	 student.setAddress(studentDto.getAddress());
	 student.setStandard(studentDto.getStandard());
	 return repo.save(student);
 }
 public Student getStudentById(int id) {
	 Student student  = repo.findById(id).orElse(null);
	 return student;
 }
 public List<Student> getAll(){
	 return repo.findAll();
 }
 public Student deleteStudentById(int id) {
	 Student student = repo.findById(id).orElse(null);
	 if(student !=null) {
		 repo.delete(student);
	 }
	 return student;
 }
 public Student updateStudentById(int id, StudentDTO studentDto) {
	 Student student = repo.findById(id).orElse(null);
	 if(student != null) {
		 student.setName(studentDto.getName());
		 student.setRollNo(studentDto.getRollNo());
		 student.setEmail(studentDto.getEmail());
		 
		 student.setAddress(studentDto.getAddress());
		 student.setStandard(studentDto.getStandard());
		 return repo.save(student);
	 }
	 return student;
 }
}
