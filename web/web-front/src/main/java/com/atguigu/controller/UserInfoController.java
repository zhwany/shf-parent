package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.UserInfo;
import com.atguigu.result.Result;
import com.atguigu.result.ResultCodeEnum;
import com.atguigu.service.UserInfoService;
import com.atguigu.util.MD5;
import com.atguigu.util.ValidateCodeUtils;
import com.atguigu.vo.LoginVo;
import com.atguigu.vo.RegisterVo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author mabo
 * @Date 2022/7/29 20:38
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController extends BaseController {
    @Reference
    private UserInfoService userInfoService;

    @PostMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo, HttpServletRequest request) {
        String nickName = registerVo.getNickName();
        String password = registerVo.getPassword();
        String phone = registerVo.getPhone();
        String code = registerVo.getCode();

        //校验请求参数是否为空，空则返回相应错误信息
        if (StringUtils.isEmpty(nickName) || StringUtils.isEmpty(phone) || StringUtils.isEmpty(password) || StringUtils.isEmpty(code)) {
            return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        }

        //生成手机验证码
        String currentCode = (String) request.getSession().getAttribute("CODE");
        if (!code.equals(currentCode)) {
            return Result.build(null, ResultCodeEnum.CODE_ERROR);
        }

        //校验该手机是否已经注册
        UserInfo userInfo = userInfoService.getByPhone(phone);
        if (null != userInfo) {
            return Result.build(null, ResultCodeEnum.PHONE_REGISTER_ERROR);
        }

        userInfo = new UserInfo();
        userInfo.setNickName(nickName);
        userInfo.setPassword(MD5.encrypt(password));
        userInfo.setPhone(phone);
        userInfo.setStatus(1);

        userInfoService.insert(userInfo);
        return Result.ok();
    }

    //"/userInfo/sendCode/'+this.registerVo.phone"
    @GetMapping("sendCode/{phoneCode}")
    public Result sendCode(@PathVariable String phoneCode, HttpServletRequest request) {
        String code = ValidateCodeUtils.generateValidateCode4String(6);
        System.out.println(code);
        request.getSession().setAttribute("CODE", code);
        return Result.ok(code);
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo, HttpServletRequest request) {
        //获取登录页面输入的电话和密码
        String phone = loginVo.getPhone();
        String password = loginVo.getPassword();

        //数据校验
        //1、校验前端数据是否为空
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)) {
            return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        }
        //2、按照phone在数据库中进行查询，校验用户是否已经注册
        UserInfo userInfo = userInfoService.getByPhone(phone);
        if (userInfo == null) {
            return Result.build(null, ResultCodeEnum.ACCOUNT_ERROR);
        }
        //3、判断用户输入的密码是否正确(密文的比较)
        if (!userInfo.getPassword().equals(MD5.encrypt(password))) {
            return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        }
        //4、判断该用户是否处于禁用状态（status）
        if (userInfo.getStatus() == 0) {
            return Result.build(null, ResultCodeEnum.ACCOUNT_LOCK_ERROR);
        }
        //5、校验成功，返回ok，将此时登录的用户昵称传回给页面
        //不仅用于登录失败后页面登录信息的回显, 还用于前端所有界面关于当前登录用户id的查询, 关注房源时用于拦截器校验用户是否登录
        request.getSession().setAttribute("USER", userInfo);
        //登录成功后携带用户名，用于在前端首页的展示
        Map map = new HashMap<>();
        map.put("nickName", userInfo.getNickName());
        return Result.ok(map);
    }

    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        request.getSession().removeAttribute("USER");
        return Result.ok();
    }
}
