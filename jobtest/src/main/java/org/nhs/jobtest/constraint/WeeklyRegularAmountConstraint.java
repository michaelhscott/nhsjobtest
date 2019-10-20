package org.nhs.jobtest.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = WeeklyRegularAmountValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface WeeklyRegularAmountConstraint {
    String message() default "Invalid weekly regular amount";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

