package com.student.system.controller;

import com.student.system.dto.TeacherDto;
import com.student.system.model.Teacher;
import com.student.system.service.TeacherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/teacher/")
@Tag(name = "Teachers Endpoints")
@AllArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping("create")
    @ResponseStatus(CREATED)
    public Teacher addTeacher(@RequestBody @Valid TeacherDto teacherDto) {
        return teacherService.addTeacher(teacherDto);
    }

    @GetMapping("{teacherId}")
    @ResponseStatus(FOUND)
    public Teacher findTeacher(@PathVariable @Valid Long teacherId) {
        return teacherService.findTeacher(teacherId);
    }

    @GetMapping("all")
    @ResponseStatus(FOUND)
    public List<Teacher> allTeachers() {
        return teacherService.allTeachers();
    }

    @PutMapping("update")
    @ResponseStatus(ACCEPTED)
    public Teacher updateTeacher(@RequestBody @Valid TeacherDto teacherDto) {
        return teacherService.updateTeacher(teacherDto);
    }

    @DeleteMapping("delete/{teacherId}")
    @ResponseStatus(ACCEPTED)
    public String deleteTeacher(@PathVariable @Valid Long teacherId) {
        return teacherService.deleteTeacher(teacherId);
    }

}
