package org.insysu.groceryproject.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.insysu.groceryproject.persistence.entity.User;
import org.insysu.groceryproject.persistence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by buress on 12/3/16.
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String currentName= (String)super.getAvailablePrincipal(principals);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user = this.userService.findByName(currentName);
        if(null != user) {
            if(user.isVIP()) {
                simpleAuthorizationInfo.addRole("vip");
            }
            simpleAuthorizationInfo.addRole("user");
            return simpleAuthorizationInfo;
        } else {
            return simpleAuthorizationInfo;
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        User user = this.userService.findByName(new String(token.getUsername()));
        if (null != user) {
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), user.getUsername());
            this.setSession("currentUser", user);
            return authenticationInfo;
        } else {
            return null;
        }
    }

    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }
}
