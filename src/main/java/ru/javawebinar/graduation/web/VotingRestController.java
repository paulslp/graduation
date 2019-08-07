package ru.javawebinar.graduation.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.graduation.model.Voting;
import ru.javawebinar.graduation.service.VotingService;
import ru.javawebinar.graduation.to.VotingResult;
import ru.javawebinar.graduation.to.VotingTo;
import ru.javawebinar.graduation.util.DateTimeUtil;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.javawebinar.graduation.util.Util.orElse;
import static ru.javawebinar.graduation.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(VotingRestController.REST_URL)
public class VotingRestController {
    static final String REST_URL = "/rest";

    @Autowired
    private final VotingService service;

    public VotingRestController(VotingService service) {
        this.service = service;
    }

    @GetMapping(value = "/user/voting/filter")
    public List<Voting> getBetweenForCurrentUser(@RequestParam(value = "startDate") LocalDate startDate,
                                                 @RequestParam(value = "endDate") LocalDate endDate) {
        return service.getBetweenForUser(SecurityUtil.authUserId(), startDate, endDate);
    }

    @GetMapping(value = "/user/voting/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Voting get(@PathVariable("id") int id) {
        return service.get(id, SecurityUtil.authUserId());
    }

    @PostMapping(value = "/user/voting/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Voting> createWithLocation(@PathVariable("restaurantId") int restaurantId) {
        int userId = SecurityUtil.authUserId();
        VotingTo votingTo = new VotingTo(LocalDate.now(), restaurantId);
        Voting created = service.create(votingTo, userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL)
                    .buildAndExpand(created).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/user/voting/{restaurantId}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Voting> update(@PathVariable("restaurantId") int restaurantId, @PathVariable("id") int id) {
        int userId = SecurityUtil.authUserId();
        VotingTo votingTo = new VotingTo(LocalDate.now(), restaurantId);
        assureIdConsistent(votingTo, id);
        Voting updated = service.update(votingTo, userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL)
                    .buildAndExpand(updated).toUri();
        return ResponseEntity.created(uriOfNewResource).body(updated);
    }

    @DeleteMapping(value = "/user/voting")
    public void deleteFromUser() {
        int userId = SecurityUtil.authUserId();
        service.deleteFromUser(userId);
    }

    @DeleteMapping(value = "/admin/voting/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        int userId = SecurityUtil.authUserId();
        service.delete(id);
    }


    @GetMapping(value = "/user/voting/result", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VotingResult> getVotingResult(@RequestParam(value = "startDate", required = false) LocalDate startDate,
                                              @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        return service.getVotingResult(orElse(startDate, DateTimeUtil.MIN_DATE), orElse(endDate, DateTimeUtil.MAX_DATE));
    }

    @GetMapping(value = "/admin/voting/filter")
    public List<Voting> getBetween(
                @RequestParam(value = "startDate", required = false) LocalDate startDate,
                @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        return service.getBetween(orElse(startDate, DateTimeUtil.MIN_DATE), orElse(endDate, DateTimeUtil.MAX_DATE));
    }

    @GetMapping(value = "/admin/voting/filter/{userId}")
    public List<Voting> getBetweenForUser(@PathVariable("userId") int userId,
                                          @RequestParam(value = "startDate", required = false) LocalDate startDate,
                                          @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        return service.getBetweenForUser(userId, orElse(startDate, DateTimeUtil.MIN_DATE), orElse(endDate, DateTimeUtil.MAX_DATE));
    }

}
