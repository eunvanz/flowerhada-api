package com.flowerhada.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name="AUTHORITY")
@ToString
@IdClass(AuthorityId.class)
public class Authority {
	@Id
	@NotNull
	@Getter @Setter
	private Long id;
	@Id
	@NotNull
	@Getter @Setter
	private String authorityName;
}
