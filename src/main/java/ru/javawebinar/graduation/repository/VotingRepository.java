package ru.javawebinar.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.graduation.model.Voting;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface VotingRepository extends JpaRepository<Voting, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Voting v WHERE v.id=:id")
    int delete(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Voting v WHERE v.user.id=:userId AND v.dateVoting=:currentDate")
    int deleteFromUser(@Param("userId") int userId, @Param("currentDate") LocalDate currentDate);

    @Override
    @Transactional
    Voting save(Voting voting);

    @Query("SELECT v from Voting v JOIN FETCH v.restaurant JOIN FETCH v.user WHERE v.id = :id AND v.user.id = :userId")
    Voting get(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT v from Voting v JOIN FETCH v.restaurant JOIN FETCH v.user WHERE v.dateVoting BETWEEN :startDate AND :endDate ORDER BY v.dateVoting DESC")
    List<Voting> getBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT v from Voting v JOIN FETCH v.restaurant JOIN FETCH v.user WHERE v.user.id = :userId AND v.dateVoting BETWEEN :startDate AND :endDate ORDER BY v.dateVoting DESC")
    List<Voting> getBetweenForUser(@Param("userId") int userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(name = "Voting.getVotingResult")
    List getVotingResult(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
