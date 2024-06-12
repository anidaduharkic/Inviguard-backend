package com.inviguardprojectbe.controllers;

import com.inviguardprojectbe.models.OrderDto;
import com.inviguardprojectbe.models.entities.Order;
import com.inviguardprojectbe.services.IncomingOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class IncomingOrdersController {

    private final IncomingOrdersService incomingOrdersService;

    @Autowired
    public IncomingOrdersController(IncomingOrdersService incomingOrdersService) {
        this.incomingOrdersService = incomingOrdersService;
    }

    @GetMapping("list")
    public List<Order> getOrders() {
        return incomingOrdersService.getOrderList();
    }

    @GetMapping("{id}")
    public OrderDto getOrder(@PathVariable Long id) {
        return incomingOrdersService.getOrderById(id);
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable Long id) {
        incomingOrdersService.deleteOrder(id);
    }

    @PostMapping()
    @Validated
    public Order createOrder(@RequestParam Long userId, @RequestParam Long itemId, @RequestParam Integer numberOrdered) {
        return incomingOrdersService.createOrder(userId, itemId, numberOrdered);
    }

  /*  @PutMapping("{id}")
    @Validated
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
        return incomingOrdersService.updateOrder(id, order);
    }*/
}



