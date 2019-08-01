package ru.javawebinar.graduation.service;

import ru.javawebinar.graduation.model.Voting;
import ru.javawebinar.graduation.to.VotingResult;
import ru.javawebinar.graduation.to.VotingTo;
import ru.javawebinar.graduation.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface VotingService {
    Voting create(VotingTo votingTo, int userId);

    Voting update(VotingTo votingTo, int userId);

    void delete(int id) throws NotFoundException;

    void deleteFromUser(int userId);

    Voting get(int id, int userId) throws NotFoundException;

    List<Voting> getBetween(LocalDate startDate, LocalDate endDate);

    List<Voting> getBetweenForUser(int userId, LocalDate startDate, LocalDate endDate);

    List<VotingResult> getVotingResult(LocalDate startDate, LocalDate endDate);
}
