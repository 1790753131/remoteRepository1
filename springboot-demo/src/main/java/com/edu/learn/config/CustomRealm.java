package com.edu.learn.config;

import com.edu.learn.dao.model.YhbModel;
import com.edu.learn.service.YhService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private YhService yhService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权===================="+principalCollection);
        //用户授权
        SimpleAuthorizationInfo authorizationInfo =  new SimpleAuthorizationInfo();
        //从数据库获取用户权限
        Subject subject =  SecurityUtils.getSubject();
        YhbModel  yhbModel = (YhbModel)subject.getPrincipal();
        authorizationInfo.addStringPermission(yhbModel.getPerms());
        System.out.println("该用户权限是===================="+yhbModel.getPerms());
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证===================="+authenticationToken);
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        String name = usernamePasswordToken.getUsername();
        YhbModel model= new YhbModel();
        model.setName(name);
        YhbModel yhbModel = yhService.getModel(model);
        if(yhbModel == null){
            return null;
        }
        //在认证这里放入用户对象，在授权中使用获取用户信息
        return new SimpleAuthenticationInfo(yhbModel,yhbModel.getPwt(),"");
    }
}
