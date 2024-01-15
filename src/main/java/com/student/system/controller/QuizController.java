package com.student.system.controller;

import com.student.system.dto.QuizDto;
import com.student.system.model.Quiz;
import com.student.system.service.QuizService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/quiz/")
@Tag(name = "Quizzes Endpoints")
@AllArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @PostMapping("create")
    @ResponseStatus(CREATED)
    public Quiz addQuiz(@RequestBody @Valid QuizDto quizDto) {
        return quizService.addQuiz(quizDto);
    }

    @GetMapping("{quizId}")
    @ResponseStatus(FOUND)
    public Quiz findQuiz(@PathVariable @Valid Long quizId) {
        return quizService.findQuiz(quizId);
    }

    @GetMapping("all")
    @ResponseStatus(FOUND)
    public List<Quiz> allQuizzes() {
        return quizService.allQuizzes();
    }

    @PutMapping("update")
    @ResponseStatus(ACCEPTED)
    public Quiz updateQuiz(@RequestBody @Valid QuizDto quizDto) {
        return quizService.updateQuiz(quizDto);
    }

    @DeleteMapping("delete/{quizId}")
    @ResponseStatus(ACCEPTED)
    public String deleteQuiz(@PathVariable @Valid Long quizId) {
        return quizService.deleteQuiz(quizId);
    }

}
