package ru.geekbrains.summer.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.summer.market.model.OrderItem;
import ru.geekbrains.summer.market.repositories.OrderItemRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public List<OrderItem> findAllByOrder(Long id) {
        return orderItemRepository.findAllByOrder(id);
    }

}
