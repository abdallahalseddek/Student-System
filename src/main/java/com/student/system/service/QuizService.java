package com.student.system.service;

import com.student.system.dto.QuizDto;
import com.student.system.model.Quiz;

import java.util.List;

public interface QuizService {
    Quiz addQuiz(QuizDto quizDto);
    Quiz findQuiz(Long quizId);
    List<Quiz> allQuizzes();
    List<Object[]> allCourseQuizzes(Long courseId);
    Quiz updateQuiz(QuizDto quizDto);
    String deleteQuiz(Long quizId);
}
