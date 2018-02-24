package com.flowerhada.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name="main_banner")
@ToString
public class MainBanner {

	@Id
	@GeneratedValue
	@Getter @Setter
	private Long id;
	
	@NotNull
	@Getter @Setter
	private String img;
	
	@NotNull
	@Getter @Setter
	private String title;
	
	@NotNull
	@Getter @Setter
	private String shortTitle;
	
	@Getter @Setter
	private String detail;
	
	@Getter @Setter
	private String link;
	
	@Getter @Setter
	private boolean activated;
	
	@JsonIgnore
	@Transient
	@Getter @Setter
	private MultipartFile imgFile;
	
}
