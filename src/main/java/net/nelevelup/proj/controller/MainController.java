package net.nelevelup.proj.controller;

import net.nelevelup.proj.entity.Film;
import net.nelevelup.proj.entity.users.User;
import net.nelevelup.proj.service.FilmService;
import net.nelevelup.proj.session_entries.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"client", "loggeduser"})
public class MainController {
    private final int filmsAmount = 3;

    @Autowired
    private FilmService filmService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView mainPageRedirect(ModelAndView modelAndView){
        modelAndView.setViewName("redirect:/main");
        return modelAndView;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView showMainPage(ModelAndView modelAndView, @SessionAttribute("loggeduser") User loggedUser){
        modelAndView.setViewName("main");
        List<Film> films = filmService.getFilms();
        List<Film> premiereFilms = new ArrayList<Film>();
        for (int filmsAmount = 0; filmsAmount < films.size(); filmsAmount++){
            premiereFilms.add(films.get(filmsAmount));
            if(filmsAmount == this.filmsAmount - 1) break;
        }
        modelAndView.addObject("premierefilms", premiereFilms);
        if(films.size() > this.filmsAmount){
            modelAndView.addObject("films", films.subList(filmsAmount, films.size()));
        }
        return modelAndView;
    }

    @RequestMapping(value = "/main/choice", method = RequestMethod.GET)
    public ModelAndView getCity(ModelAndView modelAndView, @SessionAttribute("loggeduser") User loggedUser){
        modelAndView.setViewName("choice");
        modelAndView.addObject("fillClient", new Client());
        return modelAndView;
    }
    @RequestMapping(value = "/main/choice", method = RequestMethod.POST)
    public ModelAndView getCity(ModelAndView modelAndView, @RequestParam String choice,
                     @SessionAttribute("client") Client client){
        client.setCity(choice);
        modelAndView.setViewName("redirect:/main");
        return modelAndView;
    }

    @RequestMapping(value = "/main/getimage/{poster}", method = RequestMethod.GET)
    public @ResponseBody
    void getDownloadBook(@PathVariable String poster,
                         HttpServletResponse response) throws IOException {
        File file = new File("/home/ilya/Images/" + poster + ".jpg");
        InputStream fileInputStream = new FileInputStream(file);
        response.setContentType("image/*");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + poster);
        response.setHeader("Content-Length", String.valueOf(file.length()));
        FileCopyUtils.copy(fileInputStream, response.getOutputStream());
    }

    @RequestMapping(value = "/main/getimage/inside/{poster}", method = RequestMethod.GET)
    public @ResponseBody
    void getInsideImage(@PathVariable String poster,
                         HttpServletResponse response) throws IOException {
        File file = new File("/home/ilya/Images/" + poster + ".png");
        InputStream fileInputStream = new FileInputStream(file);
        response.setContentType("image/*");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + poster);
        response.setHeader("Content-Length", String.valueOf(file.length()));
        FileCopyUtils.copy(fileInputStream, response.getOutputStream());
    }

    @ModelAttribute("client")
    public Client setUpClientForm() {
        return new Client();
    }
    @ModelAttribute("loggeduser")
    public User setUpUserForm(){
        return new User();
    }
}
