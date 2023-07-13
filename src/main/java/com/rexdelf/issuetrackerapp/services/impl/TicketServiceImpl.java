package com.rexdelf.issuetrackerapp.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.rexdelf.issuetrackerapp.models.Ticket;
import com.rexdelf.issuetrackerapp.repositories.TicketRepository;
import com.rexdelf.issuetrackerapp.services.TicketService;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

  private final TicketRepository ticketRepository;

  private final ObjectMapper objectMapper;

  public Ticket applyPatch(JsonPatch patch, Ticket targetTicket) throws JsonPatchException, IOException {
    JsonNode patched = patch.apply(objectMapper.convertValue(targetTicket, JsonNode.class));
    return objectMapper.treeToValue(patched, Ticket.class);
  }

  public Ticket save(Ticket ticket){
    return ticketRepository.save(ticket);
  }

  public Optional<Ticket> findById(Long id) {
    return ticketRepository.findById(id);
  }

  public List<Ticket> findAll() {
    return ticketRepository.findAll();
  }
}
