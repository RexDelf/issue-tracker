package com.rexdelf.issuetrackerapp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IssueTrackerAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(IssueTrackerAppApplication.class, args);
  }

}
