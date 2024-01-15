package com.student.system.service.impl;

import com.student.system.dto.StudentDto;
import com.student.system.model.Course;
import com.student.system.model.Student;
import com.student.system.repository.CourseRepository;
import com.student.system.repository.StudentRepository;
import com.student.system.service.StudentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper mapper;
    private final LocalDate date = LocalDate.now();
    @Transactional
    @Override
    public Student addStudent(StudentDto studentDto) {
        if (studentRepository.findByEmail(studentDto.getEmail()).isPresent()) {
            throw new IllegalStateException("Student with Email '"
                    + studentDto.getEmail() + "' Already Exists !!");
        }
        Student student = mapper.map(studentDto, Student.class);
        student.setEnrollmentDate(date);
        return studentRepository.save(student);
    }
    @Override
    public Student findStudent(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow();
    }
    @Override
    public List<Student> allStudents() {
        return studentRepository.findAll();
    }
    @Override
    public Course enrollStudentInCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();
        course.setStudents(Set.of(student));
        return courseRepository.save(course);
    }
    @Transactional
    @Override
    public Student updateStudent(StudentDto studentDto) {
        Student student = studentRepository.findByEmail(studentDto.getEmail())
                .orElseThrow(() -> new IllegalStateException("Student with Email '%s' Not Exists..."
                        .formatted(studentDto.getEmail())));
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setDateOfBirth(studentDto.getDateOfBirth());
        return studentRepository.save(student);
    }
    @Transactional
    @Override
    public String deleteStudent(Long studentId) {
        studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with id '%d' Not Exists..."
                        .formatted(studentId)));
        studentRepository.deleteById(studentId);
        return "Student with id '%d' deleted successfully".formatted(studentId);
    }
}
