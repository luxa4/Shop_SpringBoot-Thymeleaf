package ru.belyaev.shop.servlet.ajax;

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
public class AllProductsMoreController extends AbstractController {

    @RequestMapping(value = "/ajax/html/more/products", method = RequestMethod.GET)
    protected String doGet(HttpServletRequest req, Model model) {
        List<Product> products = productService.listAllProduct(getPage(req), Constants.MAX_PRODUCTS_PER_HTML_PAGE);
        model.addAttribute("products", products);
        return "pages/fragment/product-list";
    }
}
