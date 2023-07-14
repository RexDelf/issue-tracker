package com.rexdelf.issuetrackerapp.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.rexdelf.issuetrackerapp.dto.JsonPatchWrapper;
import com.rexdelf.issuetrackerapp.dto.TicketPatchDto;
import com.rexdelf.issuetrackerapp.mapper.TicketMapper;
import com.rexdelf.issuetrackerapp.models.Ticket;
import com.rexdelf.issuetrackerapp.repositories.TicketRepository;
import com.rexdelf.issuetrackerapp.services.TicketService;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

  private final TicketRepository ticketRepository;

  private final TicketMapper mapper;

  private final ObjectMapper objectMapper;

  public Ticket applyPatch(Ticket targetTicket, TicketPatchDto ticketPatchDto){
    Ticket patchedTicket = mapper.patchTicket(targetTicket, ticketPatchDto);

    return ticketRepository.save(patchedTicket);
  }

  public Ticket applyPatch(JsonPatchWrapper patch, Ticket targetTicket) throws JsonPatchException, IOException {
    JsonNode operationsNode = objectMapper.valueToTree(patch.getPatchArray());

    JsonPatch patchJson = JsonPatch.fromJson(operationsNode);

    JsonNode patched = patchJson.apply(objectMapper.convertValue(targetTicket, JsonNode.class));
    Ticket patchedTicket = objectMapper.treeToValue(patched, Ticket.class);

    return ticketRepository.save(patchedTicket);
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
