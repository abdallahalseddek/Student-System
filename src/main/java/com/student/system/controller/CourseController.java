package com.student.system.controller;

import com.student.system.dto.CourseDto;
import com.student.system.model.Course;
import com.student.system.service.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/course/")
@Tag(name = "Courses Endpoints")
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping("create")
    @ResponseStatus(CREATED)
    public Course addCourse(@RequestBody @Valid CourseDto courseDto) {
        return courseService.addCourse(courseDto);
    }

    @PutMapping("update")
    @ResponseStatus(ACCEPTED)
    public Course updateCourse(@RequestBody @Valid CourseDto courseDto) {
        return courseService.updateCourse(courseDto);
    }

    @GetMapping("{courseId}")
    @ResponseStatus(FOUND)
    public Course findCourse(@PathVariable @Valid Long courseId) {
        return courseService.findCourse(courseId);
    }

    @GetMapping("all")
    @ResponseStatus(FOUND)
    public List<Course> allCourses() {
        return courseService.allCourses();
    }

    @DeleteMapping("delete/{courseId}")
    @ResponseStatus(ACCEPTED)
    public String deleteCourse(@PathVariable @Valid Long courseId) {
        return courseService.deleteCourse(courseId);
    }

}
