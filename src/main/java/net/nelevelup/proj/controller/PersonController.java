package net.nelevelup.proj.controller;


import net.nelevelup.proj.*;
import net.nelevelup.proj.entity.Film;
import net.nelevelup.proj.entity.Product;
import net.nelevelup.proj.entity.Session;
import net.nelevelup.proj.entity.SessionList;
import net.nelevelup.proj.entity.users.User;
import net.nelevelup.proj.service.ExtraService;
import net.nelevelup.proj.service.FilmService;
import net.nelevelup.proj.service.SecurityService;
import net.nelevelup.proj.service.SessionService;
import net.nelevelup.proj.session_entries.LocalSession;
import net.nelevelup.proj.validator.AdditionFilmValidator;
import net.nelevelup.proj.validator.AdditionSessionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/main/person")
@SessionAttributes({"client", "loggeduser"})
public class PersonController {

    @Autowired
    AdditionFilmValidator additionFilmValidator;

    @Autowired
    AdditionSessionValidator additionSessionValidator;

    @Autowired
    FilmService filmService;

    @Autowired
    SecurityService securityService;

    @Autowired
    ExtraService extraService;

    @Autowired
    SessionService sessionService;

    @RequestMapping(value = "/client")
    public ModelAndView getClientPage(ModelAndView modelAndView,
                                      @SessionAttribute("loggeduser") User user){
        String viewName = "client";
        for(GrantedAuthority grantedAuthority : user.getCurrentRoles()){
            if(grantedAuthority.getAuthority().equals("ROLE_ADMIN")){
                viewName = "redirect:/main/person/admin";
                break;
            }
        }
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    @RequestMapping(value = "/admin")
    public ModelAndView getAdminPage(ModelAndView modelAndView){
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/addfilm", method = RequestMethod.GET)
    public ModelAndView addFilm(ModelAndView modelAndView){
        modelAndView.setViewName("addfilm");
        modelAndView.addObject("film", new Film());
        return modelAndView;
    }

    @RequestMapping(value = "/admin/addfilm", method = RequestMethod.POST)
    public ModelAndView addFilm(ModelAndView modelAndView,
                 @RequestParam("odate") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                 @ModelAttribute Film film, BindingResult result) throws IOException {
        modelAndView.setViewName("addfilm");
        film.setPremiere(date);
        additionFilmValidator.validate(film, result);
        if(result.hasErrors()){

        }else{
            if(writeFile(film)){
                if(!(date == null)) {
                    filmService.saveFilm(film);
                    modelAndView.addObject("successfully", "Successfully");
                }
                else{
                    modelAndView.addObject("odateError", "Empty date");
                }
            }
            else{
                modelAndView.addObject("successfully", "Exception");
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin/deletefilm", method = RequestMethod.GET)
    public ModelAndView deleteFilm(ModelAndView modelAndView){
        modelAndView.setViewName("deletefilm");
        List<Film> films = filmService.getFilms();
        modelAndView.addObject("films", films);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/deletefilm/{id}", method = RequestMethod.GET)
    public ModelAndView deleteFilm(ModelAndView modelAndView, @PathVariable Integer id){
        modelAndView.setViewName("redirect:/main/person/admin/deletefilm");
        if(filmService.filmExistsById(id)) {
            filmService.deleteFilmById(id);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin/addsession", method = RequestMethod.GET)
    public ModelAndView addSession(ModelAndView modelAndView){
        modelAndView.setViewName("addsession");
        modelAndView.addObject("localSession", new LocalSession());
        modelAndView.addObject("films", filmService.getFilms());
        modelAndView.addObject("costs", extraService.getCosts());
        modelAndView.addObject("halls", extraService.getHallList());
        return modelAndView;
    }

    @RequestMapping(value = "/admin/addsession", method = RequestMethod.POST)
    public ModelAndView addSession(ModelAndView modelAndView,
           @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
           @RequestParam("startTime") String startTime,
           @RequestParam("endTime") String endTime,
           @ModelAttribute("localSession") LocalSession session, BindingResult result){
        boolean dateFlag = true;
        additionSessionValidator.validate(session, result);
        if(result.hasErrors()){
            modelAndView.addObject("successfully", "Exception");
        }
        else{
            if(date == null){
                modelAndView.addObject("fDate", "Empty field");
                dateFlag = false;
            }
            if(startTime.equals("")){
                modelAndView.addObject("f1Time", "Empty field");
                dateFlag = false;
            }
            if(endTime.equals("")){
                modelAndView.addObject("f2Time", "Empty field");
                dateFlag = false;
            }
            if(dateFlag){
                Product product = new Product(filmService.getFilmById(session.getFilmId()),
                        extraService.getCostById(session.getCostId()));
                String []startDate = startTime.split(":");
                String []endDate = endTime.split(":");
                Date date1 = new Date(date.getTime());
                date1.setHours(Integer.valueOf(endDate[0]));
                date1.setMinutes(Integer.valueOf(endDate[1]));
                date.setHours(Integer.valueOf(startDate[0]));
                date.setMinutes(Integer.valueOf(startDate[1]));
                Session lSession = new Session(product, date, date1);
                sessionService.saveSession(lSession);
                extraService.saveSessionList(new SessionList(extraService.getHallListById(session.getHallId()), lSession));
                modelAndView.addObject("successfully", "Successfully");
            }
        }
        modelAndView.setViewName("addsession");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/deletesession", method = RequestMethod.GET)
    public ModelAndView deleteSession(ModelAndView modelAndView){
        List<Session> sessions = sessionService.getSessions();
        modelAndView.addObject("sessions", sessions);
        modelAndView.setViewName("deletesession");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/deletesession/{sessionname}", method = RequestMethod.GET)
    public ModelAndView deleteSession(ModelAndView modelAndView, @PathVariable Integer sessionname){
        modelAndView.setViewName("redirect:/main/person/admin/deletesession");
        if(sessionService.sessionExistsById(sessionname)) {
            sessionService.deleteSessionById(sessionname);
        }
        return modelAndView;
    }

    private boolean writeFile(Film film) throws IOException {
        boolean result = true;
        File folder = new File("/home/ilya/Images/");
        if (!folder.exists()) {
            result = folder.mkdir();
        }
        if (result) {
            BufferedOutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream("/home/ilya/Images/" + new File(film.getImage().getOriginalFilename())));
            fileOutputStream.write(film.getImage().getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } else {
            result = false;
        }
        return result;
    }
}
