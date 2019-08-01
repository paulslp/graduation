package ru.javawebinar.graduation.to;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class VotingTo extends BaseTo {

    @NotNull
    private LocalDate dateVoting;

    @NotNull
    private Integer restaurantId;

    public VotingTo(@NotNull LocalDate dateVoting, @NotNull Integer restaurantId) {
        this.dateVoting = dateVoting;
        this.restaurantId = restaurantId;
    }

    public LocalDate getDateVoting() {
        return dateVoting;
    }

    public void setDateVoting(LocalDate dateVoting) {
        this.dateVoting = dateVoting;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
}
