package com.flowerhada.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name="comment")
@ToString
public class Comment {
	
	@Id
	@GeneratedValue
	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	private String title;
	
	@Getter @Setter
	private LocalDateTime regDate;
	
	@Getter @Setter
	private LocalDateTime updateDate;
	
	@Getter @Setter
	private String content;
	
	@Getter @Setter
	private String type;
	
	@Getter @Setter
	private String image;
	
	@Getter @Setter
	@Column(name="parent_id")
	private Long parentId;
	
	@Getter @Setter
	@Column(name="user_id")
	private Long userId;
	
	@Getter @Setter
	private String groupName;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name="parent_id")
	@Getter @Setter
	private List<Comment> replies;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="user_id", insertable = false, updatable = false)
	@Getter @Setter
	private User user;
	
}
