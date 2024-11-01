package com.example.Test_Project.mvc.entity;



import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class    Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrder; // Khóa chính

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Quan hệ với người dùng

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment; // Quan hệ với thanh toán

    @OneToMany(mappedBy = "order")
    private List<Ticket> tickets; // Quan hệ với vé

    @Temporal(TemporalType.DATE)
    private Date date; // Ngày đặt hàng

    @Column(name = "totalPrice", nullable = false)
    private double totalPrice; // Tổng giá

    @Column(name = "quantity", nullable = false)
    private int quantity; // Số lượng vé

    // Getters and Setters

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
