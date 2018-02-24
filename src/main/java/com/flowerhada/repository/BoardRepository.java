package com.flowerhada.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.flowerhada.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	public Page<Board> findByCategoryOrderByIdDesc(String category, Pageable pageable);
}
