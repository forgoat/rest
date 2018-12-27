package com.rest.support;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Token {
    public static final String SECRET = "JKKLJOoasdlfj";

    public static String setToken(UserInfo info) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletResponse response = requestAttributes.getResponse();

        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token=null;

        // build token
        // param backups {iss:Service, aud:APP}
        try {
        token = JWT.create().withHeader(map) // header
                .withIssuer("REST")
                .withExpiresAt(new Date(System.currentTimeMillis() + 2 * 60 * 60 * 100))
                .withClaim("userId", info.getUserId())
                .withClaim("userType", info.getUserType())
                .withClaim("userName", info.getUserName())
                .sign(Algorithm.HMAC256(SECRET));
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assert response != null;
        response.setHeader("Authorization", "Bearer " + token);
        return token;
    }

    public static UserInfo getToken() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String header = request.getHeader("Authorization");
        if (header == null) return null;
        String token = header.replace("Bearer ", "");
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                    .withIssuer("REST").build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new UsernameIsExitedException("无效的令牌");
        }

        Map<String, Claim> claims = jwt.getClaims();

        Claim userIdClaim = claims.get("userId");
        if (null == userIdClaim || userIdClaim.asInt() == 0) {
            throw new UsernameIsExitedException("无效的令牌");
        }
        Claim userNameClaim = claims.get("userName");
        if (null == userNameClaim || StringUtils.isEmpty(userNameClaim.asString())) {
            throw new UsernameIsExitedException("无效的令牌");
        }

        Claim userTypeClaim = claims.get("userType");
        if (null == userTypeClaim || StringUtils.isEmpty(userTypeClaim.asString())) {
            throw new UsernameIsExitedException("无效的令牌");
        }

        return new UserInfo(userIdClaim.asLong(), userNameClaim.asString(), userTypeClaim.asString());
    }
}
