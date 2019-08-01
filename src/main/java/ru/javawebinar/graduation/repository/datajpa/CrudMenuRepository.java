package ru.javawebinar.graduation.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.graduation.model.Menu;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Menu m WHERE m.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Menu save(Menu menu);

    @Query("SELECT m from Menu m JOIN FETCH m.restaurant  JOIN FETCH m.meal  WHERE m.id = :id")
    Menu get(@Param("id") int id);

    @Query("SELECT m from Menu m JOIN FETCH m.restaurant  JOIN FETCH m.meal WHERE m.dateMenu BETWEEN :startDate AND :endDate ORDER BY m.dateMenu,m.restaurant.name DESC")
    List<Menu> getBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


    @Query("SELECT m from Menu m JOIN FETCH m.restaurant  JOIN FETCH m.meal WHERE m.restaurant.id = :restaurantId AND m.dateMenu BETWEEN :startDate AND :endDate ORDER BY m.dateMenu DESC")
    List<Menu> getBetweenForRestaurant(@Param("restaurantId") int restaurantId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
