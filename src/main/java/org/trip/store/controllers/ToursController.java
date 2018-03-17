package org.trip.store.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.trip.store.models.Tour;
import org.trip.store.models.User;
import org.trip.store.services.ITourService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ToursController {

    @Autowired
    private ITourService tourService;

    private static final Logger LOGGER = Logger.getLogger(ToursController.class);


    @RequestMapping(value = "/tours", method = RequestMethod.GET)
    String getAvailableTours(Model model) {
        List<Tour> list = tourService.getAvailableTours();
        model.addAttribute("list", list);
        return "tours";
    }



    @RequestMapping(value = "/tours/order", method = RequestMethod.POST)
    String bookTour(HttpSession session,  @RequestParam(value = "tourid") String tourid) {

        User u = (User) session.getAttribute("logged");
        Long tourId;
        try {
            tourId = Long.valueOf(tourid);
        } catch (NumberFormatException e) {
            LOGGER.error(e);
            return "home";
        }

        tourService.orderTour(u.getId(), tourId);
        Tour tour = tourService.getTour(tourId);
        session.setAttribute("tour", tour);
        return "tourInfo";
    }

}
