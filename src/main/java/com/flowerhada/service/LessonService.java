package com.flowerhada.service;

import java.util.List;

import com.flowerhada.domain.Lesson;

public interface LessonService {
	public Lesson createLesson(Lesson lesson);
	public void deleteLesson(Long id);
	public Lesson updateLesson(Lesson lesson);
	public Lesson readLesson(Long id);
	public List<Lesson> readLessons();
	public List<Lesson> readLessonsByMainCategory(String mainCategory);
	public List<Lesson> readLessonsByGroupName(String groupName);
}
