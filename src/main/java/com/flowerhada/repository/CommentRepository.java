package com.flowerhada.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.flowerhada.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	public List<Comment> findByGroupName(String groupName);
	public Page<Comment> findByGroupName(String groupName, Pageable pagable);
	public Page<Comment> findByGroupNameAndType(String groupName, String type, Pageable pagable);
	public List<Comment> findByUserIdAndType(Long userId, String type);
}
