package com.inviguardprojectbe;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IncomingOrdersService {

    private long id = 0;

    private List<IncomingOrders> orders = new ArrayList<>();

    public IncomingOrdersService(){
        this.orders.add(new IncomingOrders(this.id++,"Laptop Computer", 50, "Inventory Manager"));
        this.orders.add(new IncomingOrders(this.id++,"Packing Boxes", 100, "Accounting Manager"));
        this.orders.add(new IncomingOrders(this.id++,"Office Chairs", 10, "Warehouse Supervisor"));
    }

    public List<IncomingOrders> getOrderList(){
        return this.orders;
    }

   public IncomingOrders getOrder(long id){
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

    public IncomingOrders createOrders(IncomingOrders orders) {
        orders.setId(this.id++);
        this.orders.add(orders);
        return orders;
    }

    public IncomingOrders updateOrders(Long id, IncomingOrders orders) {
        IncomingOrders current = this.getOrder(id);
        if (current == null) {
            return null;
        }
        current.setItemOrdered(orders.getItemOrdered());
        current.setNumberOrdered(orders.getNumberOrdered());
        current.setOrderedBy(orders.getOrderedBy());
        return current;
    }
}
