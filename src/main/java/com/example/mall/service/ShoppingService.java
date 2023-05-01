package com.example.mall.service;

import com.example.mall.model.ShoppingEntity;
import com.example.mall.persistence.ShoppingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ShoppingService {

    @Autowired
    private ShoppingRepository repository;

    public String testService() {
        //TodoEntity 생성
        ShoppingEntity entity = ShoppingEntity.builder().title("My first todo item").build();
        //TodoEntity 저장
        repository.save(entity);
        //TodoEntity 검색
        ShoppingEntity savedEntity = repository.findById(entity.getId()).get();
        return savedEntity.getTitle();
    }

    public List<ShoppingEntity> create(final ShoppingEntity entity) {
        // Validations
        validate(entity);

        repository.save(entity);

        log.info("Entity Id : {} is saved.", entity.getId());

        return repository.findByUserId(entity.getUserId());
    }

    // 리팩토링한 메서드
    private void validate(final ShoppingEntity entity) {
        if(entity == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if(entity.getUserId() == null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }


    public List<ShoppingEntity> retrieve(final String userId) {
        return repository.findByUserId(userId);
    }


   /* public List<ShoppingEntity> search(String searchKeyword, Pageable pageable){
        return repository.findByTitleContaining(searchKeyword, pageable);
    }

    */

    public List<ShoppingEntity> update(final ShoppingEntity entity) {
        validate(entity);

        final Optional<ShoppingEntity> original = repository.findById(entity.getId());

        original.ifPresent(todo -> {
            todo.setTitle(entity.getTitle());
            todo.setGender(entity.getGender());
            todo.setBrand(entity.getBrand());

            repository.save(todo);
        });

        return retrieve(entity.getUserId());
    }
    public List<ShoppingEntity> delete(final ShoppingEntity entity) {
        validate(entity);

        try{
            repository.delete(entity);
        } catch(Exception e) {
            log.error("error deleting entity ", entity.getId(), e);

            throw new RuntimeException("error deleting entity" + entity.getId());
        }

        return retrieve(entity.getUserId());
    }



}

