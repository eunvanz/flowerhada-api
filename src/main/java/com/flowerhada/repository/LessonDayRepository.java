package com.flowerhada.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flowerhada.domain.LessonDay;

public interface LessonDayRepository extends JpaRepository<LessonDay, Long> {
	List<LessonDay> findByLessonId(Long lessonId);
}
