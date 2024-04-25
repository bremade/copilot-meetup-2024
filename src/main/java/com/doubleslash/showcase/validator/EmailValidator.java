package com.doubleslash.showcase.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<CustomEmail, String> {

  @Override
  public boolean isValid(final String value, final ConstraintValidatorContext context) {
    final Pattern pattern =
            Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    final Matcher matcher = pattern.matcher(value);
    try {
      return matcher.matches();
    } catch (Exception e) {
      return false;
    }
  }

  // create a methode to check the given email with a regex pattern


}