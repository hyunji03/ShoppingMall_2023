package com.example.mall.dto;

import com.example.mall.model.ShoppingEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShoppingDTO {
    private String id;
    private String userId;
    private String title;
    private String gender;
    private String brand;


    public ShoppingDTO(final ShoppingEntity entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.title = entity.getTitle();
        this.gender = entity.getGender();
        this.brand = entity.getBrand();
    }

    public static ShoppingEntity toEntity(final ShoppingDTO dto) {
        return ShoppingEntity.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .title(dto.getTitle())
                .gender(dto.getGender())
                .brand(dto.getBrand())
                .build();
    }


}
