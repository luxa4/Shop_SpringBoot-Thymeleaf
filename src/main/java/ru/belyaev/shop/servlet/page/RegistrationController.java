/*
 * Created by Vologda Developer
 * Date: 28.03.2020
 * Time: 17:52
 */


package ru.belyaev.shop.servlet.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.belyaev.shop.entity.Account;
import ru.belyaev.shop.service.UserService;


@Controller
@RequestMapping("/register")
public class RegistrationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model theModel) {
        theModel.addAttribute("crmUser", new Account());
        theModel.addAttribute("CURRENT_PAGE", "pages/page/registration-form.html");
        theModel.addAttribute("PAGE", "registration-form");
        return "pages/page-template";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @ModelAttribute("crmUser") Account account,
            BindingResult theBindingResult,
            Model theModel) {

        theModel.addAttribute("CURRENT_PAGE", "pages/page/successRegistration.html");
        theModel.addAttribute("PAGE", "successRegistration");
        String userName = account.getName();
        String email = account.getEmail();
        LOGGER.info("Processing registration form for: " + userName);

        // form validation
        if (theBindingResult.hasErrors()){
            return "pages/page-template";
        }

        // check the database if user already exists
        Account existingName = userService.findAccountByName(userName);
        Account existingEmail = userService.findAccountByEmail(email);
        if (existingName != null || existingEmail != null){
            theModel.addAttribute("crmUser", new Account());
            theModel.addAttribute("registrationError", "User name or email already exists.");
            theModel.addAttribute("CURRENT_PAGE", "pages/page/registration-form.html");
            theModel.addAttribute("PAGE", "registration-form");
            LOGGER.warn("User name or email already exists.");
            return "pages/page-template";
        }

        // create user account
        userService.save(account);

        LOGGER.info("Successfully created user: " + userName);
//
        return "pages/page-template";
    }
}
