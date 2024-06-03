package com.doubleslash.showcase.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<CustomEmail, String> {

  @Override
  public boolean isValid(final String value, final ConstraintValidatorContext context) {
    // TODO add check for valid email
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    Pattern pattern = Pattern.compile(emailRegex);
    if (value == null) {
      return false;
    }
    Matcher matcher = pattern.matcher(value);
    return matcher.matches();
  }

}