package com.flowerhada.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flowerhada.domain.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
	List<Lesson> findByMainCategoryAndActivatedOrderByIdDesc(String mainCategory, boolean activated);
	List<Lesson> findByGroupNameAndActivatedOrderByIdDesc(String groupName, boolean activated);
	List<Lesson> findAllByOrderByIdDesc();
	List<Lesson> findByActivatedOrderByIdDesc(boolean activated);
}
