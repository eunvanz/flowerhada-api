package com.flowerhada.controller;

import java.util.List;

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

import com.flowerhada.domain.Comment;
import com.flowerhada.domain.User;
import com.flowerhada.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("comments")
public class CommentController {

	@Autowired CommentService commentService;
	
	@GetMapping("/{id}")
	public Comment readComment(@PathVariable Long id) {
		return commentService.readComment(id);
	}
	
	@PostMapping()
	public Comment createComment(@RequestBody Comment comment) {
		return commentService.createComment(comment);
	}
	
	@PutMapping("/{id}")
	public Comment updateComment(@RequestBody Comment comment, @PathVariable Long id) {
		comment.setId(id);
		return commentService.updateComment(comment);
	}
	
	@DeleteMapping("/{id}")
	public void deleteComment(@PathVariable Long id) {
		commentService.deleteComment(id);
	}
	
	@GetMapping("/group-name/{groupName}/{type}")
	public Page<Comment> readCommentListByGroupNameAndType(@PathVariable String groupName, @PathVariable String type,
			@RequestParam("curPage") int curPage, @RequestParam("perPage") int perPage) {
		PageRequest pageRequest = new PageRequest(curPage, perPage, new Sort(Direction.DESC, "id"));
		Page<Comment> commentList = commentService.readCommentListByGroupNameAndType(groupName, type, pageRequest);
		for (Comment comment : commentList) {
			User user = comment.getUser();
			user.setName(user.getName().substring(0, Math.max(2, user.getName().length() - 6)) + (user.getName().length() - 3 > 0 ? "**" : "*"));
			user.setEmail(null);
			user.setPhone(null);
			user.setPoint(0);
			user.setRegDate(null);
		}
		return commentList;
	}
	
	@GetMapping("/user/{userId}/type/{type}")
	public List<Comment> readCommentListByUserIdAndType(@PathVariable Long userId, @PathVariable String type) {
		return commentService.readCommentListByUserIdAndType(userId, type);
	}
	
}
