package net.nelevelup.proj.validator;

import net.nelevelup.proj.entity.Cinema;
import org.springframework.lang.Nullable;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AdditionCinemaValidator implements Validator{
    public boolean supports(Class<?> aClass) {
        return false;
    }

    public void validate(@Nullable Object o, Errors errors) {
        if(!(o instanceof Cinema)) return;
        Cinema cinema = (Cinema) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "image",
                "tag", "Empty image");
    }
}
