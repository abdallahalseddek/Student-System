package com.student.system.service.impl;

import com.student.system.dto.CourseDto;
import com.student.system.model.Course;
import com.student.system.model.Teacher;
import com.student.system.repository.CourseRepository;
import com.student.system.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private ModelMapper mapper;
    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void addCourse() {
        Teacher teacher = new Teacher(1L, "ahmed", "mohamed", "ahmed@test.com",
                LocalDate.of(1998, 10, 14), LocalDate.now());
        Course course = new Course(1L, "Math 101", "Math for all", LocalDate.now(),
                LocalDate.of(2024, 5, 11), Set.of(), Set.of(), Set.of());
        CourseDto courseDto = new CourseDto(1L, "Math 101", "Math for all", LocalDate.now(),
                LocalDate.of(2024, 5, 11), teacher.getTeacherId());

        when(mapper.map(courseDto, Course.class)).thenReturn(course);
        when(courseRepository.save(course)).thenReturn(course);
        when(teacherRepository.findById(teacher.getTeacherId())).thenReturn(Optional.of(teacher));

        Course response = courseService.addCourse(courseDto);

        assertThat(response).isNotNull();
        assertThat(response.getCourseId()).isGreaterThan(0);
        assertEquals(course.getTitle(), response.getTitle());
    }

    @Test
    void findCourse() {
        Course course = new Course(1L, "Math 101", "Math for all", LocalDate.now(),
                LocalDate.of(2024, 5, 11), Set.of(), Set.of(), Set.of());
        Long id = 1L;

        when(courseRepository.findById(id)).thenReturn(Optional.of(course));

        Course response = courseService.findCourse(id);
        assertNotNull(response);
        assertEquals("Math 101", response.getTitle());
        assertEquals(id, course.getCourseId());

    }

    @Test
    void allCourses() {
        List<Course> courses = List.of(new Course(1L, "Math 101", "Math for all", LocalDate.now(),
                        LocalDate.of(2024, 5, 11), Set.of(), Set.of(), Set.of()),
                new Course(3L, "Science 101", "Forget for all", LocalDate.now(),
                        LocalDate.of(2024, 5, 11), Set.of(), Set.of(), Set.of()),
                new Course(2L, "Java 101", "Java for all", LocalDate.now(),
                        LocalDate.of(2024, 5, 11), Set.of(), Set.of(), Set.of()));

        when(courseRepository.findAll()).thenReturn(courses);

        List<Course> response = courseService.allCourses();

        assertNotNull(response);
        assertThat(response.size()).isEqualTo(courses.size());
        assertEquals(courses, response);

    }

    @Test
    void allTeacherCourses() {
        Object[] course1 = {1L, "Course 1", "Description 1", "2024-01-01", "2024-02-01"};
        Object[] course2 = {2L, "Course 2", "Description 2", "2024-02-01", "2024-03-01"};
        List<Object[]> mockedCourses = Arrays.asList(course1, course2);

        when(courseRepository.findCoursesByTeacherId(1L)).thenReturn(mockedCourses);

        List<Object[]> response = courseService.allTeacherCourses(1L);

        assertNotNull(response);
        assertEquals(2, response.size());
        assertArrayEquals(course1, response.get(0));
        assertArrayEquals(course2, response.get(1));
    }

    @Test
    void updateCourse() {
        Course course = new Course(1L, "Math 101", "Math for all", LocalDate.now(),
                LocalDate.of(2024, 5, 11), Set.of(), Set.of(), Set.of());
        CourseDto courseDto = new CourseDto(1L, "Math 101", "Math for all", LocalDate.now(),
                LocalDate.of(2024, 5, 11), 1L);

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(courseRepository.save(course)).thenReturn(course);

        Course response = courseService.updateCourse(courseDto);

        assertNotNull(response);
        assertThat(course).isEqualTo(response);

    }

    @Test
    void deleteCourse() {
        Course course = new Course(1L, "Math 101", "Math for all", LocalDate.now(),
                LocalDate.of(2024, 5, 11), Set.of(), Set.of(), Set.of());

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        doNothing().when(courseRepository).deleteById(1L);

        String response = courseService.deleteCourse(1L);

        assertNotNull(response);
    }
}