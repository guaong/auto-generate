package guaong.permission.permission.controller;

import guaong.permission.permission.bean.PermissionTree;
import guaong.permission.permission.entity.Permission;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import guaong.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import guaong.permission.permission.service.IPermissionService;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @GetMapping(value = "/list")
    public Result<?> list() {
        return Result.OK(buildTree());
    }

    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Permission permission) {
        permissionService.save(permission);
        return Result.OK("添加成功！");
    }

    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.eq("pid", id);
        if (permissionService.list(wrapper).size() > 0) return Result.ERROR("当前选择删除的菜单存在子菜单!");
        permissionService.removeById(id);
        return Result.OK("删除成功!");
    }

    @PutMapping(value = "/update")
    public Result<?> edit(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return Result.OK("更新成功！");
    }

    @GetMapping("/getPermissionList")
    public Result<?> getPermissionList(){
        return Result.OK(buildTree());
    }

    /**
     * 用于树型下拉框 多了一个'无'
     * @return
     */
    @GetMapping("/getPermissionSelectTreeList")
    public Result<?> getPermissionSelectTreeList(){
        List<PermissionTree> list = buildTree();
        if (list != null)
            list.add(0, new PermissionTree(0,0,0,"无"));
        return Result.OK(list);
    }

    private List<PermissionTree> buildTree() {
        List<PermissionTree> treeList = permissionService.getPermissionList();
        if (treeList != null) {
            List<PermissionTree> result = new ArrayList<>();
            Map<Integer, PermissionTree> treeMap =
                    treeList.stream().collect(Collectors.toMap(PermissionTree::getId, node -> node));
            for (Integer i : treeMap.keySet()
            ) {
                PermissionTree node = treeMap.get(i);
                int pid = node.getPid();
                if (pid > 0){
                    PermissionTree pNode = treeMap.get(pid);
                    if (pNode != null){
                        List<PermissionTree> children = pNode.getChildren();
                        if (children == null){
                            children = new ArrayList<>();
                            pNode.setChildren(children);
                        }
                       children.add(node);
                    }
                }else {
                    result.add(node);
                }
            }
            return result;
        }
        return null;
    }

}