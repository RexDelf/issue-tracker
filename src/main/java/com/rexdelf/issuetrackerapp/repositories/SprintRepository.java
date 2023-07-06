package com.rexdelf.issuetrackerapp.repositories;

import com.rexdelf.issuetrackerapp.models.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<Sprint, Long> {

}
