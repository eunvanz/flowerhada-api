package com.flowerhada.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flowerhada.domain.Comment;
import com.flowerhada.repository.CommentRepository;
import com.flowerhada.service.CommentService;
import com.flowerhada.util.Utils;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
	
	@Autowired CommentRepository commentRepository;

	@Override
	public Comment createComment(Comment comment) {
		comment.setRegDate(Utils.getZonedDateTimeNow("Asia/Seoul"));
		return commentRepository.save(comment);
	}

	@Override
	public Comment readComment(Long id) {
		return commentRepository.findOne(id);
	}

	@Override
	public Comment updateComment(Comment comment) {
		Comment oldComment = commentRepository.findOne(comment.getId());
		oldComment.setContent(comment.getContent());
		oldComment.setGroupName(comment.getGroupName());
		oldComment.setImage(comment.getImage());
		oldComment.setTitle(comment.getTitle());
		oldComment.setType(comment.getType());
		oldComment.setUpdateDate(Utils.getZonedDateTimeNow("Asia/Seoul"));
		oldComment.setUserId(comment.getUserId());
		return oldComment;
	}

	@Override
	public void deleteComment(Long id) {
		commentRepository.delete(id);
	}

	@Override
	public List<Comment> readCommentListByGroupName(String groupName) {
		return commentRepository.findByGroupName(groupName);
	}
	
	@Override
	public Page<Comment> readCommentListByGroupName(String groupName, Pageable pageable) {
		return commentRepository.findByGroupName(groupName, pageable);
	}

	@Override
	public Page<Comment> readCommentListByGroupNameAndType(String groupName, String type, Pageable pageable) {
		return commentRepository.findByGroupNameAndType(groupName, type, pageable);
	}

	@Override
	public List<Comment> readCommentListByUserIdAndType(Long userId, String type) {
		return commentRepository.findByUserIdAndType(userId, type);
	}

}
