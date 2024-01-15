package com.student.system.service;

import com.student.system.dto.CourseDto;
import com.student.system.model.Course;

import java.util.List;

public interface CourseService {
    Course addCourse(CourseDto courseDto);
    Course findCourse(Long courseId);
    List<Course> allCourses();
    List<Object[]> allTeacherCourses(Long teacherId);
    Course updateCourse(CourseDto courseDto);
    String deleteCourse(Long courseId);
    Course isCourseExists(Long courseId);
}
