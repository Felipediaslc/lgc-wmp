package com.luisgomezcaballero.weeklymenuplanner.repository;

import com.luisgomezcaballero.weeklymenuplanner.model.entity.MenuHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Menu repository.
 * @author Luis
 *
 */
@Repository
public interface MenuRepository extends JpaRepository<MenuHeader, Integer> {

}