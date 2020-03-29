/*
 * Created by Vologda developer.
 * User: Alexander
 * Date: 25.03.2020
 * Time: 15:20
 */

package ru.belyaev.shop.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.belyaev.shop.service.ProductService;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionListener.class);

    @Autowired
    private ProductService productService;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute("CATEGORY_LIST", productService.listAllCategories());
        se.getSession().setAttribute("PRODUCER_LIST",  productService.listAllProducers());
        LOGGER.info("Session created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
