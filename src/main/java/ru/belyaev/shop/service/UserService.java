/*
 * Created by Vologda Developer
 * Date: 21.03.2020
 * Time: 15:34
 */

package ru.belyaev.shop.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.belyaev.shop.entity.Account;

public interface UserService extends UserDetailsService {

    Account findAccountByName(String name);

    Account findAccountByEmail(String email);

    void save(Account account, boolean FaceBook);
}
