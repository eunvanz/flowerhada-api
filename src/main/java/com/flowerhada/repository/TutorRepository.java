package com.flowerhada.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flowerhada.domain.Tutor;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
}
