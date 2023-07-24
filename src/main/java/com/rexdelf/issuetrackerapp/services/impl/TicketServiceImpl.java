package com.rexdelf.issuetrackerapp.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.rexdelf.issuetrackerapp.dto.JsonPatchWrapper;
import com.rexdelf.issuetrackerapp.dto.TicketPatchDto;
import com.rexdelf.issuetrackerapp.exceptions.NotFoundException;
import com.rexdelf.issuetrackerapp.mapper.TicketMapper;
import com.rexdelf.issuetrackerapp.models.Ticket;
import com.rexdelf.issuetrackerapp.repositories.TicketRepository;
import com.rexdelf.issuetrackerapp.services.TicketService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

  private final TicketRepository ticketRepository;

  private final TicketMapper mapper;

  private final ObjectMapper objectMapper;

  @Override
  public Ticket applyPatch(TicketPatchDto ticketPatchDto, Long id){
    Ticket targetTicket = findById(id);

    Ticket patchedTicket = mapper.patchTicket(targetTicket, ticketPatchDto);

    return ticketRepository.save(patchedTicket);
  }

  @Override
  public Ticket applyPatch(JsonPatchWrapper patch, Long id) throws JsonPatchException, IOException {
    Ticket targetTicket = findById(id);

    JsonNode operationsNode = objectMapper.valueToTree(patch.getItems());

    JsonPatch patchJson = JsonPatch.fromJson(operationsNode);

    JsonNode patched = patchJson.apply(objectMapper.convertValue(targetTicket, JsonNode.class));
    Ticket patchedTicket = objectMapper.treeToValue(patched, Ticket.class);

    return ticketRepository.save(patchedTicket);
  }

  @Override
  public Ticket save(Ticket ticket){
    return ticketRepository.save(ticket);
  }

  @Override
  public Ticket findById(Long id) {
    return ticketRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Ticket not found for id: " + id));
  }

  @Override
  public List<Ticket> findAll() {
    return ticketRepository.findAll();
  }
}
