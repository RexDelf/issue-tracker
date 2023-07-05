package com.rexdelf.issuetrackerapp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false, length = 30)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(length = 100)
  private String name;

  @Column(length = 100)
  private String surname;
}
