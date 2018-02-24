package com.flowerhada.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flowerhada.domain.Tutor;
import com.flowerhada.service.TutorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("tutors")
public class TutorController {

	@Autowired TutorService tutorService;
	
	@GetMapping()
	public List<Tutor> getAllTutors() {
		return tutorService.readAllTutors();
	}
	
	@GetMapping("/{id}")
	public Tutor getTutorById(@PathVariable Long id) {
		return tutorService.readTutor(id);
	}
	
	@PutMapping()
	public Tutor putTutor(@RequestBody Tutor tutor) {
		return tutorService.updateTutor(tutor);
	}
	
	@PostMapping()
	public Tutor postTutor(@RequestBody Tutor tutor) {
		return tutorService.createTutor(tutor);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTutor(@PathVariable Long id) {
		tutorService.deleteTutor(id);
	}
	
}
