/*
 * Created by Vologda Developer
 * Date: 21.03.2020
 * Time: 15:34
 */

package ru.belyaev.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.belyaev.shop.entity.Account;
import ru.belyaev.shop.entity.Role;
import ru.belyaev.shop.repositories.AccountDao;
import ru.belyaev.shop.repositories.RoleDao;
import ru.belyaev.shop.service.UserService;
import ru.belyaev.shop.util.SessionUtil;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    AccountDao accountDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    HttpSession session;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountDao.findAccountByName(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }

        SessionUtil.setCurrentAccount(session, account);
        return new org.springframework.security.core.userdetails.User(account.getName(),
                account.getPassword(), mapRolesAuthorities(account.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

    @Override
    public Account findAccountByName(String name) {
       return accountDao.findAccountByName(name);
    }

    @Override
    @Transactional
    public void save(Account account) {
        Account theAccount = new Account();
        theAccount.setName(account.getName());
        theAccount.setEmail(account.getEmail());
        theAccount.setPassword(passwordEncoder.encode(account.getPassword()));
        theAccount.setRoles(Arrays.asList(roleDao.findRoleByRole("ROLE_USER")));
        accountDao.save(theAccount);
    }

    @Override
    public Account findAccountByEmail(String email) {
        return accountDao.findAccountByEmail(email);
    }
}
