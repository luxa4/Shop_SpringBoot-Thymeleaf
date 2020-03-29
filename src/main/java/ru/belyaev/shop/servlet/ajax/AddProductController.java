// Created by Vologda developer.
// Date: 03.11.2019
// Time: 17:53

package ru.belyaev.shop.servlet.ajax;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.belyaev.shop.form.ProductForm;
import ru.belyaev.shop.model.ShoppingCart;
import ru.belyaev.shop.servlet.AbstractController;
import ru.belyaev.shop.util.SessionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AddProductController extends AbstractController {

    private static final long serialVersionUID = 5023867691534917359L;
    private static final Logger LOGGER = LoggerFactory.getLogger(AddProductController.class);

    @GetMapping("/ajax/json/product/add")
    public ShoppingCart addProductToCart(HttpServletRequest req, HttpServletResponse resp,
                                 @RequestParam(name = "idProduct") String idProduct,
                                 @RequestParam(name = "count") String count)  {

        ProductForm productForm = createProductForm(idProduct, count); // Get Product
        ShoppingCart shoppingCart = SessionUtil.getCurrentShoppingCart(req); // Get ShoppingCart
        orderService.addProductToShoppingCart(productForm, shoppingCart); // Add product in Cart
//      Return to Ajax JSON Object ShoppingCart
        return shoppingCart;
    }
}
