package ru.javawebinar.graduation.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.graduation.model.Voting;
import ru.javawebinar.graduation.repository.VotingRepository;
import ru.javawebinar.graduation.to.VotingResult;
import ru.javawebinar.graduation.to.VotingTo;

import java.time.LocalDate;
import java.util.List;

@Repository
class DataJpaVotingRepositoryImpl implements VotingRepository {

    @Autowired
    private CrudVotingRepository crudRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Autowired
    private CrudMenuRepository crudMenuRepository;

    @Override
    public Voting save(VotingTo votingTo, int userId) {
        Voting voting = new Voting();
        voting.setId(votingTo.getId());
        voting.setUser(crudUserRepository.getOne(userId));
        voting.setRestaurant(crudRestaurantRepository.getOne(votingTo.getRestaurantId()));
        voting.setDateVoting(votingTo.getDateVoting());

        if ((!voting.isNew() && crudRepository.get(voting.getId(), userId) == null) ||
                    //if no restaurant has found a menu for the voting date
                    (crudMenuRepository.getBetweenForRestaurant(voting.getRestaurant().getId(), voting.getDateVoting(), voting.getDateVoting()).size() == 0)) {
            return null;
        }
        return crudRepository.save(voting);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public boolean deleteFromUser(int userId) {
        return crudRepository.deleteFromUser(userId, LocalDate.now()) != 0;
    }

    @Override
    public Voting get(int id, int userId) {
        return crudRepository.get(id, userId);
    }

    @Override
    public List<Voting> getBetween(LocalDate startDate, LocalDate endDate) {
        return crudRepository.getBetween(startDate, endDate);
    }

    @Override
    public List<Voting> getBetweenForUser(int userId, LocalDate startDate, LocalDate endDate) {
        return crudRepository.getBetweenForUser(userId, startDate, endDate);
    }

    @Override
    public List<VotingResult> getVotingResult(LocalDate startDate, LocalDate endDate) {
        return crudRepository.getVotingResult(startDate, endDate);
    }
}
