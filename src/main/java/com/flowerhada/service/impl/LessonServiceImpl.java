package com.flowerhada.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flowerhada.domain.Lesson;
import com.flowerhada.repository.LessonRepository;
import com.flowerhada.service.LessonService;
import com.flowerhada.util.Utils;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
	
	@Autowired LessonRepository lessonRepository;

	@Override
	public Lesson createLesson(Lesson lesson) {
		lesson.setRegDateTime(Utils.getZonedDateTimeNow("Asia/Seoul"));
		return lessonRepository.save(lesson);
	}

	@Override
	public void deleteLesson(Long id) {
		lessonRepository.delete(id);
	}

	@Override
	public Lesson updateLesson(Lesson lesson) {
		Lesson oldLesson = lessonRepository.findOne(lesson.getId());
		oldLesson.setActivated(lesson.isActivated());
		oldLesson.setAddress(lesson.getAddress());
		oldLesson.setRestAddress(lesson.getRestAddress());
		oldLesson.setContent(lesson.getContent());
		oldLesson.setCurrParty(lesson.getCurrParty());
		oldLesson.setDetail(lesson.getDetail());
		oldLesson.setDiscountedPrice(lesson.getDiscountedPrice());
		oldLesson.setEndTime(lesson.getEndTime());
		oldLesson.setExpired(lesson.isExpired());
		oldLesson.setLatitude(lesson.getLatitude());
		oldLesson.setLessonDate(lesson.getLessonDate());
		oldLesson.setLocation(lesson.getLocation());
		oldLesson.setLongitude(lesson.getLongitude());
		oldLesson.setMainCategory(lesson.getMainCategory());
		oldLesson.setMaxParty(lesson.getMaxParty());
		oldLesson.setOneday(lesson.isOneday());
		oldLesson.setPostCode(lesson.getPostCode());
		oldLesson.setPrice(lesson.getPrice());
		oldLesson.setStartTime(lesson.getStartTime());
		oldLesson.setSubCategory(lesson.getSubCategory());
		oldLesson.setTitle(lesson.getTitle());
		oldLesson.setTitleImg(lesson.getTitleImg());
		oldLesson.setWeekType(lesson.getWeekType());
		oldLesson.setWeekLong(lesson.getWeekLong());
		oldLesson.setGroupName(lesson.getGroupName());
		oldLesson.setLevel(lesson.getLevel());
		return oldLesson;
	}

	@Override
	public Lesson readLesson(Long id) {
		return lessonRepository.findOne(id);
	}

	@Override
	public List<Lesson> readLessons() {
		return lessonRepository.findAllByOrderByIdDesc();
	}

	@Override
	public List<Lesson> readLessonsByMainCategory(String mainCategory) {
		return lessonRepository.findByMainCategoryAndActivatedOrderByIdDesc(mainCategory, true);
	}

	@Override
	public List<Lesson> readLessonsByGroupName(String groupName) {
		return lessonRepository.findByGroupNameAndActivatedOrderByIdDesc(groupName, true);
	}

}
