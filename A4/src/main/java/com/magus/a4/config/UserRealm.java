package com.magus.a4.config;


import com.magus.a4.dao.UserMapper;
import com.magus.a4.pojo.User;
import com.magus.a4.utils.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//自定义的Realm
@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserMapper userInfoMapper;

    /**
     * 该方法是为了判断这个主体能否被本Realm处理，判断的方法是查看token是否为同一个类型
     * @param authenticationToken
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof JwtShiroToken;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String token = principalCollection.toString();
        //获取unionid
        String unionid = jwtUtil.getWxUnionidByToken(token);
        //查询用户信息
        User user = userInfoMapper.selectByPrimaryKey(unionid);

        String roleName = user.getRole();
        info.addRole(roleName);

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了=>授权doGetAuthenticationInfo");
        String token = authenticationToken.getCredentials().toString();
        //验证token
        if (!jwtUtil.verifyToken(token)){
            throw new AuthenticationException("token失效了");
        }

        return new SimpleAuthenticationInfo(token, token, getName());
    }

    /**
     * 注意坑点 : 密码校验 , 这里因为是JWT形式,就无需密码校验和加密,直接让其返回为true(如果不设置的话,该值默认为false,即始终验证不通过)
     */
    @Override
    public CredentialsMatcher getCredentialsMatcher() {
        return (token, info) -> true;
    }

}
