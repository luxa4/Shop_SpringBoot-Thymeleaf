/*
 * Created by Vologda Developer
 * Date: 28.03.2020
 * Time: 18:40
 */

package ru.belyaev.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.belyaev.shop.entity.Role;


public interface RoleDao extends JpaRepository<Role, Integer> {

    Role findRoleByRole(String nameRole);
}
