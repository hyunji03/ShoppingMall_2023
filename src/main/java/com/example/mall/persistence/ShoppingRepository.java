package com.example.mall.persistence;

import com.example.mall.model.ShoppingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ShoppingRepository extends JpaRepository<ShoppingEntity, String> {
    List<ShoppingEntity> findByUserId(String userId);
    //List<ShoppingEntity> findByTitleContaining(String searchKeyword, Pageable pageable);
}
