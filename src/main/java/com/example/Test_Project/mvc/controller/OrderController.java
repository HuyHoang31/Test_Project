//package com.example.Test_Project.mvc.controller;
//import com.example.Test_Project.mvc.entity.Orders;
//import com.example.Test_Project.mvc.service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//@Controller
//@RequestMapping("/orders")
//public class OrderController {
//    @Autowired
//    private OrderService orderService;
//
//    // Hiển thị danh sách đơn hàng
//    @GetMapping
//    public String getAllOrders(Model model) {
//        model.addAttribute("orders", orderService.getAllOrders());
//        return "order-list"; // Trả về trang danh sách đơn hàng
//    }
//
//    // Hiển thị chi tiết đơn hàng
//    @GetMapping("/{id}")
//    public String getOrderById(@PathVariable int id, Model model) {
//        model.addAttribute("order", orderService.getOrderById(id));
//        return "order-detail"; // Trả về trang chi tiết đơn hàng
//    }
//
//    // Hiển thị form thêm đơn hàng mới
//    @GetMapping("/new")
//    public String showCreateOrderForm(Model model) {
//        model.addAttribute("order", new Orders());
//        return "order-form"; // Trả về form thêm đơn hàng
//    }
//
//    // Lưu thông tin đơn hàng mới
//    @PostMapping("/save")
//    public String saveOrder(@ModelAttribute Orders order) {
//        orderService.saveOrder(order);
//        return "redirect:/orders"; // Chuyển hướng về danh sách đơn hàng
//    }
//
//    // Xóa đơn hàng
//    @GetMapping("/delete/{id}")
//    public String deleteOrder(@PathVariable int id) {
//        orderService.deleteOrder(id);
//        return "redirect:/orders"; // Chuyển hướng về danh sách đơn hàng sau khi xóa
//    }
//}
