package com.inviguardprojectbe.services;

import com.inviguardprojectbe.models.OrderDto;
import com.inviguardprojectbe.models.entities.Item;
import com.inviguardprojectbe.models.entities.Order;
import com.inviguardprojectbe.models.entities.User;
import com.inviguardprojectbe.repositories.ItemRepository;
import com.inviguardprojectbe.repositories.OrderRepository;
import com.inviguardprojectbe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IncomingOrdersService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ItemsService itemsService;

    @Autowired
    public IncomingOrdersService(OrderRepository orderRepository, ItemRepository itemRepository,
                                 ItemsService itemsService, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.itemsService = itemsService;
    }

    public List<Order> getOrderList(){
        return this.orderRepository.findAll();
    }

    public OrderDto getOrderById(Long id){
        Order order = getOrder(id);
        return toDto(order);
    }

    private Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Order with id:" + id + " does not exist!"));
    }

    private OrderDto toDto(Order order) {
        if (order == null) {
            return null;
        }
        return new OrderDto(
                order.getId(),
                order.getUser(),
                order.getItem(),
                order.getNumberOrdered(),
                order.getOrderDate());
    }

    public void deleteOrder(Long id) {
        Order order = getOrder(id);
        Item item = order.getItem();
        item.setLeftInStock(item.getLeftInStock() + order.getNumberOrdered());
        itemRepository.save(item);
        orderRepository.deleteById(id);
    }

    public Order createOrder(Long userId, Long itemId, Integer numberOrdered) {
        // Find user and item
        User user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("User with id:" + userId + " does not exist!"));
        Item item = itemRepository.findById(itemId).orElseThrow(() ->
                new RuntimeException("Item with id:" + itemId + " does not exist!"));

        // Check if there is sufficient stock
        if (item.getLeftInStock() >= numberOrdered) {
            // Decrease item stock
            item.setLeftInStock(item.getLeftInStock() - numberOrdered);
            itemRepository.save(item);

            // Create new order
            Order order = new Order();
            order.setUser(user);
            order.setItem(item);
            order.setNumberOrdered(numberOrdered);
            order.setOrderDate(LocalDateTime.now());

            // Save the order and return the persisted entity
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Order cannot be created: Insufficient stock.");
        }
    }
}



  /*  public Optional<Order> updateOrder(Long id, Order order) {
        return orderRepository.findById(id).map(existingOrder -> {
            existingOrder.setItem(order.getItem());
            existingOrder.setNumberOrdered(order.getNumberOrdered());
            existingOrder.setOrderDate(order.getOrderDate());
            existingOrder.setUser(order.getUser());
            return orderRepository.save(existingOrder);
        });
    }*/

/*
    public Order createOrders(Order orders) {
        return this.orderRepository.save(orders);
    }
    public Order updateOrders(Long id, Order orders) {
        if (this.orderRepository.existsById(id)) {
            orders.setId(id);
            orders.setItemOrdered(orders.getItemOrdered());
            orders.setNumberOrdered(orders.getNumberOrdered());
            orders.setOrderedBy(orders.getOrderedBy());
            return this.orderRepository.save(orders);
        }
        return null;
    }

*/
   /* public IncomingOrders getOrder(long id){
        for(IncomingOrders order : this.orders){
            if(order.getId() == id){
                return order;
            }
        }
        return null;
    }

    public void deleteOrder(long id){
        List<IncomingOrders> order = this.orders;
        for(int i = 0; i < order.size(); i++){
            IncomingOrders order1 = order.get(i);
            if(order1.getId() == id){
               order.remove(i);
                return;
            }
        }
    }
*/

 /*  public Order getOrder(long id){
       for (Order order : getOrderList()) {
           if (order.getId() == id) {
               return order;
           }
       }
       return null;
   } */


 /*   public IncomingOrders updateOrders(Long id, IncomingOrders orders) {
        IncomingOrders current = this.getOrder(id);
        if (current == null) {
            return null;
        }
        current.setItemOrdered(orders.getItemOrdered());
        current.setNumberOrdered(orders.getNumberOrdered());
        current.setOrderedBy(orders.getOrderedBy());
        return current;
    } */


