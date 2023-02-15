package com.bettersounds.services.impl;

import com.bettersounds.config.AppConfig;
import com.bettersounds.constant.DefaultRoles;
import com.bettersounds.domain.Users;
import com.bettersounds.domain.VerificationToken;
import com.bettersounds.domain.security.Role;
import com.bettersounds.dto.JwtAuthResponse;
import com.bettersounds.dto.LoginRequest;
import com.bettersounds.dto.RefreshTokenRequest;
import com.bettersounds.dto.SignupRequest;
import com.bettersounds.dto.UserInfo;
import com.bettersounds.exception.BetterSoundsException;
import com.bettersounds.exception.UserAlreadyExistAuthenticationException;
import com.bettersounds.repository.RoleRepository;
import com.bettersounds.repository.UserRepository;
import com.bettersounds.repository.VerificationTokenRepository;
import com.bettersounds.security.JwtProvider;
import com.bettersounds.services.AuthService;
import com.bettersounds.services.RefreshTokenService;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author TEGA
 */
@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{
    
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
//    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final RefreshTokenService refreshTokenService;
    private final JwtProvider jwtProvider;
    private final AppConfig appConfig;
    
    @Override
    @Transactional
    public void registerNewUser(SignupRequest signUpRequest) throws UserAlreadyExistAuthenticationException {
              
        if(userRepository.findByEmail(signUpRequest.getEmail()).isPresent()){
            throw new UserAlreadyExistAuthenticationException("User with email " + signUpRequest.getEmail() + " already exist");
        }else{
            if(signUpRequest.getPassword().equals(signUpRequest.getMatchingPassword())){
                signUpRequest.setMatchingPassword(passwordEncoder.encode(signUpRequest.getMatchingPassword()));
                Users user = Users.builder()
                        .email(signUpRequest.getEmail())
                        .createdDate(Instant.now())
                        .name(signUpRequest.getName())
                        .password(passwordEncoder.encode(signUpRequest.getPassword()))
                        .build();
//                Users user = userMapper.mapDtoToUser(signUpRequest, roleRepository);
                userRepository.save(user);
                String token = generateVerificationToken(user);
                log.info(token);
//                mailService.sendMail(new NotificationEmail("Please Activate your Account",
//                user.getEmail(), "Thank you for signing up to BetterSounds, " +
//                "please click on the below url to activate your account : " +
//                appConfig.getUrl() + "/api/auth/accountVerification/" + token));
            }else{
                throw new BetterSoundsException("Wrong Password Match");
            }
        }
    }
    @Transactional
    private String generateVerificationToken(Users user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }
    
    @Override
    @Transactional
    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(()-> new BetterSoundsException("Invalid Token"));
        fetchUserAndEnable(verificationToken.get());
        verificationTokenRepository.delete(verificationToken.get());
    }
    
    @Transactional
    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String email = verificationToken.getUser().getEmail();
        Users user = userRepository.findByEmail(email).orElseThrow(()-> new BetterSoundsException("User not found with email - "+ email));
        user.setIsEnabled(true);
        user.setIsNonLocked(true);
        Role role = roleRepository.findByName(DefaultRoles.DEFAULT_ROLE);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        
    }
    
    @Override
    public JwtAuthResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        UserInfo user = new UserInfo(getCurrentUser().getId(), getCurrentUser().getName(),getCurrentUser().getEmail(), getCurrentUser().getImageUrl(), getCurrentUser().getRoles());
        return JwtAuthResponse.builder()
                .authenticationToken(token)
                .user(user)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .build();
    }
    
    @Override
    public JwtAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUser().getEmail());
        
        return JwtAuthResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .user(refreshTokenRequest.getUser())
                .build();
    }

    
    @Transactional(readOnly = true)
    @Override
    public Users getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        log.info(principal.getUsername());
        return userRepository.findByEmail(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
    }
    
    @Override
    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }
    
    @Override
    public Optional<Users> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    @Override
    public Boolean logout(RefreshTokenRequest refreshTokenRequest) {
        return refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
    }
  
}
