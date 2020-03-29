package ru.belyaev.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.belyaev.shop.service.OrderService;
import ru.belyaev.shop.service.ProductService;
import ru.belyaev.shop.service.SocialService;

@Service
public class ServiceManager {

    @Autowired
    public SocialService socialService;

    @Autowired
    public ProductService productService;

    @Autowired
    public OrderService orderService;

    public ServiceManager() {}
}












