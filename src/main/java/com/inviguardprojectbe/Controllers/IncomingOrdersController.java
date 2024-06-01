package com.inviguardprojectbe.Controllers;

import com.inviguardprojectbe.Classes.IncomingOrders;
import com.inviguardprojectbe.Services.IncomingOrdersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("orders")
@RestController

public class IncomingOrdersController {


    private final IncomingOrdersService incomingOrders;

    public IncomingOrdersController(IncomingOrdersService incomingOrders){
        this.incomingOrders = incomingOrders;
    }

    @GetMapping("list")
    public List<IncomingOrders> getOrders() {
        return this.incomingOrders.getOrderList();
    }

    @GetMapping("{id}")
    public IncomingOrders getOrder(@PathVariable Long id){
     return this.incomingOrders.getOrder(id);
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable Long id){
        this.incomingOrders.deleteOrder(id);
    }

    @PostMapping()
    public IncomingOrders createOrder(@RequestBody IncomingOrders order){
        return this.incomingOrders.createOrders(order);
    }

    @PutMapping("{id}")
    public IncomingOrders updateOrders(@PathVariable Long id, @RequestBody IncomingOrders order){
        return this.incomingOrders.updateOrders(id, order);
    }
}
