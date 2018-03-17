package org.trip.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.trip.store.models.Tour;
import org.trip.store.models.User;
import org.trip.store.services.IUserService;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller

public class UserController {


    @Autowired
    private IUserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    String getLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    String getRegisterPage() {
        return "register";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    String login(HttpSession session,
                 Model model,
                 @RequestParam(value = "login") String login,
                 @RequestParam(value = "password") String password) {

        User user = userService.signin(login, password);
        if (user == null) {
            model.addAttribute("errorMessage", "Incorrect login or password");
            return "forward:/views/login.jsp";
        } else {
            session.setAttribute("logged", user);
            session.setAttribute("list", userService.getMyTours(user));
            return "redirect:/tours";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    String login(HttpSession session,
                 Model model,
                 @RequestParam(value = "login") String login,
                 @RequestParam(value = "password") String password,
                 @RequestParam(value = "passwordRepeat") String repeatedPassword) {


        if (!password.equals(repeatedPassword)
                || "".equals(login) || "".equals(password)) {
            model.addAttribute("registrationError", "Bad input. Try again, please!");
            return "forward:/views/register.jsp";

        } else {

            User u = userService.register(login, password);
            if (u == null) {
                model.addAttribute("registrationError", "Such user already exists");
                return "forward:/register";
            } else {
                session.setAttribute("logged", u);
                return "redirect:/tours/my";
            }

        }
    }


    @RequestMapping(value = "/tours/my", method = RequestMethod.GET)
    String getMyTours(HttpSession session) {

        User u = (User) session.getAttribute("logged");
        List<Tour> list = userService.getMyTours(u);
        session.setAttribute("mylist", list);
        return "mytours";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    String logout(HttpSession session) {

        session.invalidate();
        return "redirect:/home";
    }


}
