package com.student.system.service.impl;

import com.student.system.dto.StudentDto;
import com.student.system.model.Course;
import com.student.system.model.Student;
import com.student.system.repository.CourseRepository;
import com.student.system.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private ModelMapper mapper;
    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void addStudent() {
        Student student = new Student(1L, "john", "khan", "john@pepole.com",
                LocalDate.of(1998, 5, 12), LocalDate.now());
        StudentDto studentDto = new StudentDto("john", "khan", "john@pepole.com",
                LocalDate.of(1998, 5, 12), LocalDate.now());

        when(mapper.map(studentDto, Student.class)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(student);

        Student response = studentService.addStudent(studentDto);

        assertNotNull(response);
        assertThat(response.getStudentId()).isGreaterThan(0);
        assertEquals("john@pepole.com", response.getEmail());
    }

    @Test
    void findStudent() {
        Long id = 1L;
        Student student = new Student(1L, "john", "khan", "john@pepole.com",
                LocalDate.of(1998, 5, 12), LocalDate.now());

        when(studentRepository.findById(id)).thenReturn(Optional.of(student));

        Student response = studentService.findStudent(id);

        assertEquals(id, response.getStudentId());
        assertEquals("john", response.getFirstName());
        assertEquals("john@pepole.com", response.getEmail());

    }

    @Test
    void allStudents() {
        List<Student> students = List.of(new Student(1L, "john", "khan", "john@pepole.com",
                        LocalDate.of(1998, 5, 12), LocalDate.now()),
                new Student(2L, "john", "khan", "ahmed@pepole.com",
                        LocalDate.of(1998, 5, 12), LocalDate.now()),
                new Student(3L, "yousef", "khan", "yousef@pepole.com",
                        LocalDate.of(1998, 5, 12), LocalDate.now()));

        when(studentRepository.findAll()).thenReturn(students);

        List<Student> response = studentService.allStudents();

        assertThat(students.size()).isEqualTo(response.size());
        assertEquals(students, response);
    }

    @Test
    void enrollStudentInCourse() {
        Long studId = 1L;
        Long courseId = 1L;
        Student student = new Student(1L, "john", "khan", "john@pepole.com",
                LocalDate.of(1998, 5, 12), LocalDate.now());
        Course course = new Course(1L, "Math 101", "Math for all", LocalDate.now(),
                LocalDate.of(2024, 5, 11), Set.of(), Set.of(), Set.of());

        when(studentRepository.findById(studId)).thenReturn(Optional.of(student));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(courseRepository.save(course)).thenReturn(course);

        assertAll(() -> studentService.enrollStudentInCourse(studId, courseId));
    }

    @Test
    void updateStudent() {
        Student student = new Student(1L, "john", "khan", "john@pepole.com",
                LocalDate.of(1998, 5, 12), LocalDate.now());
        StudentDto studentDto = new StudentDto("john", "khan", "john@pepole.com",
                LocalDate.of(1998, 5, 12), LocalDate.now());

        when(studentRepository.findByEmail(studentDto.getEmail())).thenReturn(Optional.of(student));
        when(studentRepository.save(student)).thenReturn(student);

        Student response = studentService.updateStudent(studentDto);

        assertNotNull(response);
        assertThat(student).isEqualTo(response);
    }

    @Test
    void deleteStudent() {
        Student student = new Student(1L, "john", "khan", "john@pepole.com",
                LocalDate.of(1998, 5, 12), LocalDate.now());
        Long id = 1L;

        when(studentRepository.findById(id)).thenReturn(Optional.of(student));
        doNothing().when(studentRepository).deleteById(id);

        String response = studentService.deleteStudent(id);

        assertNotNull(response);

    }
}