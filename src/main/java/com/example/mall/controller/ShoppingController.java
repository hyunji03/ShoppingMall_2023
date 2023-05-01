package com.example.mall.controller;

import com.example.mall.dto.ResponseDTO;
import com.example.mall.dto.ShoppingDTO;
import com.example.mall.model.ShoppingEntity;
import com.example.mall.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
public class ShoppingController {
    // testTodo 메서드 작성하기
    @Autowired
    private ShoppingService service;

    @GetMapping("/test")
    public ResponseEntity<?> testTodo() {
        String str = service.testService();
        List<String> list = new ArrayList<>();
        list.add(str);
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody ShoppingDTO dto) {
        try {
            String temporaryUserId = "HyunJiKim";

            ShoppingEntity entity = ShoppingDTO.toEntity(dto);

            entity.setId(null);

            entity.setUserId(temporaryUserId);

            List<ShoppingEntity> entities = service.create(entity);

            List<ShoppingDTO> dtos = entities.stream().map(ShoppingDTO::new).collect(Collectors.toList());

            ResponseDTO<ShoppingDTO> response = ResponseDTO.<ShoppingDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);
        }catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<ShoppingDTO> response = ResponseDTO.<ShoppingDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }








    @GetMapping
    public ResponseEntity<?> retrieveTodoList() {
        String temporaryUserId = "HyunJiKim";

        List<ShoppingEntity> entities =service.retrieve(temporaryUserId);

        List<ShoppingDTO> dtos = entities.stream().map(ShoppingDTO::new).collect(Collectors.toList());

        ResponseDTO<ShoppingDTO> response = ResponseDTO.<ShoppingDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }



    @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody ShoppingDTO dto) {
        String temporaryUserId = "HyunJiKim";

        ShoppingEntity entity = ShoppingDTO.toEntity(dto);

        entity.setUserId(temporaryUserId);

        List<ShoppingEntity> entities = service.update(entity);

        List<ShoppingDTO> dtos = entities.stream().map(ShoppingDTO::new).collect(Collectors.toList());

        ResponseDTO<ShoppingDTO> response = ResponseDTO.<ShoppingDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTodo(@RequestBody ShoppingDTO dto) {
        try {
            String temporaryUserId = "HyunJiKim";

            ShoppingEntity entity = ShoppingDTO.toEntity(dto);

            entity.setUserId(temporaryUserId);

            List<ShoppingEntity> entities = service.delete(entity);

            List<ShoppingDTO> dtos = entities.stream().map(ShoppingDTO::new).collect(Collectors.toList());

            ResponseDTO<ShoppingDTO> response = ResponseDTO.<ShoppingDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<ShoppingDTO> response = ResponseDTO.<ShoppingDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
