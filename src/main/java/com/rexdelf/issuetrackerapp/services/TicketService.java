package com.rexdelf.issuetrackerapp.services;

import com.github.fge.jsonpatch.JsonPatchException;
import com.rexdelf.issuetrackerapp.dto.JsonPatchWrapper;
import com.rexdelf.issuetrackerapp.dto.TicketPatchDto;
import com.rexdelf.issuetrackerapp.models.Ticket;

import java.io.IOException;
import java.util.List;

public interface TicketService {
  List<Ticket> findAll();

  Ticket findById(Long id);

  Ticket applyPatch(TicketPatchDto ticketPatchDto, Long id);

  Ticket applyPatch(JsonPatchWrapper patch, Long id) throws JsonPatchException, IOException;

  Ticket save(Ticket ticket);
}
