package com.example.umc9th2.domain.member.repository;

import com.example.umc9th2.domain.member.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    long countByIdIn(List<Long> ids);
}