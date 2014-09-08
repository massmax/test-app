package ru.yma.lec7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.yma.lec7.entities.Order;

public interface OrderRepository extends JpaRepository< Order, Integer > {

}
