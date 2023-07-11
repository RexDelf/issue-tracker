package com.rexdelf.issuetrackerapp.services;

import com.rexdelf.issuetrackerapp.models.Ticket;

import java.util.List;

public interface TicketService {
  List<Ticket> findAll();

  Ticket save(Ticket ticket);
}
