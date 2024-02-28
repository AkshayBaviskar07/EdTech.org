package org.telusko.course.TeluskoApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.telusko.course.TeluskoApp.model.Course;

import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {
    Optional<Course> findByCourseName(String courseName);
}
