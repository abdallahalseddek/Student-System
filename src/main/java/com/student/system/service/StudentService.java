package com.student.system.service;

import com.student.system.dto.StudentDto;
import com.student.system.model.Course;
import com.student.system.model.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(StudentDto studentDto);
    Student findStudent(Long studentId);
    List<Student> allStudents();
    Course enrollStudentInCourse(Long studentId, Long courseId);
    Student updateStudent(StudentDto studentDto);
    String deleteStudent(Long studentId);
}
