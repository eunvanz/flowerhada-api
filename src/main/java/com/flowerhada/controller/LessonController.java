package com.flowerhada.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.flowerhada.domain.Lesson;
import com.flowerhada.property.DatePropertyEditor;
import com.flowerhada.property.NullIntegerPropertyEditor;
import com.flowerhada.property.NullStringPropertyEditor;
import com.flowerhada.property.TimePropertyEditor;
import com.flowerhada.service.LessonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("lessons")
public class LessonController {
	
	@Autowired LessonService lessonService;
	
	@PostMapping()
	public Lesson createLesson(@ModelAttribute Lesson lesson) {
		return lessonService.createLesson(lesson);
	}
	
	@GetMapping("/main-category/{mainCategory}")
	public List<Lesson> readLessonByMainCategory(@PathVariable String mainCategory) {
		return lessonService.readLessonsByMainCategory(mainCategory);
	}
	
	@GetMapping("/group-name/{groupName}")
	public List<Lesson> readLessonByGroupName(@PathVariable String groupName) {
		return lessonService.readLessonsByGroupName(groupName);
	}
	
	@GetMapping("/{id}")
	public Lesson readLesson(@PathVariable Long id) {
		return lessonService.readLesson(id);
	}
	
	@GetMapping()
	public List<Lesson> readLessons() {
		return lessonService.readLessons();
	}
	
	@PutMapping("/{id}")
	public Lesson updateLesson(@ModelAttribute Lesson lesson, @PathVariable Long id) {
		lesson.setId(id);
		return lessonService.updateLesson(lesson);
	}
	
	@DeleteMapping("/{id}")
	public void deleteLesson(@PathVariable Long id) {
		lessonService.deleteLesson(id);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DatePropertyEditor());
		binder.registerCustomEditor(Time.class, new TimePropertyEditor());
		binder.registerCustomEditor(int.class, new NullIntegerPropertyEditor());
		binder.registerCustomEditor(String.class, new NullStringPropertyEditor());
	}
}
