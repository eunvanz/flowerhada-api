package com.flowerhada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flowerhada.domain.Board;
import com.flowerhada.domain.Inquiry;
import com.flowerhada.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("boards")
public class BoardController {

	@Autowired BoardService boardService;
	
	@GetMapping("/category/{category}")
	public Page<Board> getAllInauiriesByUserId(@PathVariable String category, @RequestParam("curPage") int curPage, @RequestParam("perPage") int perPage) {
		PageRequest pageRequest = new PageRequest(curPage, perPage, new Sort(Direction.DESC, "id"));
		return boardService.readByCategoryOrderByIdDesc(category, pageRequest);
	}
	
	@PutMapping("/increase-view/{id}")
	public Board increaseBoardView(@PathVariable Long id) {
		return boardService.increaseView(id);
	}
	
	@GetMapping("/{id}")
	public Board getBoardById(@PathVariable Long id) {
		return boardService.readById(id);
	}
	
	@PostMapping()
	public Board postBoard(@RequestBody Board board) {
		return boardService.createBoard(board);
	}
	
	@PutMapping()
	public Board putBoard(@RequestBody Board board) {
		return boardService.updateBoard(board);
	}
	
	@DeleteMapping("/{id}")
	public void deleteBoard(@PathVariable Long id) {
		boardService.deleteBoard(id);
	}
	
}
