package pl.darczuk.studia.java.entities.validators;

import lombok.extern.java.Log;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.regex.Pattern;

@Log
public class NameValidator implements ConstraintValidator<Name, String> {

    private static final String PATTERN = "^\\p{Lu}(\\p{L}|\\p{N})*([ ](\\p{L}|\\p{N})*)*$";

    private String name;

    @Override
    public void initialize(Name constraintAnnotation) {
        if (!constraintAnnotation.toString().equals("")) {
                name = constraintAnnotation.toString();
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (name != null) {

            if (!Pattern.matches(PATTERN, value)) {
                return false;
            } else
                return !value.isEmpty();
        }
        else {
            return false;
        }
    }
}
