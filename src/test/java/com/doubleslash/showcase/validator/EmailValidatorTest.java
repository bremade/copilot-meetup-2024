package com.doubleslash.showcase.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorTest {

    private EmailValidator emailValidator;

    @BeforeEach
    public void setup() {
        emailValidator = new EmailValidator();
    }

    @Test
    @DisplayName("Should return true when email is valid")
    public void isValidReturnsTrueForValidEmail() {
        assertTrue(emailValidator.isValid("test@test.com", null));
    }

    @Test
    @DisplayName("Should return false when email is null")
    public void isValidReturnsFalseForNullEmail() {
        assertFalse(emailValidator.isValid(null, null));
    }

    @Test
    @DisplayName("Should return false when email is empty")
    public void isValidReturnsFalseForEmptyEmail() {
        assertFalse(emailValidator.isValid("", null));
    }

    @Test
    @DisplayName("Should return false when email is without @ symbol")
    public void isValidReturnsFalseForEmailWithoutAtSymbol() {
        assertFalse(emailValidator.isValid("test.com", null));
    }

    @Test
    @DisplayName("Should return false when email is without domain")
    public void isValidReturnsFalseForEmailWithoutDomain() {
        assertFalse(emailValidator.isValid("test@", null));
    }
}