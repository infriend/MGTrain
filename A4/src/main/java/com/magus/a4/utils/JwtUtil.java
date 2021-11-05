package com.magus.a4.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.magus.a4.dao.UserMapper;
import com.magus.a4.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Component
//@PropertySource("classpath:application.yml")
public class JwtUtil {
    @Autowired
    UserMapper userInfoMapper;

    /**
     * JWT 自定义密钥 在配置文件进行配置
     */
    private String secretKey = "dddfasfhu";;

    /**
     * JWT 过期时间值 3600/小时 这里设置为24个小时
     */
    private static final long EXPIRE_TIME = 3600 * 24;


    /**
     * 根据微信用户登陆信息创建 token
     * redis里面缓存的时间应该和jwt token的过期时间设置相同
     * @param user 用户信息
     * @return 返回 jwt token
     */
    public String createTokenByWxAccount(User user) {
        //JWT 随机ID,做为验证的key
        String jwtId = UUID.randomUUID().toString();
        //1 . 加密算法进行签名得到token
        //生成签名
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        //生成token
        String token = JWT.create()
                .withClaim("username", user.getUsername())
                .withClaim("jwt-id", jwtId)
                //JWT 配置过期时间的正确姿势，因为单位是毫秒，所以需要乘1000
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME * 1000))
                .sign(algorithm);
        //2 . Redis缓存JWT, 注 : 请和JWT过期时间一致
        //stringRedisTemplate.opsForValue().set("JWT-SESSION-" + jwtId, token, EXPIRE_TIME, TimeUnit.SECONDS);

        return token;
    }

    /**
     * 校验token是否正确
     *
     * @param token 密钥
     * @return 返回是否校验通过
     */
    public boolean verifyToken(String token) {
        try {
            if (token==null){
                return false;
            }
            String username = getWxUnionidByToken(token);
            String jwtId = getJwtIdByToken(token);

            if(username==null || jwtId==null){
                return false;
            }

            //查找用户信息
            User userDtos = userInfoMapper.selectByPrimaryKey(username);
            if (userDtos == null){
                return false;
            }

            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            //验证token
            verifier.verify(token);
            return true;
        } catch (Exception e) { //捕捉到任何异常都视为校验失败
            return false;
        }
    }

    /**
     * 从请求头中获取Token，并返回用户的unionid
     */
    public String getUnionid() {
        HttpServletRequest request = ServletUtil.getRequest();
        String token = request.getHeader("token");
        return getWxUnionidByToken(token);
    }

    /**
     * 根据Token获取wxUnionid(注意坑点 : 就算token不正确，也有可能解密出wxUnionid,同下)
     */
    public String getWxUnionidByToken(String token)  {
        return JWT.decode(token).getClaim("username").asString();
    }

    /**
     * 根据Token 获取jwt-id
     */
    public String getJwtIdByToken(String token)  {
        return JWT.decode(token).getClaim("jwt-id").asString();
    }

}
