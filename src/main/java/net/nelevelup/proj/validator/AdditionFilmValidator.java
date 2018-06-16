package net.nelevelup.proj.validator;

import net.nelevelup.proj.entity.Film;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AdditionFilmValidator implements Validator{
    public boolean supports(Class<?> aClass) {
        return false;
    }

    public void validate(@Nullable Object o, Errors errors) {
        if(!(o instanceof Film)) return;
        Film film = (Film) o;
        if(!film.getImage().getContentType().equals("image/jpg")
              &&   !film.getImage().getContentType().equals("image/jpeg")){
            errors.rejectValue("image", "tag", "Uncorrect format");
        }
        if(film.getImage().getSize() == 0){
            errors.rejectValue("image", "tag", "empty file");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "image",
                "tag", "Empty image");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title",
                "tag", "Empty title");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ageLimit",
                "tag", "Empty age limit");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
                "tag", "Empty description");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "filmGenres",
                "tag", "Empty image");
    }
}
