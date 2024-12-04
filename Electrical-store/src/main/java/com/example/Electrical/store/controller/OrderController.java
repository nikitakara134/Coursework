package com.example.Electrical.store.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Electrical.store.dto.OrderDto;
import com.example.Electrical.store.exception.CartException;
import com.example.Electrical.store.exception.CurrentUserServiceException;
import com.example.Electrical.store.exception.OrderException;
import com.example.Electrical.store.exception.UserException;
import com.example.Electrical.store.model.Order;
import com.example.Electrical.store.service.OrderService;


/**
 * RestController for Order functioning.
 */

@CrossOrigin("http://localhost:3000/")
@RestController
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<String> makeOrder(@RequestParam String authenticationToken, @RequestBody OrderDto orderDto)
            throws UserException, CurrentUserServiceException, CartException {

        logger.info("called makeOrder method of orderService to create a new order");
        orderService.makeOrder(orderDto, authenticationToken);
        logger.info("a new order created successfully");

        return new ResponseEntity<>("Order Created", HttpStatus.ACCEPTED);

    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<List<Order>> listOrdersByUserId(@RequestParam String authenticationToken,
                                                          @PathVariable("id") Integer userId) throws OrderException, UserException, CurrentUserServiceException {
        logger.info("called listOrdersByUserId method of orderService to fetch orders by user id");
        List<Order> orders = orderService.listOrdersByUserId(userId, authenticationToken);
        logger.info("orders fetched successfully for given user id");

        return new ResponseEntity<>(orders, HttpStatus.OK);

    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> listAllOrders(@RequestParam String authenticationToken)
            throws OrderException, UserException, CurrentUserServiceException {
        logger.info("called listAllOrders method of orderService to fetch all orders available in order table");
        List<Order> orders = orderService.listAllOrders(authenticationToken);
        logger.info("orders fetched successfully");

        return new ResponseEntity<>(orders, HttpStatus.OK);

    }

    @PutMapping("/updateorder")
    public ResponseEntity<String> updateOrder(@RequestParam String authenticationToken, @RequestBody OrderDto orderDto) throws CurrentUserServiceException, OrderException {

        logger.info("called updateOrder method of orderService to update order");
        orderService.updateOrder(orderDto,authenticationToken);
        logger.info("order updated successfully");

        return new ResponseEntity<>("Order Updated", HttpStatus.ACCEPTED);
    }

}
