package ru.yma.lec7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yma.lec7.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository< OrderItem, Integer >
{

}

