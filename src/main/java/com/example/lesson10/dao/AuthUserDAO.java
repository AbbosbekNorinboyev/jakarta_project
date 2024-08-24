package com.example.lesson10.dao;

import com.example.lesson10.entity.AuthUser;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public class AuthUserDAO extends BaseDAO<AuthUser, String> {
    public Optional<AuthUser> findByEmail(@NotNull String email) {
        try {
            begin();
            AuthUser authUser =
                    entityManager.createQuery("select a from AuthUser a where a.email ilike :email", AuthUser.class)
                            .setParameter("email", email)
                            .getSingleResult();
            commit();
            return Optional.ofNullable(authUser);
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
            return Optional.empty();
        }
    }
}
