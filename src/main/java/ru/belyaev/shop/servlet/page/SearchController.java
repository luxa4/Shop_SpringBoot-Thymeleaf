// Created by Vologda developer.
// Date: 20.10.2019
// Time: 17:08

package ru.belyaev.shop.servlet.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.belyaev.shop.Constants;
import ru.belyaev.shop.entity.Product;
import ru.belyaev.shop.form.SearchForm;
import ru.belyaev.shop.servlet.AbstractController;
import java.util.List;

@Controller
public class SearchController extends AbstractController {
    private static final long serialVersionUID = 509924737698931116L;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    protected String makeSearch (@RequestParam(name = "query", required = false, defaultValue = "") String query,
                                 @RequestParam(name = "category", required = false) String[] category,
                                 @RequestParam(name = "producer", required = false) String[] producer,
                                 Model model) {

        SearchForm searchForm = createSearchForm(query, category, producer);
        int countProduct = productService.countProductBySearchFrom(searchForm, productService.getListCategoriesId(), productService.getListProducersId() );
        List<Product> products = productService.ListProductBySearchForm(searchForm, 1, Constants.MAX_PRODUCTS_PER_HTML_PAGE,
                productService.getListCategoriesId(), productService.getListProducersId());
        model.addAttribute("pageCount", pageCount(countProduct, Constants.MAX_PRODUCTS_PER_HTML_PAGE));
        model.addAttribute("products", products);
        model.addAttribute("searchForm", searchForm);
        model.addAttribute("productCount", countProduct); // quantity product that was found
        model.addAttribute("CURRENT_PAGE", "pages/page/search-result.html");
        model.addAttribute("PAGE", "search-result");
        return "pages/page-template";
    }
}
