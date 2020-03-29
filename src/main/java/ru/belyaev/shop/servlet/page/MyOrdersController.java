// Created by Vologda developer.
// Date: 16.11.2019
// Time: 12:03

package ru.belyaev.shop.servlet.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.belyaev.shop.Constants;
import ru.belyaev.shop.entity.Account;
import ru.belyaev.shop.entity.Order;
import ru.belyaev.shop.servlet.AbstractController;
import ru.belyaev.shop.util.SessionUtil;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MyOrdersController extends AbstractController {
    private static final long serialVersionUID = -3940675839945296526L;
    private static final Logger LOGGER = LoggerFactory.getLogger(MyOrdersController.class);

    @GetMapping("/my-orders")
    protected String doGet(HttpServletRequest req, Model model) {
        Account currentAccount = SessionUtil.getCurrentAccount(req);
        List<Order> orders = orderService.listMyOrders(currentAccount, 1, Constants.ORDERS_PER_PAGE);
        model.addAttribute("orders", orders);
        if (orders.size() == 0)
        model.addAttribute("orders", null);
        int orderCount = orderService.countMyOrders(currentAccount);
        model.addAttribute("pageCount", pageCount(orderCount, Constants.ORDERS_PER_PAGE));
        model.addAttribute("CURRENT_PAGE", "pages/page/my-orders.html");
        model.addAttribute("PAGE", "my-orders");
        return "pages/page-template";
    }
}

