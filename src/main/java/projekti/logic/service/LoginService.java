package projekti.logic.service;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.springframework.stereotype.Service;
import projekti.security.PasswordConstraintValidator;

@Service
public class LoginService {

    @Documented
    @Constraint(validatedBy = PasswordConstraintValidator.class)
    @Target({TYPE, FIELD, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    public @interface ValidPassword {

        String message() default "Invalid Password";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

    }
}