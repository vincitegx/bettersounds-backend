package com.bettersounds.dto;

import com.bettersounds.domain.Users;
import com.bettersounds.util.GeneralUtils;

/**
 *
 * @author TEGA
 */
 
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
public class LocalUser extends User implements OAuth2User, OidcUser{
    
    private final OidcIdToken idToken;
    private final OidcUserInfo userInfo;
    private Map<String, Object> attributes;
    private com.bettersounds.domain.Users user;
 
    public LocalUser(final String userID, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired,
            final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities, final com.bettersounds.domain.Users user) {
        this(userID, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities, user, null, null);
    }
 
    public LocalUser(final String userID, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired,
            final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities, final com.bettersounds.domain.Users user, OidcIdToken idToken,
            OidcUserInfo userInfo) {
        super(userID, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.user = user;
        this.idToken = idToken;
        this.userInfo = userInfo;
    }
 
    public static LocalUser create(com.bettersounds.domain.Users user, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) {
        LocalUser localUser = new LocalUser(user.getEmail(), user.getPassword(), user.getIsEnabled(), true, true, true, null,
                user, idToken, userInfo);
        localUser.setAttributes(attributes);
        return localUser;
    }
 
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
 
    @Override
    public String getName() {
        return this.user.getName();
    }
 
    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
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
