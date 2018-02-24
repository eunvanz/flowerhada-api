package com.flowerhada.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.flowerhada.domain.Board;
import com.flowerhada.repository.BoardRepository;
import com.flowerhada.service.BoardService;
import com.flowerhada.util.Utils;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired BoardRepository boardRepository;
	
	@Override
	public Page<Board> readByCategoryOrderByIdDesc(String category, Pageable pageable) {
		return boardRepository.findByCategoryOrderByIdDesc(category, pageable);
	}

	@Override
	public Board readById(Long id) {
		return boardRepository.findOne(id);
	}

	@Override
	public Board createBoard(Board board) {
		System.out.println("board: " + board);
		board.setUpdateDate(Utils.getZonedDateTimeNow("Asia/Seoul"));
		board.setRegDate(Utils.getZonedDateTimeNow("Asia/Seoul"));
		board.setView(0);
		return boardRepository.save(board);
	}

	@Override
	public void deleteBoard(Long id) {
		boardRepository.delete(id);
	}

	@Override
	public Board updateBoard(Board board) {
		Board oldBoard = boardRepository.findOne(board.getId());
		oldBoard.setCategory(board.getCategory());
		oldBoard.setContent(board.getContent());
		oldBoard.setTitle(board.getTitle());
		oldBoard.setUpdateDate(Utils.getZonedDateTimeNow("Asia/Seoul"));
		return oldBoard;
	}

	@Override
	public Board increaseView(Long id) {
		Board oldBoard = boardRepository.findOne(id);
		int updatedView = oldBoard.getView() + 1;
		oldBoard.setView(updatedView);
		return oldBoard;
	}

}
