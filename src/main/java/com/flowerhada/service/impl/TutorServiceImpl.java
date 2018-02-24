package com.flowerhada.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flowerhada.domain.Tutor;
import com.flowerhada.repository.TutorRepository;
import com.flowerhada.service.TutorService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TutorServiceImpl implements TutorService {

	@Autowired TutorRepository tutorRepository;
	
	@Override
	public Tutor createTutor(Tutor tutor) {
		return tutorRepository.save(tutor);
	}

	@Override
	public Tutor readTutor(Long id) {
		return tutorRepository.findOne(id);
	}

	@Override
	public Tutor updateTutor(Tutor tutor) {
		Tutor oldTutor = tutorRepository.findOne(tutor.getId());
		oldTutor.setImage(tutor.getImage());
		oldTutor.setName(tutor.getName());
		oldTutor.setIntroduce(tutor.getIntroduce());
		return oldTutor;
	}

	@Override
	public void deleteTutor(Long id) {
		tutorRepository.delete(id);
	}

	@Override
	public List<Tutor> readAllTutors() {
		return tutorRepository.findAll();
	}

}
