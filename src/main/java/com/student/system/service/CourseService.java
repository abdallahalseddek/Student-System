package com.student.system.service;

import com.student.system.dto.CourseDto;
import com.student.system.model.Course;
import com.student.system.model.Teacher;
import com.student.system.repository.CourseRepository;
import com.student.system.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
@Transactional
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final ModelMapper mapper;

    public Course addCourse(CourseDto courseDto) {
        Teacher teacher = teacherRepository.findById(courseDto.getTeacherId())
                .orElseThrow(() -> new IllegalStateException("Teacher with Id '%d' Not Found !!"
                        .formatted(courseDto.getTeacherId())));
        Course course = mapper.map(courseDto, Course.class);
        course.setTeachers(Set.of(teacher));
        return courseRepository.save(course);
    }

    public Course findCourse(Long courseId) {
        return isCourseExists(courseId);
    }

    public List<Course> allCourses() {
        return courseRepository.findAll();
    }

    public Course updateCourse(CourseDto courseDto) {
        Course course = isCourseExists(courseDto.getCourseId());
        course.setTitle(courseDto.getTitle());
        course.setDescription(courseDto.getDescription());
        course.setStartDate(courseDto.getStartDate());
        course.setEndDate(courseDto.getEndDate());
        return courseRepository.save(course);
    }

    public String deleteCourse(Long courseId) {
        isCourseExists(courseId);
        courseRepository.deleteById(courseId);
        return "Course with id '%d' deleted successfully".formatted(courseId);
    }

    public Course isCourseExists(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException("course with Id '%d' Not Exists ..."
                        .formatted(courseId)));
    }
}
