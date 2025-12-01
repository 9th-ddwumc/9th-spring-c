package com.example.umc9th2.global.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import com.example.umc9th2.domain.member.repository.FoodRepository;

@RequiredArgsConstructor
public class ExistFoodsValidator implements ConstraintValidator<ExistFoods, Long> {

    private final FoodRepository foodRepository;

    @Override
    public boolean isValid(Long foodId, ConstraintValidatorContext context) {
        if (foodId == null) return false;
        return foodRepository.existsById(foodId);
    }
}
