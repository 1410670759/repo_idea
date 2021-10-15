package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 查询所有角色
     * @param role
     * @return
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(Role role){
        List<Role> allRole = roleService.findAllRole(role);
        return new ResponseResult(true,200,"相应成功",allRole);
    }

    /**
     * 查询所有菜单列表
     * @param
     * @return
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid(){
        //-1表示查询所有的父zi级菜单
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);
        HashMap<String, Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);
        return new ResponseResult(true,200,"查询所有的父子菜单信息成功",map);

    }

    /**
     * 根据角色id查询对应菜单信息
     * @return
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        List<String> list = roleService.findMenuByRoleId(roleId);
        return new ResponseResult(true,200,"查询所有的父子菜单信息成功",list);
    }



    /**
     * 角色和菜单关联
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){

        roleService.RoleContextMenu(roleMenuVo);
        return new ResponseResult(true,200,"响应成功",null);

    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole( Integer id ){
        roleService.deleteRole(id);
        return new ResponseResult(true,200,"删除角色成功！",null);
    }
}
