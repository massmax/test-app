package ru.yma.lec7.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ru.yma.lec7.entities.Warehouse;

public interface WarehouseRepository extends JpaRepository< Warehouse, Integer >
{
	@Query("select u from Warehouse u where u.name = :name")
    List< Warehouse > findByNameWarehouse(@Param( "name" ) String name );	
}
