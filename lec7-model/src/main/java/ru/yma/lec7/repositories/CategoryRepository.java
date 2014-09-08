package ru.yma.lec7.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ru.yma.lec7.entities.Category;

public interface CategoryRepository extends JpaRepository< Category, Integer >
{
	@Query("select u from Category u where u.name = :name")
    List< Category > findByNameCategory(@Param( "name" ) String name );
}
