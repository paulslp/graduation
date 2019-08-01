package ru.javawebinar.graduation.to;

import ru.javawebinar.graduation.model.AbstractBaseEntity;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.LocalDate;

public class VotingResult extends AbstractBaseEntity {

    @NotNull
    private LocalDate dateVoting;

    @NotNull
    private Integer restaurantId;

    @NotNull
    private String restaurantName;

    @NotNull
    private BigInteger count;

    public VotingResult() {
    }

    public VotingResult(@NotNull LocalDate dateVoting, @NotNull Integer restaurantId, @NotNull String restaurantName, @NotNull BigInteger count) {
        this.dateVoting = dateVoting;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.count = count;
    }

    public LocalDate getDateVoting() {
        return dateVoting;
    }

    public void setDateVoting(LocalDate dateVoting) {
        this.dateVoting = dateVoting;
    }

    public BigInteger getCount() {
        return count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
