package com.example.userregistration.domain.ports;

import com.example.userregistration.domain.entities.UserEntity;
import org.springframework.dao.DataAccessException;
import java.util.List;
import java.util.Optional;

/**
 * Defines the contract for user persistence operations, outlining methods for saving and retrieving user entities.
 */
public interface UserRepositoryPort {

    UserEntity saveUser(UserEntity userEntity) throws DataAccessException;

    Optional<UserEntity> findUserById(Long id) throws DataAccessException;

    void deleteUserById(Long id) throws DataAccessException;

    List<UserEntity> findAllUsers() throws DataAccessException;

    UserEntity updateUser(UserEntity userEntity) throws DataAccessException;

}