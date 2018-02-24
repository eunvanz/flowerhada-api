package com.flowerhada.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flowerhada.domain.PointHistory;
import com.flowerhada.service.PointHistoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("point-histories")
public class PointHistoryController {

	@Autowired PointHistoryService pointHistoryService;
	
	@GetMapping("/user/{userId}")
	public Page<PointHistory> getPointHistoryByUserId(@PathVariable Long userId, @RequestParam("curPage") int curPage, @RequestParam("perPage") int perPage) {
		PageRequest pageRequest = new PageRequest(curPage, perPage, new Sort(Direction.DESC, "id"));
		return pointHistoryService.readPointHistoryByUserId(userId, pageRequest);
	}
	
	@PostMapping()
	public PointHistory postPointHistory(@RequestBody PointHistory pointHistory) throws Exception {
		return pointHistoryService.createPointHistory(pointHistory);
	}
	
}
