package com.rexdelf.issuetrackerapp.mapper;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BaseMapper {
  default OffsetDateTime mapToOffsetDateTime(LocalDateTime dateTime){
    return Optional.ofNullable(dateTime)
        .map(dt -> OffsetDateTime.of(dt, ZoneOffset.UTC))
        .orElse(null);
  }
}
