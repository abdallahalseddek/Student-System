package com.student.system.controller;

import com.student.system.dto.StudentDto;
import com.student.system.model.Course;
import com.student.system.model.Student;
import com.student.system.service.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/student/")
@Tag(name = "Students Endpoints")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("create")
    @ResponseStatus(CREATED)
    public Student addStudent(@RequestBody @Valid StudentDto studentDto) {
        return studentService.addStudent(studentDto);
    }

    @GetMapping("{studentId}")
    @ResponseStatus(FOUND)
    public Student findStudent(@PathVariable @Valid Long studentId) {
        return studentService.findStudent(studentId);
    }

    @GetMapping("all")
    @ResponseStatus(FOUND)
    public List<Student> allStudents() {
        return studentService.allStudents();
    }

    @PutMapping("enroll/{studentId}/{courseId}")
    @ResponseStatus(ACCEPTED)
    public Course enrollStudentInCourse(@PathVariable @Valid Long studentId,
                                        @PathVariable @Valid Long courseId) {
        return studentService.enrollStudentInCourse(studentId, courseId);
    }

    @PutMapping("update")
    @ResponseStatus(ACCEPTED)
    public Student updateStudent(@RequestBody @Valid StudentDto studentDto) {
        return studentService.updateStudent(studentDto);
    }

    @DeleteMapping("delete/{studentId}")
    @ResponseStatus(ACCEPTED)
    public String deleteStudent(@PathVariable @Valid Long studentId) {
        return studentService.deleteStudent(studentId);
    }

}
