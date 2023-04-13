package com.example.demo.validation;

import com.example.demo.kit.validation.EmailValidation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EmailValidationTest {

    @Test
    public void testValidEmail() {
        assertTrue(EmailValidation.track("example@gmail.com"));
    }

    @Test
    public void testInvalidEmail() {
        assertFalse(EmailValidation.track("example@.com"));
    }

    @Test
    public void testEmailWithSpecialCharacters() {
        assertTrue(EmailValidation.track("example+123@gmail.com"));
    }

    @Test
    public void testEmailWithMultipleDots() {
        assertTrue(EmailValidation.track("example.first.last@gmail.com"));
    }
}
