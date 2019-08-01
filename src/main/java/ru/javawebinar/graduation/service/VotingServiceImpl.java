package ru.javawebinar.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.graduation.model.Voting;
import ru.javawebinar.graduation.repository.VotingRepository;
import ru.javawebinar.graduation.to.VotingResult;
import ru.javawebinar.graduation.to.VotingTo;
import ru.javawebinar.graduation.util.exception.InvalidRequestTimeException;
import ru.javawebinar.graduation.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.graduation.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service("votingService")
public class VotingServiceImpl implements VotingService {

    @Autowired
    private final VotingRepository repository;

    public VotingServiceImpl(VotingRepository repository) {
        this.repository = repository;
    }


    @Override
    public Voting create(VotingTo votingTo, int userId) {
        return repository.save(votingTo, userId);
    }

    @Override
    public Voting update(VotingTo votingTo, int userId) throws InvalidRequestTimeException {
        if (LocalTime.now().isBefore(LocalTime.of(11, 0))) {
            repository.save(votingTo, userId);
            return repository.get(votingTo.getId(), userId);
        } else {
            throw new InvalidRequestTimeException("Record update is not possible after 11:00");
        }

    }

    @Override
    public void deleteFromUser(int userId) throws NotFoundException, InvalidRequestTimeException {
        if (LocalTime.now().isBefore(LocalTime.of(11, 0))) {
            checkNotFound(repository.deleteFromUser(userId), "userId = " + userId);
        } else {
            throw new InvalidRequestTimeException("Record delete is not possible after 11:00");
        }
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Voting get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public List<Voting> getBetween(LocalDate startDate, LocalDate endDate) throws NotFoundException {
        Assert.notNull(startDate, "startDate must not be null");
        Assert.notNull(endDate, "endDate  must not be null");
        return repository.getBetween(startDate, endDate);
    }

    @Override
    public List<Voting> getBetweenForUser(int userId, LocalDate startDate, LocalDate endDate) throws NotFoundException {
        Assert.notNull(userId, "userId must not be null");
        Assert.notNull(startDate, "startDate must not be null");
        Assert.notNull(endDate, "endDate  must not be null");
        return repository.getBetweenForUser(userId, startDate, endDate);
    }

    @Override
    public List<VotingResult> getVotingResult(LocalDate startDate, LocalDate endDate) throws NotFoundException {
        Assert.notNull(startDate, "startDate must not be null");
        Assert.notNull(endDate, "endDate  must not be null");
        return repository.getVotingResult(startDate, endDate);
    }

}