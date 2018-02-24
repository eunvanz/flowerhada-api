package com.flowerhada.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.flowerhada.domain.Board;

public interface BoardService {
	public Page<Board> readByCategoryOrderByIdDesc(String category, Pageable pageable);
	public Board readById(Long id);
	public Board createBoard(Board board);
	public void deleteBoard(Long id);
	public Board updateBoard(Board board);
	public Board increaseView(Long id);
}
