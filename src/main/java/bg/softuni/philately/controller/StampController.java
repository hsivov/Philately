package bg.softuni.philately.controller;

import bg.softuni.philately.model.dto.stamp.AddStampBindingModel;
import bg.softuni.philately.service.StampService;
import bg.softuni.philately.service.session.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StampController {
    private final LoggedUser loggedUser;
    private final StampService stampService;

    public StampController(LoggedUser loggedUser, StampService stampService) {
        this.loggedUser = loggedUser;
        this.stampService = stampService;
    }

    @GetMapping("/stamp/add")
    public ModelAndView add(@ModelAttribute("addStampBindingModel") AddStampBindingModel addStampBindingModel) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/login");
        }

        return new ModelAndView("add-stamp");
    }

    @PostMapping("/stamp/add")
    public ModelAndView add(
            @ModelAttribute("addStampBindingModel") @Valid AddStampBindingModel addStampBindingModel,
            BindingResult bindingResult) {

        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/login");
        }

        if (bindingResult.hasErrors()) {
            return new ModelAndView("add-stamp");
        }

        boolean isCreated = stampService.add(addStampBindingModel);

        if (!isCreated) {
            ModelAndView modelAndView = new ModelAndView("add-stamp");
            modelAndView.addObject("addStampError", true);

            return modelAndView;
        }

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/stamp/wishlist/add/{id}")
    public ModelAndView toWishlist(@PathVariable("id") Long id) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/login");
        }

        stampService.toWishlist(id);

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/stamp/wishlist/remove/{id}")
    public ModelAndView removeFromWishlist(@PathVariable("id") Long id) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/login");
        }

        stampService.removeFromWishlist(id);

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/stamp/buy")
    public ModelAndView buy() {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/login");
        }

        stampService.buy();

        return new ModelAndView("redirect:/home");
    }
}
