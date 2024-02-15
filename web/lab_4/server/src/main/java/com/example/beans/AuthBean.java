package com.example.beans;

import com.example.DB.User;
import com.example.exceptions.LoginException;
import com.example.exceptions.RefreshTokenException;
import com.example.exceptions.RegistrationException;
import com.example.models.LogReq;
import com.example.models.RefreshTokenReq;
import com.example.models.RegisterReq;
import com.example.models.Token;
import com.example.util.CryptUtils;
import com.example.util.JWTutil;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import lombok.extern.java.Log;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Stateless
@Log
public class AuthBean {
    @PersistenceContext(unitName = "persistence-unit")
    private EntityManager entityManager;

    public Token login(LogReq request) throws LoginException {
        User user = findUserByUsername(request.getUsername())
                .orElseThrow(() -> {
                    String message = "User with this username not found. Try to register";
                    return LoginException.userNotFound(message);
                });

        if (!CryptUtils.checkPassword(request.getPassword(), user.getPassword()))
            throw LoginException.wrongPassword("Wrong password");

        String accessToken = JWTutil.tokenForUser(user);
        String refreshToken = UUID.randomUUID().toString();

        user.setRefreshToken(refreshToken);

        return new Token(accessToken, refreshToken);
    }

    public Token refreshToken(RefreshTokenReq request) throws RefreshTokenException {
        User user = findByRefreshToken(request.getRefresh()).orElseThrow(RefreshTokenException::new);
        String accessToken = JWTutil.tokenForUser(user);
        String refreshToken = request.getRefresh();
        return new Token(accessToken, refreshToken);
    }

    public void registration(RegisterReq request) throws RegistrationException {
        AuthBean.validate(request);

        if (findUserByUsername(request.getUsername()).isPresent())
            throw RegistrationException.userAlreadyExist("User with this username already exist. Try to login");

        String hashedPassword = CryptUtils.hashPassword(request.getPassword());

        User user = User.builder()
                .username(request.getUsername())
                .password(hashedPassword)
                .build();

        entityManager.persist(user);
    }

    private Optional<User> findUserByUsername(String username) {
        try {
            Query namedQuery = entityManager.createNamedQuery("User.findByUsername");
            namedQuery.setParameter("username", username);
            User user = (User) namedQuery.getSingleResult();
            return Optional.of(user);
        } catch (PersistenceException exception) {
            return Optional.empty();
        }
    }

    private Optional<User> findByRefreshToken(String refreshToken) {
        try {
            Query namedQuery = entityManager.createNamedQuery("User.findByRefreshToken");
            namedQuery.setParameter("refreshToken", refreshToken);
            User user = (User) namedQuery.getSingleResult();
            return Optional.of(user);
        } catch (PersistenceException exception) {
            return Optional.empty();
        }
    }

    private static void validate(RegisterReq request) throws RegistrationException {
        if (Objects.isNull(request.getUsername()))
            throw RegistrationException.notEnoughData("Hasn't username");

        if (Objects.isNull(request.getPassword()))
            throw RegistrationException.notEnoughData("Hasn't password");

        if (Objects.isNull(request.getPassword2()))
            throw RegistrationException.notEnoughData("Hasn't password confirmation");

        if (request.getUsername().isEmpty())
            throw RegistrationException.invalidData("Username is empty");

        if (request.getPassword().isEmpty())
            throw RegistrationException.invalidData("Password is empty");

        if (request.getPassword2().isEmpty())
            throw RegistrationException.invalidData("Password confirmation is empty");

        if (!Objects.equals(request.getPassword(), request.getPassword2()))
            throw RegistrationException.passwordsNotEqual("Password and password confirmation must equal");
    }
}