package ru.yma.lec7.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ru.yma.lec7.entities.Product;

public interface ProductRepository extends JpaRepository< Product, Integer >
{
	@Query( "select u from Product u where LOWER(u.name) = LOWER(:name)" )
    public List< Product > findByNameProduct(@Param( "name" ) String name );
}
