package com.doubleslash.showcase.validator;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailValidatorTest {

    private EmailValidator emailValidator;

    @Mock
    private ConstraintValidatorContext context;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        emailValidator = new EmailValidator();
    }

    @ParameterizedTest
    @MethodSource("provideEmailsForValidation")
    public void emailValidationTest(String email, boolean expected) {
        assertEquals(expected, emailValidator.isValid(email, context));
    }

    private static Stream<Arguments> provideEmailsForValidation() {
        return Stream.of(
                Arguments.of("test@example.com", true),
                Arguments.of("test.name+alias-100@example.com", true),
                Arguments.of("test.name@example.co.uk", true),
                Arguments.of("test.example.com", false),
                Arguments.of("test@", false),
                Arguments.of("@example.com", false),
                Arguments.of(null, false)
        );
    }
}