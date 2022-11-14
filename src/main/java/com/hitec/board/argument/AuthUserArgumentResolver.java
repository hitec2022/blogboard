package com.hitec.board.argument;

import java.util.Base64;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitec.board.vo.AuthUserVo;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isRegUserAnnotation = parameter.getParameterAnnotation(AuthUser.class) != null;
        boolean isAuthUserVo = parameter.getParameterType().equals(AuthUserVo.class);
       
        return isRegUserAnnotation && isAuthUserVo;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        String xUserInfo = webRequest.getHeader("X-USERINFO");
        if(xUserInfo!=null){
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String userInfo = new String(decoder.decode(xUserInfo));
            JsonNode userInfoNode = objectMapper.readTree(userInfo);

            String name = userInfoNode.get("name").textValue();
            String preferred_username = userInfoNode.get("preferred_username").textValue();
            String username = userInfoNode.get("username").textValue();
            String email = userInfoNode.get("email").textValue();
            String id = userInfoNode.get("id").textValue();
            
            return new AuthUserVo(name, preferred_username, username, email, id);
        }
        return null;
    }
    
}
