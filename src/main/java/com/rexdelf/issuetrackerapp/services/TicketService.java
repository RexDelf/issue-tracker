package com.rexdelf.issuetrackerapp.services;

import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.rexdelf.issuetrackerapp.models.Ticket;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface TicketService {
  List<Ticket> findAll();

  Optional<Ticket> findById(Long id);

  Ticket applyPatch(JsonPatch patch, Ticket targetTicket) throws JsonPatchException, IOException;

  Ticket save(Ticket ticket);
}
