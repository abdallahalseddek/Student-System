package com.student.system.service.impl;

import com.student.system.dto.TeacherDto;
import com.student.system.model.Teacher;
import com.student.system.repository.TeacherRepository;
import com.student.system.service.TeacherService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final ModelMapper mapper;
    private final LocalDate date = LocalDate.now();
    @Transactional
    @Override
    public Teacher addTeacher(TeacherDto teacherDto) {
        if (teacherRepository.findByEmail(teacherDto.getEmail()).isPresent()) {
            throw new IllegalStateException("Teacher with Email '%s' Already Exists !!"
                    .formatted(teacherDto.getEmail()));
        }
        Teacher teacher = mapper.map(teacherDto, Teacher.class);
        teacher.setHireDate(date);
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher findTeacher(Long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalStateException("Not Found Teacher"));
    }
    @Override
    public List<Teacher> allTeachers() {
        return teacherRepository.findAll();
    }
    @Transactional
    @Override
    public Teacher updateTeacher(TeacherDto teacherDto) {
        Teacher teacher = teacherRepository.findByEmail(teacherDto.getEmail())
                .orElseThrow(() -> new IllegalStateException("Teacher with Email '%s' Not Exists"
                        .formatted(teacherDto.getEmail())));
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setDateOfBirth(teacherDto.getDateOfBirth());
        return teacherRepository.save(teacher);
    }
    @Transactional
    @Override
    public String deleteTeacher(Long teacherId) {
        teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalStateException("Teacher with id '%d' Not Exists..."
                        .formatted(teacherId)));
        teacherRepository.deleteById(teacherId);
        return "Teacher with id '%d' deleted successfully".formatted(teacherId);
    }
}
