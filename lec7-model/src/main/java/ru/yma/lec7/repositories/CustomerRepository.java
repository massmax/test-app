package ru.yma.lec7.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ru.yma.lec7.entities.Customer;

public interface CustomerRepository extends JpaRepository< Customer, Integer >
{
	@Query("select u from Customer u where u.name = :name")
    List< Customer > findByNameCustomer(@Param( "name" ) String name );
}
