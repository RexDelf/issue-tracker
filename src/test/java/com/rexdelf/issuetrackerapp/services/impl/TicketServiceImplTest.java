package com.rexdelf.issuetrackerapp.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.rexdelf.issuetrackerapp.repositories.TicketRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {

  @Mock
  private TicketRepository ticketRepository;

  @InjectMocks
  private TicketServiceImpl ticketService;

  @Test
  void findAll() {
    assertDoesNotThrow(() -> ticketService.findAll());
  }
}