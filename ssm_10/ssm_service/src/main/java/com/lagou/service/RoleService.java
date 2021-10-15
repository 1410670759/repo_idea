package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {

    public List<Role> findAllRole(Role role);

    /**
     * 根据角色id查询对应菜单信息
     * @param roleId
     * @return
     */
    public List<String> findMenuByRoleId(Integer roleId);

    /**
     *角色分配菜单，操作中间表信息
     */
    void RoleContextMenu(RoleMenuVo roleMenuVo);

    /**
     * 删除角色
     * @param id
     */
    void deleteRole(Integer id);
}
