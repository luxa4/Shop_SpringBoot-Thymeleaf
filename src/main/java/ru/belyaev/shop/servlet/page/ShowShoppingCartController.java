// Created by Vologda developer.
// Date: 15.10.2019
// Time: 8:34

package ru.belyaev.shop.servlet.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.belyaev.shop.servlet.AbstractController;
import ru.belyaev.shop.util.SessionUtil;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ShowShoppingCartController extends AbstractController {
    private static final long serialVersionUID = 2057475736109395575L;

    @GetMapping("/shopping-cart")
    protected String showShoppingCart (HttpServletRequest req, Model model) {
        // Verify if SoppingCart is null, we redirect to /products
        if (SessionUtil.isCurrentShoppingCartCreated(req)) {
            model.addAttribute("showActionColumn", "true");
            model.addAttribute("CURRENT_PAGE", "pages/page/shopping-cart.html");
            model.addAttribute("PAGE", "shoppingCart");
            return "pages/page-template";
        } else {
            return "redirect:/products";
        }
    }
}
