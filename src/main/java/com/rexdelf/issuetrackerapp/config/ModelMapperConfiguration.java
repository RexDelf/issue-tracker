package com.rexdelf.issuetrackerapp.config;

import com.rexdelf.issuetrackerapp.dto.TicketDto;
import com.rexdelf.issuetrackerapp.models.Ticket;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.addConverter(ticketToTicketDtoConverter());
    return modelMapper;
  }

  @Bean
  public Converter<Ticket, TicketDto> ticketToTicketDtoConverter() {
    return new AbstractConverter<>() {
      @Override
      protected TicketDto convert(Ticket source) {
        TicketDto destination = new TicketDto();

        destination.setId(source.getId());
        destination.setTitle(source.getTitle());
        destination.setDescription(source.getDescription());
        destination.setReporterName(source.getReporter().getName());
        destination.setReporterSurname(source.getReporter().getSurname());
        destination.setAssigneeName(source.getAssignee().getName());
        destination.setAssigneeSurname(source.getAssignee().getSurname());
        destination.setStatus(source.getStatus().getName());
        destination.setCreatedAt(source.getCreatedAt());
        destination.setPriority(source.getPriority().getName());

        return destination;
      }
    };
  }
}