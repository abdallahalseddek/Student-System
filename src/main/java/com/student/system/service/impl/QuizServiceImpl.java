package com.student.system.service.impl;

import com.student.system.dto.QuizDto;
import com.student.system.model.Course;
import com.student.system.model.Quiz;
import com.student.system.repository.CourseRepository;
import com.student.system.repository.QuizRepository;
import com.student.system.service.QuizService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper mapper;
    @Transactional
    @Override
    public Quiz addQuiz(QuizDto quizDto) {
        Course course = courseRepository.findById(quizDto.getCourseId())
                .orElseThrow(() -> new IllegalStateException("Course with id '%d' Not Exists..."
                        .formatted(quizDto.getCourseId())));
        Quiz quiz = mapper.map(quizDto, Quiz.class);
        course.setQuizzes(Set.of(quiz));
        System.out.println(Set.of(quiz));
        System.out.println(course);
        return quizRepository.save(quiz);
        // TODO: debug why it updates on the exist in quiz_course table and not creating a new record
    }
    @Override
    public Quiz findQuiz(Long quizId) {
        return isQuizExists(quizId);
    }
    @Override
    public List<Quiz> allQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public List<Object[]> allCourseQuizzes(Long courseId) {
        return quizRepository.findAllCourseQuizzes(courseId);
    }

    @Transactional
    @Override
    public Quiz updateQuiz(QuizDto quizDto) {
        Quiz quiz = isQuizExists(quizDto.getQuizId());
        quiz.setTitle(quizDto.getTitle());
        quiz.setDescription(quizDto.getDescription());
        quiz.setQuestions(quizDto.getQuestions());
        quiz.setDuration(quizDto.getDuration());
        return quizRepository.save(quiz);
    }
    @Transactional
    @Override
    public String deleteQuiz(Long quizId) {
        isQuizExists(quizId);
        quizRepository.deleteById(quizId);
        return "Quiz with id '%d' deleted successfully".formatted(quizId);
    }

    public Quiz isQuizExists(Long quizId) {
        return quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalStateException("Quiz with id '%d' Not Exists..."
                        .formatted(quizId)));
    }
}
