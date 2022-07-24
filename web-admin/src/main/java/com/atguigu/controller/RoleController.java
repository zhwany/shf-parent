package com.atguigu.controller;

import com.atguigu.base.BaseController;
import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author mabo
 * @Date 2022/7/19 0:42
 */
@Controller
@RequestMapping(value="/role")
@SuppressWarnings({"unchecked", "rawtypes"})
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    private final static String LIST_ACTION = "redirect:/role";
    private final static String PAGE_INDEX = "role/index";
    private final static String PAGE_CREATE = "role/create";
    private final static String PAGE_EDIT = "role/edit";
    private final static String PAGE_SUCCESS = "common/successPage";

    @RequestMapping
    public String index(ModelMap model, HttpServletRequest request) {
        Map<String,Object> filters = getFilters(request);
        PageInfo<Role> page = roleService.findPage(filters);

        model.addAttribute("page", page);
        model.addAttribute("filters", filters);
        return PAGE_INDEX;
    }

    @GetMapping("/create ")
    public String create(ModelMap model) {
        return PAGE_CREATE;
    }

    @PostMapping("/save")
    public String save(Role role) {
        roleService.insert(role);
        return PAGE_SUCCESS;
    }

    @GetMapping("/edit/{id}")
    public String edit(ModelMap model,@PathVariable Long id) {
        Role role = roleService.getById(id);
        model.addAttribute("role",role);
        return PAGE_EDIT;
    }

    @PostMapping(value="/update")
    public String update(Role role) {

        roleService.update(role);
        return PAGE_SUCCESS;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        return LIST_ACTION;
    }
}


//改造前
//@Controller
//@RequestMapping("/role")
//public class RoleController {
//
//    @Autowired
//    private RoleService roleService;
//
//
//    private final static String PAGE_INDEX = "role/index";
//    private final static String PAGE_CREATE = "role/create";
//    private final static String LIST_ACTION = "redirect:/role";
//    private final static String PAGE_SUCCESS = "common/successPage";
//    private final static String PAGE_EDIT = "role/edit";
//
//
////    /**
////     * 列表
////     * @param model
////     * @return
////     */
////    @RequestMapping
////    public String index(ModelMap model) {
////        List<Role> list = roleService.findAll();
////
////        model.addAttribute("list", list);
////        return PAGE_INDEX;
////    }
//
//    @RequestMapping("/create")
//    public String create() {
//        return PAGE_CREATE;
//    }
//
////    @RequestMapping("/save")
////    public String save(Role role, HttpServletRequest request) {
////        roleService.insert(role);
////        return LIST_ACTION;
////    }
//
//    @RequestMapping("/save")
//    public String save(Role role) {
//        roleService.insert(role);
//        return PAGE_SUCCESS;
//    }
//
//    @RequestMapping("/edit/{id}")
//    public String edit(ModelMap model,@PathVariable Long id) {
//        Role role = roleService.getById(id);
//        model.addAttribute("role",role);
//        return PAGE_EDIT;
//    }
//
//    @RequestMapping("/update")
//    public String update(Role role) {
//        roleService.update(role);
//        return PAGE_SUCCESS;
//    }
//
//    @RequestMapping("/delete/{id}")
//    public String delete(@PathVariable Long id) {
//        roleService.delete(id);
//        return LIST_ACTION;
//    }
//
//
//    @RequestMapping
//    public String index(ModelMap model, HttpServletRequest request) {
//        Map<String,Object> filters = getFilters(request);
//        PageInfo<Role> page = roleService.findPage(filters);
//
//        model.addAttribute("page", page);
//        model.addAttribute("filters", filters);
//        return PAGE_INDEX;
//    }
//
//    /**
//     * 封装页面提交的分页参数及搜索条件
//     * @param request
//     * @return
//     */
//    private Map<String, Object> getFilters(HttpServletRequest request) {
//        Enumeration<String> paramNames = request.getParameterNames();
//        Map<String, Object> filters = new TreeMap();
//        while(paramNames != null && paramNames.hasMoreElements()) {
//            String paramName = (String)paramNames.nextElement();
//            String[] values = request.getParameterValues(paramName);
//            if (values != null && values.length != 0) {
//                if (values.length > 1) {
//                    filters.put(paramName, values);
//                } else {
//                    filters.put(paramName, values[0]);
//                }
//            }
//        }
//        if(!filters.containsKey("pageNum")) {
//            filters.put("pageNum", 1);
//        }
//        if(!filters.containsKey("pageSize")) {
//            filters.put("pageSize", 10);
//        }
//
//        return filters;
//    }
//}
