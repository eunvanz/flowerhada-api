package com.flowerhada.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

import lombok.Data;

@Data
@Entity
public class Inquiry {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="user_id")
	private Long userId;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="user_id", insertable = false, updatable = false)
	private User user;
	
	private String title;
	
	private String category;
	
	private String content;
	
	@Column(name="parent_id")
	private Long parentId;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name="parent_id")
	private List<Inquiry> answers;
	
	public void addAnswer(Inquiry inquiry) {
		if (this.answers == null) {
			answers = new ArrayList<Inquiry>();
		}
		answers.add(inquiry);
	}
	
	private LocalDateTime updateDate;
	
}
