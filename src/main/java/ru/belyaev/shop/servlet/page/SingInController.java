// Created by Vologda developer.
// Date: 12.11.2019
// Time: 20:22

package ru.belyaev.shop.servlet.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.belyaev.shop.Constants;
import ru.belyaev.shop.servlet.AbstractController;
import ru.belyaev.shop.util.RoutingUtil;
import ru.belyaev.shop.util.SessionUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;



@Controller
public class SingInController  extends AbstractController {
    private static final long serialVersionUID = -7565542222448593595L;
    private static final Logger LOGGER = LoggerFactory.getLogger(SingInController.class);

    @GetMapping("/sign-in")
    protected void signInGet (HttpServletResponse resp, HttpServletRequest req) throws ServletException, IOException {
        LOGGER.info("-->>> Launching controller sign-in method Get");
        if (SessionUtil.isCurrentAccountCreated(req)) {
            RoutingUtil.redirect("/my-orders", req, resp);
        } else {
            LOGGER.info("-->>> RoutingUtil.forwardToPage -> sign-in.jsp");
            RoutingUtil.forwardToPage("loginPage.html", "mySignIn", req, resp);
        }
    }

    @PostMapping("/sign-in")
    protected RedirectView signInPost (HttpServletRequest req) throws UnsupportedEncodingException {
        LOGGER.info("-->>> Launching controller sign-in method POST");
        if (SessionUtil.isCurrentAccountCreated(req)) {
            return new RedirectView("/my-orders");
        } else {
//            String targetUrl = URLEncodeTag(success_url);
//            req.getSession().setAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN, targetUrl);
//            System.out.println("Атрибут SUCCESS_REDIRECT_URL_AFTER_SIGNIN равен - "+ req.getSession().getAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN));
            return new RedirectView(socialService.getAuthorizeUrl());
        }
    }

    private String URLEncodeTag (String url) throws UnsupportedEncodingException {
            return URLEncoder.encode(url, "UTF-8");
    }
}
