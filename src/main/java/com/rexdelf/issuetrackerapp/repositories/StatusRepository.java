package com.rexdelf.issuetrackerapp.repositories;

import com.rexdelf.issuetrackerapp.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {

}
