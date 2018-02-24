package com.flowerhada.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flowerhada.domain.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
	public static final String FIND_AUTHORITY_BY_ID = "SELECT a.authorityName FROM Authority a WHERE a.id = :id";
	
	@Query(FIND_AUTHORITY_BY_ID)
	List<String> findAuthoritiesById(@Param("id") Long id);

	List<String> findById(Long id);
}
