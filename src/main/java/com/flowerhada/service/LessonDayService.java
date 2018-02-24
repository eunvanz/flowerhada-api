package com.flowerhada.service;

import com.flowerhada.domain.LessonDay;

public interface LessonDayService {
	public LessonDay createLessonDay(LessonDay lessonDay);
	public void deleteLessonDay(Long id);
	public LessonDay updateLessonDay(LessonDay lessonDay);
	public void deleteLessonDayByLessonId(Long id);
}
