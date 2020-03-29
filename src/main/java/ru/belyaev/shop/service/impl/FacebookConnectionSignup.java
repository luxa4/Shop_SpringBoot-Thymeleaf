///*
// * Created by Vologda Developer
// * Date: 29.03.2020
// * Time: 0:15
// */
//
//
//package ru.belyaev.shop.service.impl;
//
//import com.restfb.Connection;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import ru.belyaev.shop.entity.Account;
//import ru.belyaev.shop.service.UserService;
//
//@Service
//public class FacebookConnectionSignup implements ConnectionSignUp {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public String execute(Connection<?> connection) {
//        Account user = new Account();
//        user.setName(connection.getDisplayName());
//        user.setEmail(connection.get);
//        user.setPassword(randomAlphabetic(8));
//        userRepository.save(user);
//        return user.getUsername();
//    }
//}