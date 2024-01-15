package com.student.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuizDto {
    private Long quizId;
    private String title;
    private String description;
    private String questions;
    private Integer duration;
    private Long courseId;
}
