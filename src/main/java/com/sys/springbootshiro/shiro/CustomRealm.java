package com.sys.springbootshiro.shiro;

import com.sys.springbootshiro.beans.MyUsernamePasswordToken;
import com.sys.springbootshiro.beans.Permissions;
import com.sys.springbootshiro.beans.Role;
import com.sys.springbootshiro.beans.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * shiro realm---内存 simplerealm  配置文件 IniRealm   数据库 JdbcRealm
 * 1 认证
 * 2 授权
 * 3 可设置密码加密
 * 4 自定义session
 * 5
 *
 */
public class CustomRealm extends AuthorizingRealm {
    //添加角色和权限
    SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       // Principal principal = (Principal) getAvailablePrincipal(principalCollection);
        //获取登录用户名
        if(simpleAuthorizationInfo.getRoles() !=null){
            return simpleAuthorizationInfo ;
        }
        //获取 UsernamePasswordToken
        MyUsernamePasswordToken name = (MyUsernamePasswordToken) principalCollection.getPrimaryPrincipal();
        //或者
        MyUsernamePasswordToken name2 = (MyUsernamePasswordToken) getAvailablePrincipal(principalCollection);

        //模拟数据库查询权限
        //根据用户名去数据库查询用户信息
        User user = new User("sys");

        for (Role role : user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            //添加权限
            for (Permissions permissions : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permissions.getPermissionsName());
            }
        }
        return simpleAuthorizationInfo;
    }

    //认证
    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        MyUsernamePasswordToken myUsernamePasswordToken = (MyUsernamePasswordToken) authenticationToken;
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String name = myUsernamePasswordToken.getUsername().toString();
        //模拟数据库查询用户信息
        User user = new User("sys");
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            //shiro 会自动与传过来的值进行比较
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(myUsernamePasswordToken, user.getPassword(), getName());
            return simpleAuthenticationInfo; //不是返回认证结果，只是返回需要认证的信息
        }
    }
    //设置加密方式
    /*@Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("SHA-1");
        matcher.setHashIterations(1024);
        // 设置自定义认证加密方式
        super.setCredentialsMatcher(matcher);
    }*/
}
