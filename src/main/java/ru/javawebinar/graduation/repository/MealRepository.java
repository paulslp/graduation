package ru.javawebinar.graduation.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.graduation.model.Meal;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface MealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Meal save(Meal meal);

    @Override
    Optional<Meal> findById(Integer id);

    @Override
    List<Meal> findAll(Sort sort);

    Meal getByName(String name);

}
