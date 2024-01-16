package com.student.system.service.impl;

import com.student.system.dto.TeacherDto;
import com.student.system.model.Teacher;
import com.student.system.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeacherServiceImplTest {
    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private ModelMapper mapper;
    @InjectMocks
    private TeacherServiceImpl teacherService;

    @Test
    void addTeacher() {
        Teacher teacher = new Teacher(1L, "ahmed", "mohamed", "ahmed@test.com",
                LocalDate.of(1998, 10, 14), LocalDate.now());
        TeacherDto teacherDto = new TeacherDto("ahmed", "mohamed", "ahmed@test.com",
                LocalDate.of(1998, 10, 14), LocalDate.now());

        when(mapper.map(teacherDto, Teacher.class)).thenReturn(teacher);
        when(teacherRepository.save(teacher)).thenReturn(teacher);

        Teacher addedTeacher = teacherService.addTeacher(teacherDto);

        assertThat(addedTeacher).isNotNull();
        assertThat(addedTeacher.getTeacherId()).isGreaterThan(0);
        assertEquals(teacher.getEmail(), addedTeacher.getEmail());
    }

    @Test
    void findTeacher() {
        Teacher teacher = new Teacher(1L, "ahmed", "mohamed", "ahmed@test.com",
                LocalDate.of(1998, 10, 14), LocalDate.now());

        Long teacherId = 1L;
        when(teacherRepository.findById(teacherId))
                .thenReturn(Optional.of(teacher));

        Teacher response = teacherService.findTeacher(teacher.getTeacherId());

        assertNotNull(response);
        assertEquals("ahmed@test.com", response.getEmail());
        assertEquals(teacherId, teacher.getTeacherId());
    }

    @Test
    void allTeachers() {
        List<Teacher> teachers = List.of(new Teacher(1L, "ahmed", "mohamed", "ahmed@test.com",
                        LocalDate.of(1998, 10, 14), LocalDate.now()),
                new Teacher(2L, "abdallah", "mohamed", "abdallah@test.com",
                        LocalDate.of(1998, 10, 14), LocalDate.now()),
                new Teacher(3L, "ahmed", "mohamed", "john@test.com",
                        LocalDate.of(1998, 10, 14), LocalDate.now()));

        when(teacherRepository.findAll()).thenReturn(teachers);

        List<Teacher> response = teacherService.allTeachers();

        assertNotNull(response);
        assertThat(response.size()).isEqualTo(teachers.size());
        assertEquals(teachers, response);
    }

    @Test
    void updateTeacher() {
        Teacher teacher = new Teacher(1L, "ahmed", "mohamed", "ahmed@test.com",
                LocalDate.of(1998, 10, 14), LocalDate.now());
        TeacherDto teacherDto = new TeacherDto("ahmed", "mohamed", "ahmed@test.com",
                LocalDate.of(1998, 10, 14), LocalDate.now());

        when(teacherRepository.findByEmail(teacherDto.getEmail()))
                .thenReturn(Optional.of(teacher));
        when(teacherRepository.save(teacher)).thenReturn(teacher);

        Teacher response = teacherService.updateTeacher(teacherDto);

        assertNotNull(response);
        assertThat(teacher).isEqualTo(response);
    }

    @Test
    void deleteTeacher() {
        Teacher teacher = new Teacher(1L, "ahmed", "mohamed", "ahmed@test.com",
                LocalDate.of(1998, 10, 14), LocalDate.now());
        Long teacherId = 1L;

        when(teacherRepository.findById(teacherId))
                .thenReturn(Optional.of(teacher));
        doNothing().when(teacherRepository).deleteById(teacherId);

        String response = teacherService.deleteTeacher(teacherId);

        assertNotNull(response);
    }
}