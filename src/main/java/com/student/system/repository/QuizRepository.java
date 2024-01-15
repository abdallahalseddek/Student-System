package com.student.system.repository;

import com.student.system.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query(value = "SELECT q.title,q.description,q.duration,q.description FROM Quiz q JOIN quiz_course qc ON q.quiz_id = qc.quiz_id WHERE qc.course_id =:courseId",nativeQuery = true)
    List<Object[]> findAllCourseQuizzes(@Param("courseId") Long courseId);
}
