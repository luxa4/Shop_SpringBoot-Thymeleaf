package ru.belyaev.shop.servlet.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.belyaev.shop.servlet.AbstractController;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ErrorController extends AbstractController {

    @GetMapping("/error")
    protected String showErrorPage(Model model) {
        model.addAttribute("statusCode",HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        model.addAttribute("CURRENT_PAGE", "pages/page/error.html");
        model.addAttribute("PAGE", "error");
        return "pages/page-template";
    }
}
