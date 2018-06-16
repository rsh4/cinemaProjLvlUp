package net.nelevelup.proj.controller;

import net.nelevelup.proj.entity.users.User;
import net.nelevelup.proj.service.SecurityService;
import net.nelevelup.proj.service.UserService;
import net.nelevelup.proj.validator.SignUpValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/main/panel")
@SessionAttributes({"client", "loggeduser"})
public class LoginController {

    @Autowired
    SecurityService securityService;

    @Autowired
    SignUpValidator signUpValidator;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView signIn(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView signIn(ModelAndView modelAndView, @ModelAttribute User user){
        modelAndView.setViewName("redirect:/main");
        User loggedUser = securityService.autoLogin(user.getUsername(), user.getPassword());
        if(loggedUser == null){
            return new ModelAndView("redirect:/main/panel/login?error=true");}
        else{
            modelAndView.addObject("loggeduser", loggedUser);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView signUp(ModelAndView modelAndView){
        modelAndView.setViewName("registration");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView signUp(ModelAndView modelAndView, @ModelAttribute User user,
                               BindingResult result){
        modelAndView.setViewName("registration");
        if(!userService.isUserExists(user.getUsername())) {
            signUpValidator.validate(user, result);
            if (result.hasErrors()) {

            } else {
                userService.registerUser(user.getUsername(), user.getPassword());
                User loggedUser = securityService.autoLogin(user.getUsername(), user.getPassword());
                modelAndView.addObject("loggeduser", loggedUser);
                modelAndView.addObject("successfully", "Successfully");
            }
        }
        else{
            modelAndView.addObject("successfully", "Such user exists");
        }
        return modelAndView;
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(ModelAndView modelAndView, HttpServletRequest request){
        modelAndView.setViewName("redirect:" + request.getHeader("referer"));
        modelAndView.addObject("loggeduser", new User());
        securityService.clearContext();
        return modelAndView;
    }
}
