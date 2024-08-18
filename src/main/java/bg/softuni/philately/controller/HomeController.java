package bg.softuni.philately.controller;

import bg.softuni.philately.model.dto.stamp.StampHomeViewModel;
import bg.softuni.philately.service.StampService;
import bg.softuni.philately.service.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private final LoggedUser loggedUser;
    private final StampService stampService;

    public HomeController(LoggedUser loggedUser, StampService stampService) {
        this.loggedUser = loggedUser;
        this.stampService = stampService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }

        return new ModelAndView("index");
    }

    @GetMapping("/home")
    public ModelAndView home() {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        StampHomeViewModel viewModel = stampService.getHomeViewData();

        return new ModelAndView("home", "stamps", viewModel);
    }
}
