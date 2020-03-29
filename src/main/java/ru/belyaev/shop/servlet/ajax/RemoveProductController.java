/*
 * Created by Vologda Developer
 * Date: 11.01.2020
 * Time: 0:45
 */

package ru.belyaev.shop.servlet.ajax;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import ru.belyaev.shop.Constants;
import ru.belyaev.shop.form.ProductForm;
import ru.belyaev.shop.model.ShoppingCart;
import ru.belyaev.shop.servlet.AbstractController;
import ru.belyaev.shop.util.SessionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class RemoveProductController extends AbstractController {
    private static final long serialVersionUID = -3046216247699203961L;
    private static final Logger LOGGER = LoggerFactory.getLogger(RemoveProductController.class);

    @GetMapping("/ajax/json/product/remove")
    protected ShoppingCart processProductForm(ProductForm form, HttpServletRequest req, HttpServletResponse resp) {
        ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute(Constants.CURRENT_SHOPPING_CART);
        orderService.removeProductFromShoppingCart(form, shoppingCart);
        if (shoppingCart.getItems().isEmpty()) {
            SessionUtil.clearCurrentShoppingCart(req, resp);
        } else {
            String cookieValue = orderService.serializeShoppingCart(shoppingCart);
            SessionUtil.updateCurrentShoppingCartCookie(cookieValue, resp);
        }
        return shoppingCart;
    }
}
