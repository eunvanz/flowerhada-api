package com.flowerhada.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flowerhada.domain.LessonDay;
import com.flowerhada.repository.LessonDayRepository;
import com.flowerhada.service.LessonDayService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class LessonDayServiceImpl implements LessonDayService {
	
	@Autowired LessonDayRepository lessonDayRepository;

	@Override
	public LessonDay createLessonDay(LessonDay lessonDay) {
		return lessonDayRepository.save(lessonDay);
	}

	@Override
	public void deleteLessonDay(Long id) {
		lessonDayRepository.delete(id);
	}

	@Override
	public LessonDay updateLessonDay(LessonDay lessonDay) {
		LessonDay oldLessonDay = lessonDayRepository.findOne(lessonDay.getId());
		oldLessonDay.setDay(lessonDay.getDay());
		oldLessonDay.setEndTime(lessonDay.getEndTime());
		oldLessonDay.setStartTime(lessonDay.getStartTime());
		return oldLessonDay;
	}
	
	@Override
	public void deleteLessonDayByLessonId(Long lessonId) {
		List<LessonDay> lessonDayList = lessonDayRepository.findByLessonId(lessonId);
		lessonDayRepository.delete(lessonDayList);
	}

}
