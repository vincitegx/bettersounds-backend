package com.bettersounds.security;

import com.bettersounds.constant.AuthProvider;
import com.bettersounds.domain.Users;
import com.bettersounds.services.impl.AuthServiceImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author TEGA
 */
@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        
        System.out.println("Hello");
        System.out.println(authentication.getPrincipal());

//        Users user = authServiceImpl.findUserByEmail(email);
//        if(user == null){
//            authServiceImpl.registerNewUserAfterOAuthLoginSuccess(email, name, AuthProvider.GOOGLE);
//        }else{
//            authServiceImpl.updateExistingUserAfterOAuthLoginSuccess(user, name, AuthProvider.GOOGLE);
//        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
    
    
    
}
