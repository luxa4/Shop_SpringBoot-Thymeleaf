// Created by Vologda developer.
// Date: 20.10.2019
// Time: 17:16

package ru.belyaev.shop.servlet.ajax;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.belyaev.shop.Constants;
import ru.belyaev.shop.entity.Product;
import ru.belyaev.shop.form.SearchForm;
import ru.belyaev.shop.servlet.AbstractController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SearchResultMoreController extends AbstractController {
    private static final long serialVersionUID = 6725071099705912476L;

    // Обработка Ajax запроса при нажатии на кнопку LoadMore
    @RequestMapping(value = "/ajax/html/more/search", method = RequestMethod.GET)
    protected String showMoreResualt(@RequestParam(name = "query", required = false, defaultValue = "") String query,
                         @RequestParam(name = "category", required = false) String[] category,
                         @RequestParam(name = "producer", required = false) String[] producer,
                         HttpServletRequest req, Model model) {
        SearchForm searchForm = createSearchForm(query, category, producer);
        List<Product> products = productService.ListProductBySearchForm(searchForm, getPage(req), Constants.MAX_PRODUCTS_PER_HTML_PAGE,
                productService.getListCategoriesId(), productService.getListProducersId());
        model.addAttribute("products", products);
        return "pages/fragment/product-list";
    }
}
