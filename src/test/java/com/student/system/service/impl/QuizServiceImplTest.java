package com.student.system.service.impl;

import com.student.system.dto.QuizDto;
import com.student.system.model.Course;
import com.student.system.model.Quiz;
import com.student.system.repository.CourseRepository;
import com.student.system.repository.QuizRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuizServiceImplTest {
    @Mock
    private QuizRepository quizRepository;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private ModelMapper mapper;
    @InjectMocks
    private QuizServiceImpl quizService;

    @Test
    void addQuiz() {
        Course course = new Course(1L, "Math 101", "Math for all", LocalDate.now(),
                LocalDate.of(2024, 5, 11), Set.of(), Set.of(), Set.of());
        Quiz quiz = new Quiz(1L, "test prog", "you'll fail"
                , "you can't answer", 30);
        QuizDto quizDto = new QuizDto(1L, "test prog", "you'll fail"
                , "you can't answer", 30, 1L);

        when(mapper.map(quizDto, Quiz.class)).thenReturn(quiz);
        when(quizRepository.save(quiz)).thenReturn(quiz);
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        Quiz response = quizService.addQuiz(quizDto);

        assertThat(response).isNotNull();
        assertThat(response.getQuizId()).isGreaterThan(0);
        assertEquals("test prog", response.getTitle());

    }

    @Test
    void findQuiz() {
        Quiz quiz = new Quiz(1L, "test prog", "you'll fail"
                , "you can't answer", 30);

        when(quizRepository.findById(1L)).thenReturn(Optional.of(quiz));

        Quiz response = quizService.findQuiz(1L);

        assertNotNull(response);
        assertEquals("test prog", response.getTitle());
        assertEquals(1, quiz.getQuizId());

    }

    @Test
    void allQuizzes() {
        List<Quiz> quizzes = List.of(new Quiz(1L, "test prog", "you'll fail"
                        , "you can't answer", 30),
                new Quiz(2L, "test case", "you'll fail"
                        , "you can't answer", 15),
                new Quiz(3L, "test fail", "you'll fail"
                        , "you can't answer", 20));

        when(quizRepository.findAll()).thenReturn(quizzes);

        List<Quiz> response = quizService.allQuizzes();

        assertNotNull(response);
        assertThat(response.size()).isEqualTo(3);
        assertEquals(quizzes, response);
    }

    @Test
    void allCourseQuizzes() {
        Object[] quiz1 = {1L, "quiz 1", "Description 1", 30, "new Quiz"};
        Object[] quiz2 = {2L, "Course 2", "Description 2", 4, "new Quiz"};
        List<Object[]> mockedQuizzes = Arrays.asList(quiz1, quiz2);

        when(quizRepository.findAllCourseQuizzes(anyLong())).thenReturn(mockedQuizzes);

        List<Object[]> response = quizService.allCourseQuizzes(anyLong());

        assertNotNull(response);
        assertEquals(2, response.size());
        assertArrayEquals(quiz1, response.get(0));
        assertArrayEquals(quiz2, response.get(1));

    }

    @Test
    void updateQuiz() {
        Quiz quiz = new Quiz(1L, "test prog", "you'll fail"
                , "you can't answer", 30);
        QuizDto quizDto = new QuizDto(1L, "test prog", "you'll fail"
                , "you can't answer", 30, 1L);

        when(quizRepository.findById(anyLong())).thenReturn(Optional.of(quiz));
        when(quizRepository.save(quiz)).thenReturn(quiz);

        Quiz response = quizService.updateQuiz(quizDto);

        assertNotNull(response);
        assertThat(quiz).isEqualTo(response);

    }

    @Test
    void deleteQuiz() {
        Quiz quiz = new Quiz(1L, "test prog", "you'll fail"
                , "you can't answer", 30);

        when(quizRepository.findById(anyLong())).thenReturn(Optional.of(quiz));
        doNothing().when(quizRepository).deleteById(anyLong());

        String response = quizService.deleteQuiz(anyLong());

        assertNotNull(response);

    }
}