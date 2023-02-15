package com.bettersounds.security;

import com.bettersounds.util.GeneralUtils;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 *
 * @author TEGA
 */
@AllArgsConstructor
@Data
public class CustomOAuth2User implements OAuth2User,OidcUser{
    
    private OAuth2User oauth2User;

    private final OidcIdToken idToken;
    private final OidcUserInfo userInfo;
    private Map<String, Object> attributes;
    private com.bettersounds.domain.Users user;

    public CustomOAuth2User(final String userID, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired,
            final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities, final com.bettersounds.domain.Users user) {
        this(userID, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities, user, null, null);
    }
    
    public CustomOAuth2User(final String userID, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired,
            final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities, final com.bettersounds.domain.Users user, OidcIdToken idToken,
            OidcUserInfo userInfo) {
        super();
//        super(userID, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, null);
        this.user = user;
        this.idToken = idToken;
        this.userInfo = userInfo;
    }
    
    public static CustomOAuth2User create(com.bettersounds.domain.Users user, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) {
        CustomOAuth2User localUser = new CustomOAuth2User(user.getEmail(), user.getPassword(), user.getIsEnabled(), true, true, true, null,
                user, idToken, userInfo);
        localUser.setAttributes(attributes);
        return localUser;
    }

    CustomOAuth2User(OAuth2User oAuth2User) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oauth2User.getAuthorities();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public String getName() {
        return this.user.getName();
    }

    @Override
    public Map<String, Object> getClaims() {
        return this.attributes;
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return this.userInfo;
    }

    @Override
    public OidcIdToken getIdToken() {
        return this.idToken;
    }
    
    public com.bettersounds.domain.Users getUser() {
        return user;
    }
}
