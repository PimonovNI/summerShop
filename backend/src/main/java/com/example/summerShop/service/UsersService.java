package com.example.summerShop.service;

import com.example.summerShop.dto.UserRegistrationDto;
import com.example.summerShop.dto.UserLoginDto;
import com.example.summerShop.model.User;
import com.example.summerShop.model.enums.Role;
import com.example.summerShop.model.enums.Status;
import com.example.summerShop.repository.UsersRepository;
import com.example.summerShop.security.JwtTokenProvider;
import com.example.summerShop.util.MailUtils;
import com.example.summerShop.util.exception.JwtRefreshException;
import com.example.summerShop.util.exception.NotVerifiedEmailException;
import com.example.summerShop.util.response.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class UsersService {

    @Value("${spring.site.url}")
    private String hostName;

    private final UsersRepository usersRepository;
    private final MailSenderService mailSenderService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UsersService(UsersRepository usersRepository, MailSenderService mailSenderService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.usersRepository = usersRepository;
        this.mailSenderService = mailSenderService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional
    public Map<String, Object> login(UserLoginDto dto)
            throws BadCredentialsException, UsernameNotFoundException, NotVerifiedEmailException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        User user = usersRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Користувач з таким ім'я не знайдений"));

        return createResponseForLogin(user);
    }

    @Transactional
    public void registration(UserRegistrationDto dto) {
        User user = mapFrom(dto);
        String activationCode = UUID.randomUUID().toString();
        user.setActivationCode(activationCode);

        usersRepository.save(user);

        mailSenderService.send(
                dto.getEmail(),
                MailUtils.SUBJECT_VERIFYING,
                MailUtils.createMessageForVerifying(dto.getUsername(), hostName, activationCode)
        );
    }

    @Transactional
    public boolean activation(String activationCode) {
        Optional<User> user = usersRepository.findByActivationCode(activationCode);

        if (user.isEmpty()) {
            return false;
        }

        user.get().setActivationCode(null);
        user.get().setStatus(Status.ACTIVE);
        usersRepository.save(user.get());
        return true;
    }

    @Transactional
    public Map<String, Object> refresh(String token) throws JwtRefreshException {
        Long id = jwtTokenProvider.parseRefreshToken(token);
        if (id == null || id == -1) {
            throw new JwtRefreshException("JWT токен просрочений чи невалідний");
        }

        Optional<User> userOptional = usersRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new JwtRefreshException("JWT токен невалідний");
        }

        User user = userOptional.get();
        if (!user.getRefreshToken().equals(token)) {
            throw new JwtRefreshException("JWT токен невалідний");
        }

        return createResponseForLogin(user);
    }

    private Map<String, Object> createResponseForLogin(User user) {
        String accessToken = jwtTokenProvider.createAccessToken(
                user.getId(), user.getUsername(), user.getRole(), user.getEmail());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getId());

        user.setRefreshToken(refreshToken);
        usersRepository.save(user);

        Map<String, Object> response = new HashMap<>();
        response.put("user", mapFrom(user));
        response.put("accessToken", accessToken);
        response.put("refreshToken", refreshToken);

        return response;
    }

    private User mapFrom(UserRegistrationDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .role(Role.USER)
                .status(Status.VERIFIABLE)
                .createdAt(Instant.now())
                .build();
    }

    private UserResponseDto mapFrom(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }
}
