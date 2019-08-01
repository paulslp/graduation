package ru.javawebinar.graduation.repository;

import ru.javawebinar.graduation.model.Voting;
import ru.javawebinar.graduation.to.VotingResult;
import ru.javawebinar.graduation.to.VotingTo;

import java.time.LocalDate;
import java.util.List;

public interface VotingRepository {

    Voting save(VotingTo votingTo, int userId);

    boolean delete(int id);

    boolean deleteFromUser(int userId);

    Voting get(int id, int userId);

    List<Voting> getBetween(LocalDate startDate, LocalDate endDate);

    List<Voting> getBetweenForUser(int userId, LocalDate startDate, LocalDate endDate);

    List<VotingResult> getVotingResult(LocalDate startDate, LocalDate endDate);
}
