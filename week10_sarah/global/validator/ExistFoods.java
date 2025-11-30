package com.example.umc9th2.global.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistFoodsValidator.class)
public @interface ExistFoods {
    String message() default "존재하지 않는 음식 ID입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
