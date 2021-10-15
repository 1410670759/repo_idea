package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.Role_menu_relation;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Role;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    /**
     * 根据角色id查询对应菜单信息
     */

    @Override
    public List<String> findMenuByRoleId(Integer roleId) {
        List<String> list = roleMapper.findMenuByRoleId(roleId);
        return list;
    }



    /**
     *角色分配菜单，操作中间表信息
     */
    @Override
    public void RoleContextMenu(RoleMenuVo roleMenuVo) {
        //清空中间表关联信息
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        for (Integer mid : roleMenuVo.getMenuIdList()) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedBy("system");
            role_menu_relation.setCreatedTime(new Date());
            role_menu_relation.setUpdatedTime(new Date());
            roleMapper.RoleContextMenu(role_menu_relation);

        }
    }

    @Override
    public void deleteRole(Integer id) {
        //调用根据roleID清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(id);
        roleMapper.deleteRole(id);
    }


}
