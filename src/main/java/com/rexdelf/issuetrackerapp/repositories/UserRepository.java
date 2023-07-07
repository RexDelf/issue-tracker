package com.rexdelf.issuetrackerapp.repositories;

import com.rexdelf.issuetrackerapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
