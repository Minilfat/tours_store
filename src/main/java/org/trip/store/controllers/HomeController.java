package org.trip.store.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("home")
    String listTour() {
        return "forward:/index.jsp";
    }



}
