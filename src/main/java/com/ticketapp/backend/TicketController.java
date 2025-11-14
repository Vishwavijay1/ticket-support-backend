package com.ticketapp.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tickets") // API URL: http://localhost:8080/tickets
public class TicketController {

    @Autowired
    private TicketRepository repository;

    // 1. CREATE a Ticket (POST)
    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        ticket.setStatus("OPEN");
        return repository.save(ticket);
    }

    // 2. GET All Tickets (GET)
    @GetMapping
    public List<Ticket> getAllTickets() {
        return repository.findAll();
    }

    // 3. GET One Ticket (GET /tickets/1)
    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found!"));
    }

    // 4. UPDATE a Ticket (PUT /tickets/1)
    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable Long id, @RequestBody Ticket ticketDetails) {
        Ticket ticket = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found!"));

        ticket.setTitle(ticketDetails.getTitle());
        ticket.setDescription(ticketDetails.getDescription());
        ticket.setStatus(ticketDetails.getStatus());

        return repository.save(ticket);
    }

    // 5. DELETE a Ticket (DELETE /tickets/1)
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        repository.deleteById(id);
    }
}