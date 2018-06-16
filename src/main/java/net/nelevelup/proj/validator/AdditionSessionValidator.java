package net.nelevelup.proj.validator;

import net.nelevelup.proj.service.ExtraService;
import net.nelevelup.proj.service.FilmService;
import net.nelevelup.proj.session_entries.LocalSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AdditionSessionValidator implements Validator{

    @Autowired
    FilmService filmService;

    @Autowired
    ExtraService extraService;

    public boolean supports(Class<?> aClass) {
        return false;
    }

    public void validate(@Nullable Object o, Errors errors) {
        if(!(o instanceof LocalSession)) return;
        LocalSession localSession = (LocalSession) o;
        if(!filmService.filmExistsById(localSession.getFilmId())){
            errors.rejectValue("filmId", "tag", "No such film");
        }
        if(!extraService.costExistsById(localSession.getCostId())){
            errors.rejectValue("costId", "tag", "No such cost");
        }
        if(!extraService.hallExistsById(localSession.getHallId())){
            errors.rejectValue("hallId", "tag", "No such hall");
        }
    }
}
