package com.student.system.service.impl;

import com.student.system.dto.CourseDto;
import com.student.system.model.Course;
import com.student.system.model.Teacher;
import com.student.system.repository.CourseRepository;
import com.student.system.repository.TeacherRepository;
import com.student.system.service.CourseService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final ModelMapper mapper;

    @Transactional
    @Override
    public Course addCourse(CourseDto courseDto) {
        Teacher teacher = teacherRepository.findById(courseDto.getTeacherId())
                .orElseThrow(() -> new IllegalStateException("Teacher with Id '%d' Not Found !!"
                        .formatted(courseDto.getTeacherId())));
        Course course = mapper.map(courseDto, Course.class);
        course.setTeachers(Set.of(teacher));
        return courseRepository.save(course);
    }

    @Override
    public Course findCourse(Long courseId) {
        return isCourseExists(courseId);
    }

    @Override
    public List<Course> allCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Object[]> allTeacherCourses(Long teacherId) {
        return courseRepository.findCoursesByTeacherId(teacherId);
    }

    @Transactional
    @Override
    public Course updateCourse(CourseDto courseDto) {
        Course course = isCourseExists(courseDto.getCourseId());
        course.setTitle(courseDto.getTitle());
        course.setDescription(courseDto.getDescription());
        course.setStartDate(courseDto.getStartDate());
        course.setEndDate(courseDto.getEndDate());
        return courseRepository.save(course);
    }
    @Transactional
    @Override
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
