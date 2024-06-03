package com.doubleslash.showcase.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<CustomEmail, String> {

  @Override
  public boolean isValid(final String value, final ConstraintValidatorContext context) {
    // TODO add check for valid email
    return false;
  }

}