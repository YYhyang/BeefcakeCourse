package com.example.demo.sercurity;

import com.example.demo.entity.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
@Component
public class JwtService {
    private String secret="Beefcake";

    private static final Map<String,String> JWT_HEADER=
            new HashMap<String,String>(){{
                put("alg","HS256");
                put("typ","JWT");
            }};

    public String generateJwt(UserEntity user){

        ObjectMapper objectMapper=new ObjectMapper();
        JWTPayLoad jwt=new JWTPayLoad(user.getId(),user.getUsername(),user.getRole(),user.getIs_active(),user.getName());
        try{
            String header=objectMapper.writeValueAsString(JWT_HEADER);
            String payload=objectMapper.writeValueAsString(jwt);
            return generateJwt(header,payload);
        }catch (Exception e){

        }
        return null;

    }

    public String generateJwt(String header,String payload){
        String base64header=base64encode(header);
        String base64payload=base64encode(payload);
        String signature=generateSignature(base64header,base64payload);
        return base64header+'.'+base64payload+'.'+signature;
    }
    private String base64encode(String src) {
        return Base64.getEncoder().encodeToString(src.getBytes());
    }

    private String base64decode(String src) {
        return new String(Base64.getDecoder().decode(src));
    }

    private String generateSignature(String header, String payload) {
        String signature = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            mac.init(secretKey);
            String src = header + '.' + payload;
            signature = Base64.getEncoder().encodeToString(mac.doFinal(src.getBytes()));
        } catch (Exception e) {

        }
        return signature;
    }

    //将payload分离并且解码出来
    public JWTPayLoad verifyJwt(String jwtString){
        String[] t =jwtString.split("\\.");
        if(t.length!=3) {
            System.out.println("length!=3");
            return null;
        }
        String headerString=t[0];
        String payloadString=t[1];
        String signature=t[2];
        if(signature.equals(generateSignature(headerString,payloadString))==false){
            System.out.println("signature not equal");
            return null;
        }
        ObjectMapper objectMapper=new ObjectMapper();
        try{
            payloadString=base64decode(payloadString);
            JWTPayLoad jwtPayLoad=objectMapper.readValue(payloadString.getBytes(),JWTPayLoad.class);
            return jwtPayLoad;
        }catch (Exception e){
             System.out.println(e.getMessage());
            return null;
        }
    }
}
