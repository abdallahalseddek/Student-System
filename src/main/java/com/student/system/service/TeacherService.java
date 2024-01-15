package com.student.system.service;

import com.student.system.dto.TeacherDto;
import com.student.system.model.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher addTeacher(TeacherDto teacherDto);
    Teacher findTeacher(Long teacherId);
    List<Teacher> allTeachers();
    Teacher updateTeacher(TeacherDto teacherDto);
    String deleteTeacher(Long teacherId);
}
