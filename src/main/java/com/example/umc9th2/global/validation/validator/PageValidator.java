package com.example.umc9th2.global.validation.validator;

import com.example.umc9th2.global.validation.annotation.ValidPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PageValidator implements ConstraintValidator<ValidPage, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // null인 경우는 @RequestParam(defaultValue="1")로 커버되니까 통과
        if (value == null) {
            return true;
        }
        // 1 이상만 허용
        return value >= 1;
    }
}
