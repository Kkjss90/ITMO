package com.example.beans;

import com.example.DB.User;
import com.example.REST.filters.UserPrincipal;
import com.example.models.CheckResponse;
import com.example.models.Coordinates;
import com.example.DB.Result;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import lombok.extern.java.Log;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Log
@Stateless
public class ResultBean {
    @PersistenceContext(unitName = "persistence-unit")
    private EntityManager entityManager;
    @EJB
    private CoordCheck coordinatesChecker;

    public CheckResponse check(Coordinates coordinates, UserPrincipal userPrincipal) {
        try {
            Query namedQuery = entityManager.createNamedQuery("User.findByID");
            namedQuery.setParameter("id", userPrincipal.getId());
            User user = (User) namedQuery.getSingleResult();

            Boolean success = coordinatesChecker.check(coordinates);
            log.info(success.toString());
            log.info(coordinates.toString());

            Result result = Result.builder()
                    .x(coordinates.getX())
                    .y(coordinates.getY())
                    .r(coordinates.getR())
                    .success(success)
                    .timestamp(LocalDateTime.now())
                    .user(user)
                    .build();

            entityManager.persist(result);

            return new CheckResponse(success);
        } catch (NoResultException ignored) {
            return null;
        }
    }

    public List<com.example.models.Result> getResults(UserPrincipal userPrincipal) {
        try {
            Query namedQuery = entityManager.createNamedQuery("User.findByIDWithResults");
            namedQuery.setParameter("id", userPrincipal.getId());
            User user = (User) namedQuery.getSingleResult();

            return user.getResults().stream()
                    .sorted(Comparator.comparing(Result::getTimestamp))
                    .map(ResultBean::transformToResult)
                    .collect(Collectors.toList());
        } catch (PersistenceException exception) {
            return new ArrayList<>();
        }
    }

    public boolean clearResults(UserPrincipal userPrincipal) {
        try {
            Query namedQuery = entityManager.createNamedQuery("User.findByIDWithResults");
            namedQuery.setParameter("id", userPrincipal.getId());
            User user = (User) namedQuery.getSingleResult();
            for (Result result : user.getResults()) {
                entityManager.remove(result);
            }
            return true;
        } catch (NoResultException e) {
            return true;
        } catch (PersistenceException exception) {
            log.warning("Error clearing results: " + exception.getMessage());
            return false;
        }
    }

    private static com.example.models.Result transformToResult(Result db) {
        return new com.example.models.Result(
                db.getId(),
                String.valueOf(db.getX()),
                String.valueOf(db.getY()),
                String.valueOf(db.getR()),
                db.getTimestamp().toString(),
                db.getSuccess()
        );
    }
}
