package ru.javawebinar.graduation.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.graduation.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Restaurant save(Restaurant restaurant);

    @Override
    Optional<Restaurant> findById(Integer id);

    @Override
    List<Restaurant> findAll(Sort sort);

    Restaurant getByName(String name);

    @Query(value = "SELECT r.id,r.name FROM menu m LEFT JOIN restaurants r ON m.restaurant_id = r.id WHERE m.date_menu BETWEEN :startDate AND :endDate GROUP BY r.id,r.name ORDER BY r.name ASC", nativeQuery = true)
    List<Restaurant> getRestaurantListWithMenuOnDate(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
