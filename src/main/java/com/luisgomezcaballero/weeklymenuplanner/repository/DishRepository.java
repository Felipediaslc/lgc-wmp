package com.luisgomezcaballero.weeklymenuplanner.repository;

import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Dish repository. 
 * 
 * @author Luis
 *
 */
@Repository
public interface DishRepository extends JpaRepository<MenuDish, Integer> {

}