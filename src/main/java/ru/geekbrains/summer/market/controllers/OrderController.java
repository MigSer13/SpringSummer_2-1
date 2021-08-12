package ru.geekbrains.summer.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.summer.market.dto.OrderDto;
import ru.geekbrains.summer.market.dto.ProductDto;
import ru.geekbrains.summer.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.summer.market.model.Order;
import ru.geekbrains.summer.market.model.OrderItem;
import ru.geekbrains.summer.market.model.User;
import ru.geekbrains.summer.market.services.OrderService;
import ru.geekbrains.summer.market.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;


    @PostMapping
    public void createOrder(Principal principal, @RequestParam String address, @RequestParam String phone) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to create order. User not found"));;
        orderService.createOrder(user, address, phone);
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.findAll().stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public List<ProductDto> getProductsDtoByOrder(@PathVariable Long id){
        Order order  = orderService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        List<ProductDto> productDtoList = order.getItems().stream().map(orderItem -> new ProductDto(orderItem.getProduct())).collect(Collectors.toList());
        return productDtoList;
    }
}
