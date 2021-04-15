package com.edu.learn.controller;
import com.edu.learn.config.ConfigProperty;
import com.edu.learn.dao.model.YhbModel;
import com.edu.learn.service.YhService;
import com.wxy.demo.service.QiniuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest/edu")
public class IndexController {

    private static final transient Logger log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private YhService service;

    @Autowired
    private ConfigProperty configProperty;

    @Autowired
    QiniuService qiniuService;

    @RequestMapping("/tologin")
    public ModelAndView tologin(String name,String pwt){
        qiniuService.initService();
        return  new ModelAndView("login");
    }


    @RequestMapping("/index")
    public ModelAndView getIndex(){
//        System.out.println("输出学校===================="+shool);
        return  new ModelAndView("index");
    }





    @RequestMapping("/add")
    public ModelAndView add(){
        YhbModel model = new YhbModel();
        model.setName("詹十三");
        model.setPwt("1111");
        model.setPerms("yh:add");
        service.inserYh(model);
        return  new ModelAndView("add");
    }

    @RequestMapping("/update")
    public ModelAndView update(){
        return  new ModelAndView("update");
    }


    @RequestMapping("/login")
    public ModelAndView login(@RequestParam("name") String name, @RequestParam("pwt")String pwt){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token =  new UsernamePasswordToken(name,pwt);
        try {
            subject.login(token);
            return  new ModelAndView("index") ;
        } catch (UnknownAccountException uae) {
            log.info("There is no user with username of " + token.getPrincipal());
            return  new ModelAndView("login");
        } catch (IncorrectCredentialsException ice) {
            log.info("Password for account " + token.getPrincipal() + " was incorrect!");
            return  new ModelAndView("login");
        } catch (LockedAccountException lae) {
            log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                    "Please contact your administrator to unlock it.");
            return  new ModelAndView("login");
        }
    }

    @RequestMapping("/unauthorize")
    public String unauthorize()
    {
        return  "未授权";
    }
}
