package com.inviguardprojectbe.Services;

import com.inviguardprojectbe.Classes.IncomingOrders;
import org.springframework.stereotype.Service;
import com.inviguardprojectbe.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class IncomingOrdersService {

    private OrderRepository orderRepository;

    public IncomingOrdersService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public List<IncomingOrders> getOrderList(){
           return this.orderRepository.findAll();
    }

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

   public IncomingOrders getOrder(long id){
       for (IncomingOrders order : getOrderList()) {
           if (order.getId() == id) {
               return order;
           }
       }
       return null;
   }

    public void deleteOrder(long id){
        this.orderRepository.deleteById(id);
    }

    public IncomingOrders createOrders(IncomingOrders orders) {
       return this.orderRepository.save(orders);
    }


    public IncomingOrders updateOrders(Long id, IncomingOrders orders) {
        if (this.orderRepository.existsById(id)) {
            orders.setId(id);
            orders.setItemOrdered(orders.getItemOrdered());
            orders.setNumberOrdered(orders.getNumberOrdered());
            orders.setOrderedBy(orders.getOrderedBy());
            return this.orderRepository.save(orders);
        }
        return null;
    }


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

}
