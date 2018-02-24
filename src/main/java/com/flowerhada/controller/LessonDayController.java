package com.flowerhada.controller;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flowerhada.domain.LessonDay;
import com.flowerhada.property.TimePropertyEditor;
import com.flowerhada.service.LessonDayService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("lesson-days")
public class LessonDayController {

	@Autowired LessonDayService lessonDayService;
	
	@PostMapping()
	public LessonDay createLessonDay(@ModelAttribute LessonDay lessonDay) {
		return lessonDayService.createLessonDay(lessonDay);
	}
	
	@PutMapping("/{id}")
	public LessonDay updateLessonDay(@ModelAttribute LessonDay lessonDay, @PathVariable Long id) {
		lessonDay.setId(id);
		return lessonDayService.updateLessonDay(lessonDay);
	}
	
	@DeleteMapping("/lesson-id/{id}")
	public void deleteLessonDayByLessonId(@PathVariable Long id) {
		lessonDayService.deleteLessonDayByLessonId(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteLessonDay(@PathVariable Long id) {
		lessonDayService.deleteLessonDay(id);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Time.class, new TimePropertyEditor());
	}
}
