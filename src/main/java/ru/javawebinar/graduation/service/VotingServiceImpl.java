package ru.javawebinar.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.graduation.model.Voting;
import ru.javawebinar.graduation.repository.MenuRepository;
import ru.javawebinar.graduation.repository.RestaurantRepository;
import ru.javawebinar.graduation.repository.UserRepository;
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

    @Autowired
    private final UserRepository crudUserRepository;

    @Autowired
    private final RestaurantRepository crudRestaurantRepository;

    @Autowired
    private final MenuRepository crudMenuRepository;

    public VotingServiceImpl(VotingRepository repository, UserRepository crudUserRepository, RestaurantRepository crudRestaurantRepository, MenuRepository crudMenuRepository) {
        this.repository = repository;
        this.crudUserRepository = crudUserRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudMenuRepository = crudMenuRepository;
    }

    @Override
    public Voting create(VotingTo votingTo, int userId) {
        return save(votingTo, userId);
    }

    @Override
    public Voting update(VotingTo votingTo, int userId) throws InvalidRequestTimeException {
        if (LocalTime.now().isBefore(LocalTime.of(11, 0))) {
            save(votingTo, userId);
            return repository.get(votingTo.getId(), userId);
        } else {
            throw new InvalidRequestTimeException("Record update is not possible after 11:00");
        }

    }

    public Voting save(VotingTo votingTo, int userId) {
        Voting voting = new Voting();
        voting.setId(votingTo.getId());
        voting.setUser(crudUserRepository.getOne(userId));
        voting.setRestaurant(crudRestaurantRepository.getOne(votingTo.getRestaurantId()));
        voting.setDateVoting(votingTo.getDateVoting());

        if ((!voting.isNew() && repository.get(voting.getId(), userId) == null) ||
                    //if no restaurant has found a menu for the voting date
                    (crudMenuRepository.getBetweenForRestaurant(voting.getRestaurant().getId(), voting.getDateVoting(), voting.getDateVoting()).size() == 0)) {
            return null;
        }
        return repository.save(voting);
    }

    @Override
    public void deleteFromUser(int userId) throws NotFoundException, InvalidRequestTimeException {
        if (LocalTime.now().isBefore(LocalTime.of(11, 0))) {
            checkNotFound(repository.deleteFromUser(userId, LocalDate.now()) != 0, "userId = " + userId);
        } else {
            throw new InvalidRequestTimeException("Record delete is not possible after 11:00");
        }
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id) != 0, id);
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