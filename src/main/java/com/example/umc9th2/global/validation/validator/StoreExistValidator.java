package com.example.umc9th2.global.validation.validator;

import com.example.umc9th2.global.validation.annotation.ExistStore;
import com.example.umc9th2.repository.StoreRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<ExistStore, Long> {

    private final StoreRepository storeRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        // null 은 @NotNull 로 따로 검증하게 두고 여기선 패스
        if (value == null) {
            return true;
        }

        boolean isValid = storeRepository.existsById(value);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("존재하지 않는 가게입니다.")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
