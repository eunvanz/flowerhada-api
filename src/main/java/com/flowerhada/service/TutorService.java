package com.flowerhada.service;

import java.util.List;

import com.flowerhada.domain.Tutor;

public interface TutorService {
	public Tutor createTutor(Tutor tutor);
	public Tutor readTutor(Long id);
	public List<Tutor> readAllTutors();
	public Tutor updateTutor(Tutor tutor);
	public void deleteTutor(Long id);
}
