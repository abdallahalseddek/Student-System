package com.student.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDto {
    private Long courseId;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long teacherId;
}
