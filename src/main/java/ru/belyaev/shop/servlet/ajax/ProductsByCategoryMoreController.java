// Created by Vologda developer.
// Date: 20.10.2019
// Time: 17:18

package ru.belyaev.shop.servlet.ajax;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.belyaev.shop.Constants;
import ru.belyaev.shop.entity.Product;
import ru.belyaev.shop.servlet.AbstractController;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductsByCategoryMoreController extends AbstractController {
    private static final long serialVersionUID = 5557217209869498166L;
    private static final int SUBSTRING_INDEX = "/ajax/html/more/products".length();

    @RequestMapping(value = "/ajax/html/more/products/*", method = RequestMethod.GET)
    protected String doGet(HttpServletRequest req, Model model) throws ServletException, IOException {
        String categoryUrl = req.getRequestURI().substring(SUBSTRING_INDEX);
        List<Product> products = productService.listProductsByCategory(categoryUrl, getPage(req), Constants.MAX_PRODUCTS_PER_HTML_PAGE);
        model.addAttribute("products", products);
        return "pages/fragment/product-list";
    }
}
