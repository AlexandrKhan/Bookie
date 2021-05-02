package edu.epam.bookie.validator;

import java.util.EnumSet;
import java.util.Set;

/**
 * Set of Validation errors
 * @see ValidationError
 */
public class ValidationErrorSet {
    private static final ValidationErrorSet INSTANCE = new ValidationErrorSet();
    private final EnumSet<ValidationError> errors = EnumSet.noneOf(ValidationError.class);

    private ValidationErrorSet() {
    }

    public static ValidationErrorSet getInstance(){
        return INSTANCE;
    }

    public boolean add(ValidationError validationError) {
        return errors.add(validationError);
    }

    public boolean contains(ValidationError error) {
        return errors.contains(error);
    }

    public void clear() {
        errors.clear();
    }

    public Set<ValidationError> getAllAndClear() {
        EnumSet<ValidationError> copy = EnumSet.copyOf(errors);
        errors.clear();
        return copy;
    }
}
