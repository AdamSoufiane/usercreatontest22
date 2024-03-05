package com.example.userregistration.infrastructure.repositories;

import com.example.userregistration.domain.entities.UserEntity;
import com.example.userregistration.domain.exceptions.UserDomainPersistenceException;
import com.example.userregistration.domain.ports.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PostgresUserRepositoryImpl implements UserRepositoryPort {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public UserEntity saveUser(UserEntity userEntity) throws DataAccessException {
        if (userEntity == null) {
            throw new UserDomainPersistenceException("User entity cannot be null");
        }
        try {
            entityManager.persist(userEntity);
            return userEntity;
        } catch (DataAccessException e) {
            throw new UserDomainPersistenceException("Error persisting user entity", e);
        }
    }

    @Override
    @Transactional
    public Optional<UserEntity> findUserById(Long id) throws DataAccessException {
        try {
            UserEntity userEntity = entityManager.find(UserEntity.class, id);
            return Optional.ofNullable(userEntity);
        } catch (DataAccessException e) {
            throw new UserDomainPersistenceException("Error retrieving user by id", e);
        }
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) throws DataAccessException {
        try {
            UserEntity userEntity = entityManager.find(UserEntity.class, id);
            if (userEntity != null) {
                entityManager.remove(userEntity);
            }
        } catch (DataAccessException e) {
            throw new UserDomainPersistenceException("Error deleting user by id", e);
        }
    }

    @Override
    @Transactional
    public List<UserEntity> findAllUsers() throws DataAccessException {
        try {
            return entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class).getResultList();
        } catch (DataAccessException e) {
            throw new UserDomainPersistenceException("Error retrieving all users", e);
        }
    }

    @Override
    @Transactional
    public UserEntity updateUser(UserEntity userEntity) throws DataAccessException {
        if (userEntity == null) {
            throw new UserDomainPersistenceException("User entity cannot be null");
        }
        try {
            return entityManager.merge(userEntity);
        } catch (DataAccessException e) {
            throw new UserDomainPersistenceException("Error updating user entity", e);
        }
    }
}
