package net.nelevelup.proj.validator;

import net.nelevelup.proj.entity.users.User;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.regex.Pattern;

@Component
public class SignUpValidator implements org.springframework.validation.Validator{
    public boolean supports(Class<?> aClass) {
        return false;
    }

    public void validate(@Nullable Object o, Errors errors) {
        if(!(o instanceof User)) return;
        User user = (User) o;
        if(!user.getPassword().equals(user.getConfirmPassword())){
            errors.rejectValue("password", "tag", "fields don't match");
            errors.rejectValue("confirmPassword", "tag", "fields don't match");
        }
        Pattern passwordPattern = Pattern.compile("^[A-Za-z0-9]{6,16}$");
        Pattern usernamePattern = Pattern.compile("^[A-Za-z0-9]{4,16}$");
        if(!passwordPattern.matcher(user.getPassword()).matches())
            errors.rejectValue("password", "tag", "Password should be between 6 and 16" +
                    "and use a-Z and numbers");
        if(!usernamePattern.matcher(user.getUsername()).matches())
            errors.rejectValue("username", "tag", "Username should be between 4 and 16" +
                    "and use a-Z and numbers");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
                "tag", "Empty username");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "tag", "Empty password");
    }
}
