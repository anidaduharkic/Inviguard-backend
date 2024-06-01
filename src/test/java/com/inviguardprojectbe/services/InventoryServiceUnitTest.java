package com.inviguardprojectbe.services;

import com.inviguardprojectbe.Classes.IncomingOrders;
import com.inviguardprojectbe.Services.IncomingOrdersService;
import com.inviguardprojectbe.data.InventoryTest;
import com.inviguardprojectbe.repositories.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class InventoryServiceUnitTest {


    @MockBean
    private OrderRepository orderRepository;


    @TestConfiguration
    static class InventoryServiceTestConfiguration {

        @Bean
        @Primary
        public IncomingOrdersService incomingOrdersService(OrderRepository orderRepository) {
            return new IncomingOrdersService(orderRepository);
        }
    }

    @Autowired
    private IncomingOrdersService incomingOrdersService;


    @Test
    public void givenOrders_whenGetOrdersList_thenReturnListOfOrders() {

        // arrange

        Mockito.when(orderRepository.findAll())
                .thenReturn(List.of(InventoryTest.incomingOrders1(), InventoryTest.incomingOrders2()));

        //act
        List<IncomingOrders> result = incomingOrdersService.getOrderList();

        //assert
        assertThat(result).hasSize(2);
    }


    @Test
    public void givenNoOrders_whenGetOrdersList_thenReturnEmptyList() {

        // arrange
        Mockito.when(orderRepository.findAll())
                .thenReturn(Collections.EMPTY_LIST);

        //act
        List<IncomingOrders> result = incomingOrdersService.getOrderList();

        //assert
        assertThat(result).isEmpty();
    }

    @Test
    public void givenTwoOrders_whenCreateOrder_thenOrderIsCreated() {

        // arrange
        IncomingOrders inputOrder = InventoryTest.incomingOrders1();
        inputOrder.setId(null);

        IncomingOrders outputOrder = InventoryTest.incomingOrders1();

        Mockito.when(orderRepository.save(any(IncomingOrders.class))).
                thenReturn(outputOrder);

        // act
        IncomingOrders result = incomingOrdersService.createOrders(inputOrder);

        // assert
        assertThat(result).isNotNull();
        assertThat(result.getOrderedBy()).isEqualTo(inputOrder.getOrderedBy());
        assertThat(result.getId()).isNotNull();
    }

    @Test
    public void givenOrderId_whenGetOrder_thenReturnOrder() {
        //arrange
        IncomingOrders order = InventoryTest.incomingOrders1();

        Mockito.when(orderRepository.findAll())
                .thenReturn(List.of(order));

        //act
        IncomingOrders result = incomingOrdersService.getOrder(order.getId());

        //assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(order.getId());
    }

    @Test
    public void givenInvalidOrderId_whenGetOrder_thenReturnNull() {

        //arrange
        Mockito.when(orderRepository.findAll())
                .thenReturn(Collections.emptyList());

        //act
        IncomingOrders result = incomingOrdersService.getOrder(1L);

        //assert
        assertThat(result).isNull();
    }

    @Test
    public void givenOrderId_whenDeleteOrder_thenOrderIsDeleted() {
        long orderId = 1L;

        Mockito.doNothing().when(orderRepository).deleteById(orderId);

        incomingOrdersService.deleteOrder(orderId);

        Mockito.verify(orderRepository, Mockito.times(1)).deleteById(orderId);
    }

    @Test
    public void givenValidIdAndOrder_whenUpdateOrder_thenOrderIsUpdated() {
        //arrange
        long orderId = 1L;
        IncomingOrders inputOrder = InventoryTest.incomingOrders1();
        inputOrder.setId(null);

        IncomingOrders updatedOrder = InventoryTest.incomingOrders1();

        Mockito.when(orderRepository.existsById(orderId)).thenReturn(true);
        Mockito.when(orderRepository.save(any(IncomingOrders.class))).thenReturn(updatedOrder);

        //act
        IncomingOrders result = incomingOrdersService.updateOrders(orderId, inputOrder);

        //assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(orderId);
        assertThat(result.getOrderedBy()).isEqualTo(inputOrder.getOrderedBy());
    }

    @Test
    public void givenInvalidId_whenUpdateOrder_thenReturnNull() {

        //arrange
        long orderId = 1L;
        IncomingOrders inputOrder = InventoryTest.incomingOrders1();

        Mockito.when(orderRepository.existsById(orderId)).thenReturn(false);

        //act
        IncomingOrders result = incomingOrdersService.updateOrders(orderId, inputOrder);

        //assert
        assertThat(result).isNull();
    }
}
