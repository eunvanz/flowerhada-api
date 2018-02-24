package com.flowerhada.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.flowerhada.domain.Comment;

public interface CommentService {
	public Comment createComment(Comment comment);
	public Comment readComment(Long id);
	public Comment updateComment(Comment comment);
	public void deleteComment(Long id);
	public List<Comment> readCommentListByGroupName(String groupName);
	public Page<Comment> readCommentListByGroupName(String groupName, Pageable pageable);
	public Page<Comment> readCommentListByGroupNameAndType(String groupName, String type, Pageable pageable);
	public List<Comment> readCommentListByUserIdAndType(Long userId, String type);
}