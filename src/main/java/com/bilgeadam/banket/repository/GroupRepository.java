package com.bilgeadam.banket.repository;

import com.bilgeadam.banket.entity.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface GroupRepository extends MongoRepository<Group, String> {
    Boolean existsByName(String name);

    Optional<Group> findByUuid(UUID uuid);

}
