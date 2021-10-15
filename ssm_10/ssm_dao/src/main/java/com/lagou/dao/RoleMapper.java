package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;


import java.util.List;

public interface RoleMapper {
    /**
     * 查询所有菜单的父子菜单
     * @param role
     * @return
     */
    public List<Role> findAllRole(Role role);

    /**
     * 根据角色id查询菜单信息
     * @param roleId
     * @return
     */
    public List<String> findMenuByRoleId(Integer roleId);


    /**
     * 根据roleID清空中间表的关联关系
     */
    void deleteRoleContextMenu(Integer rid);

    /**
     * 角色和菜单关联
     */
    void RoleContextMenu(Role_menu_relation role_menu_relation);

    /**
     * 删除角色
     */
void deleteRole (Integer id);
}
