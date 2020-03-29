/*
 * Created by Vologda Developer
 * Date: 21.03.2020
 * Time: 23:09
 */

package ru.belyaev.shop.servlet.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class
ShowMyLoginPage {

    @GetMapping(value = "/showMyLoginPage")
    public String showMyLoginPage(Model model) {
        model.addAttribute("CURRENT_PAGE", "pages/page/loginPage.html");
        model.addAttribute("PAGE", "loginPage");
        return "pages/page-template";
    }
}
