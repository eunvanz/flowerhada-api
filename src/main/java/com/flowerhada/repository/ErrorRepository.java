package com.flowerhada.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.flowerhada.domain.Error;

public interface ErrorRepository extends JpaRepository<Error, Long> {

}
