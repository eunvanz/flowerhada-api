package com.flowerhada.domain;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name="lesson")
@ToString
public class Lesson {
	
	@Id
	@GeneratedValue
	@Getter @Setter
	private Long id;
	
	@NotNull
	@Getter @Setter
	private String title;
	
	@NotNull
	@Getter @Setter
	private String detail;
	
	@NotNull
	@Getter @Setter
	private String mainCategory;
	
	@Getter @Setter
	private String subCategory;
	
	@Getter @Setter
	private boolean oneday;
	
	@NotNull
	@Getter @Setter
	private String content;
	
	@Getter @Setter
	private String location;
	
	@Getter @Setter
	private String postCode;
	
	@Getter @Setter
	private String address;
	
	@Getter @Setter
	private String restAddress;
	
	@Getter @Setter
	private String longitude;
	
	@Getter @Setter
	private String latitude;
	
	@NotNull
	@Getter @Setter
	private int maxParty;
	
	@NotNull
	@Getter @Setter
	private int currParty;
	
	@NotNull
	@Getter @Setter
	private String titleImg;
	
	@Getter @Setter
	private int price;
	
	@Getter @Setter
	private int discountedPrice;
	
	@Getter @Setter
	private LocalDateTime regDateTime;
	
	@Getter @Setter
	private Date lessonDate;
	
	@Getter @Setter
	private Time startTime;
	
	@Getter @Setter
	private Time endTime;
	
	@Getter @Setter
	private boolean expired;
	
	@Getter @Setter
	private boolean activated;
	
	@Getter @Setter
	private String weekType;
	
	@Getter @Setter
	private String weekLong;
	
	@Getter @Setter
	private String groupName;
	
	@Getter @Setter
	private int level;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="lesson_id")
	@Getter @Setter
	private List<LessonDay> lessonDays;
	
	public void addLessonDays(LessonDay lessonDay) {
		if (this.lessonDays == null) {
			lessonDays = new ArrayList<LessonDay>();
		}
		lessonDays.add(lessonDay);
	}
	
	@Getter @Setter
	@Column(name="tutor_id")
	private Long tutorId;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="tutor_id", insertable = false, updatable = false)
	@Getter @Setter
	private Tutor tutor;
	
}
