package com.bilgeadam.banket.utility;

import com.bilgeadam.banket.entity.BaseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ServiceManager<T extends BaseEntity,ID> implements IService<T,ID>{

    private final MongoRepository<T,ID> mongoRepository;

    public ServiceManager(MongoRepository<T, ID> mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @Override
    public T save(T t) {
        LocalDateTime time = LocalDateTime.now();
        t.setUuid(UUID.randomUUID());
        t.setDeleted(false);
        t.setCreationDate(time);
        t.setUpdatedDate(time);
        t.setCreatedBy(t.getUuid().toString());
        t.setLastModifiedBy(t.getUuid().toString());
        return mongoRepository.save(t);
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> t) {
        LocalDateTime time = LocalDateTime.now();
        t.forEach(x-> {
            x.setUuid(UUID.randomUUID());
            x.setDeleted(false);
            x.setCreationDate(time);
            x.setUpdatedDate(time);
            x.setCreatedBy(x.getUuid().toString());
            x.setLastModifiedBy(x.getUuid().toString());
        });
        return mongoRepository.saveAll(t);
    }

    @Override
    public T update(T t) {
        LocalDateTime time = LocalDateTime.now();
        t.setUpdatedDate(time);
        t.setLastModifiedBy(t.getUuid().toString());
        return mongoRepository.save(t);
    }

    @Override
    public Boolean softDelete(T t) {
        LocalDateTime time = LocalDateTime.now();
        t.setDeleted(true);
        t.setUpdatedDate(time);
        t.setLastModifiedBy(t.getUuid().toString());
        mongoRepository.save(t);
        return true;
    }

    @Override
    public void hardDelete(T t) {
        mongoRepository.delete(t);
    }

    @Override
    public Boolean softDeleteById(ID id) {
        Optional<T> optionalT = mongoRepository.findById(id);
        if(optionalT.isEmpty()) return false;
        T t = optionalT.get();
        LocalDateTime time = LocalDateTime.now();
        t.setDeleted(true);
        t.setUpdatedDate(time);
        t.setLastModifiedBy(t.getUuid().toString());
        mongoRepository.save(t);
        return true;
    }

    @Override
    public void hardDeleteById(ID id) {
        mongoRepository.deleteById(id);
    }

    @Override
    public Optional<T> findById(ID id) {
        return mongoRepository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return mongoRepository.findAll();
    }

    @Override
    public Boolean existsById(ID id) {
        return mongoRepository.existsById(id);
    }

}