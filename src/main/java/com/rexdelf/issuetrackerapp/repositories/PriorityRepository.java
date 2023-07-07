package com.rexdelf.issuetrackerapp.repositories;

import com.rexdelf.issuetrackerapp.models.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, Long> {

}
