// Created by Vologda developer.
// Date: 20.10.2019
// Time: 17:05

package ru.belyaev.shop.servlet.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.belyaev.shop.Constants;
import ru.belyaev.shop.entity.Product;
import ru.belyaev.shop.servlet.AbstractController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProductsByCategoryController extends AbstractController {
    private static final long serialVersionUID = 7275592594451636455L;
    private static final int SUBSTRING_INDEX = "/products".length();

    @RequestMapping(value = "/products/*", method = RequestMethod.GET)
    protected String showProductByCategory(Model model, HttpServletRequest req) {
        String categoryUrl = req.getRequestURI().substring(SUBSTRING_INDEX);
        List<Product> products = productService.listProductsByCategory(categoryUrl, 1, Constants.MAX_PRODUCTS_PER_HTML_PAGE);
        int countProduct = productService.countAllProductByCategory(categoryUrl);
        model.addAttribute("pageCount", pageCount(countProduct,Constants.MAX_PRODUCTS_PER_HTML_PAGE));
        model.addAttribute("products", products);
        model.addAttribute("selectedCategoryUrl", categoryUrl); // для того, чтобы подсветить выбранную категорию в списке ASIDE
        model.addAttribute("CURRENT_PAGE", "pages/page/products.html");
        model.addAttribute("PAGE", "products");
        return "pages/page-template";
    }
}
