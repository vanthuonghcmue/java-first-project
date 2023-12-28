package com.example.demo.validator;

import com.example.demo.annotation.UniqueUsername;
import com.example.demo.service.impl.UserService;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;


public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    @Autowired
    UserService userService;

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
        // Initialization logic, if needed
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext cxt) {
        return !userService.exists(username);
    }
}
