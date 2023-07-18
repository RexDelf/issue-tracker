package com.rexdelf.issuetrackerapp.mapper;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BaseMapper {
  default OffsetDateTime mapToOffsetDateTime(LocalDateTime dateTime){
    return OffsetDateTime.of(dateTime, ZoneOffset.UTC);
  }
}
