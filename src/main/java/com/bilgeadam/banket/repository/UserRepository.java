package com.bilgeadam.banket.repository;

import com.bilgeadam.banket.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    Optional<User> findByUuid(UUID uuid);

}
