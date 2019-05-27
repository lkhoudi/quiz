package com.quiz.demoQuiz.web;

import com.quiz.demoQuiz.Serveur.parser.ParserJson;
import com.quiz.demoQuiz.entity.Options;
import com.quiz.demoQuiz.entity.QuizLevels;
import com.quiz.demoQuiz.entity.QuizThemes;
import com.quiz.demoQuiz.entity.Quizz;
import com.quiz.demoQuiz.service.UserService;
import com.quiz.demoQuiz.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();

        model.setViewName("user/login");
        return model;
    }

    @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("user", user);
        model.setViewName("user/signup");

        return model;
    }

    @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
    public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());

        if(userExists != null) {
            bindingResult.rejectValue("email", "error.user", "This email already exists!");
        }
        if(bindingResult.hasErrors()) {
            model.setViewName("user/signup");
        } else {
            userService.saveUser(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new User());
            model.setViewName("user/signup");
        }

        return model;
    }

    @RequestMapping(value= {"/play"}, method=RequestMethod.POST)
    public ModelAndView play(@RequestParam String themes, @RequestParam String levels){
        ModelAndView modelAndView = new ModelAndView();
        ParserJson parserJson = new ParserJson();
        Quizz quizz = parserJson.jsonparser(QuizThemes.valueOf(themes.toUpperCase()).getValue(), QuizLevels.valueOf(levels.toUpperCase()).getValue());
        modelAndView.addObject("quizz",quizz);
        modelAndView.setViewName("home/play");
        return modelAndView;
    }

    @RequestMapping(value= {"/home/home"}, method=RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Options options = new Options();
        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.addObject("options", options);
        model.setViewName("home/home");
        return model;
    }

    @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }
}