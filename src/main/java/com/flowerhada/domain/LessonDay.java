package com.flowerhada.domain;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name="lesson_day")
@ToString
public class LessonDay {
	@Id
	@GeneratedValue
	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	private String day;
	
	@Getter @Setter
	private Time startTime;
	
	@Getter @Setter
	private Time endTime;
	
	@Column(name="lesson_id")
	@Getter @Setter
	private Long lessonId;
}
