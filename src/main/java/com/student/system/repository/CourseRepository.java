package com.student.system.repository;

import com.student.system.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(value = "SELECT c.course_id, c.title, c.description, c.start_date, c.end_date FROM course c JOIN teacher_course tc ON c.course_id = tc.course_id WHERE tc.teacher_id = :teacherId", nativeQuery = true)
    List<Object[]> findCoursesByTeacherId(@Param("teacherId") Long teacherId);
}
