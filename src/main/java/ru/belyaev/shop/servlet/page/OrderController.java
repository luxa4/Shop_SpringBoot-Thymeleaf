// Created by Vologda developer.
// Date: 16.11.2019
// Time: 12:01

package ru.belyaev.shop.servlet.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.belyaev.shop.entity.Order;
import ru.belyaev.shop.model.ShoppingCart;
import ru.belyaev.shop.servlet.AbstractController;
import ru.belyaev.shop.util.RoutingUtil;
import ru.belyaev.shop.util.SessionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Нажатие на кнопку "Make order" в корзине
@Controller
public class OrderController extends AbstractController {
    private static final long serialVersionUID = 175490084268122527L;
    private static final String CURRENT_MESSAGE = "CURRENT_MESSAGE";

    @GetMapping("/order")
    protected String doGet(HttpServletRequest req, Model model) {
        String message = (String) req.getSession().getAttribute("CURRENT_MESSAGE");
        req.getSession().removeAttribute(CURRENT_MESSAGE);
        model.addAttribute(CURRENT_MESSAGE, message);
        Order order = orderService.findOrderById(Long.parseLong(req.getParameter("id")), SessionUtil.getCurrentAccount(req));
        model.addAttribute("showActionColumn", "false");
        model.addAttribute("order", order);
        model.addAttribute("CURRENT_PAGE", "pages/page/order.html");
        model.addAttribute("PAGE", "order");
        return "pages/page-template";
    }

    @PostMapping("/order")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ShoppingCart shoppingCart = SessionUtil.getCurrentShoppingCart(req);
        long idOrder = orderService.makeOrder(shoppingCart, SessionUtil.getCurrentAccount(req));
        SessionUtil.clearCurrentShoppingCart(req, resp);
        req.getSession().setAttribute(CURRENT_MESSAGE, "Congratulations! Your order has been created successfully. Please wait our answer");
        RoutingUtil.redirect("/order?id="+ idOrder, req, resp);
    }
}
