package com.example.Test_Project.mvc.service;
import com.example.Test_Project.mvc.entity.ShowTime;
import com.example.Test_Project.mvc.entity.Ticket;
import com.example.Test_Project.mvc.entity.TicketTypes;
import com.example.Test_Project.mvc.repository.ShowtimeRepository;
import com.example.Test_Project.mvc.repository.TicketTypesRepository;
import com.example.Test_Project.mvc.repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketsService {
    @Autowired
    private TicketTypesRepository ticketTypesRepository;
    @Autowired
    private ShowtimeRepository  showtimeRepository;
    @Autowired
    private TicketsRepository ticketsRepository;

    public List<Ticket> getAllTickets() {
        return ticketsRepository.findAll();
    }

    public Ticket getTicketById(int id) {
        return ticketsRepository.findById(id).orElse(null);
    }

    public void saveTicket(Ticket ticket) {
        ticketsRepository.save(ticket);
    }

    public void deleteTicket(int id) {
        ticketsRepository.deleteById(id);
    }
    public void calculateTotalPrice(Ticket ticket) {
        double pricePerTicket = ticket.getTicketType().getPrice();
        int quantity = ticket.getQuantity();
        ticket.setTotalPrice(pricePerTicket * quantity);
    }

    // Lấy danh sách các suất chiếu khả dụng
    public List<ShowTime> getAvailableShowTimes() {
        return showtimeRepository.findAll(); // Thay đổi điều kiện nếu cần (ví dụ, chỉ suất chiếu còn trống)
    }

    // Lấy danh sách các loại vé khả dụng
    public List<TicketTypes> getTicketTypes() {
        return ticketTypesRepository.findAll();
    }
}

