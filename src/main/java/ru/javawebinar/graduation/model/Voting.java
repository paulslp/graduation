package ru.javawebinar.graduation.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.javawebinar.graduation.to.VotingResult;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.LocalDate;


@Entity
@SqlResultSetMapping(name = "VotingResultMapping",
            classes = @ConstructorResult(
                        targetClass = VotingResult.class,
                        columns = {
                                    @ColumnResult(name = "dateVoting", type = LocalDate.class),
                                    @ColumnResult(name = "restaurantId", type = Integer.class),
                                    @ColumnResult(name = "restaurantName", type = String.class),
                                    @ColumnResult(name = "count", type = BigInteger.class)
                        }
            )
)
@NamedNativeQuery(
            name = "Voting.getVotingResult",
            query = "SELECT rd.date as dateVoting,rd.id as restaurantId, rd.name as restaurantName,  count(v.user_id) as count " +
                        "FROM (SELECT r.id, r.name, d.date FROM public.restaurants r,public.date_list d WHERE d.date BETWEEN :startDate AND :endDate) rd " +
                        "LEFT JOIN public.voting v ON rd.id = v.restaurant_id and rd.date=v.date_voting " +
                        "GROUP BY rd.id, rd.name, rd.date " +
                        "ORDER BY rd.date DESC,rd.name ASC",
            resultSetMapping = "VotingResultMapping")
@Table(name = "voting", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_voting"}, name = "voting_unique_restaurant_user_date_idx")})
public class Voting extends AbstractBaseEntity {


    @Column(name = "date_voting", nullable = false)
    @NotNull
    private LocalDate dateVoting;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public Voting() {
    }

    public Voting(@NotNull LocalDate dateVoting, User user, Restaurant restaurant) {
        this.dateVoting = dateVoting;
        this.restaurant = restaurant;
    }

    public LocalDate getDateVoting() {
        return dateVoting;
    }

    public void setDateVoting(LocalDate dateVoting) {
        this.dateVoting = dateVoting;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}
