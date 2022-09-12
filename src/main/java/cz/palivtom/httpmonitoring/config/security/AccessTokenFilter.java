package cz.palivtom.httpmonitoring.config.security;

import cz.palivtom.httpmonitoring.exception.AccessTokenAuthorizationException;
import cz.palivtom.httpmonitoring.model.User;
import cz.palivtom.httpmonitoring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * <p>Pseudo JWT access token filter.</p>
 *
 * <p><strong>Not entirely secure right now because it is working only like a session cookie.</strong></p>
 *
 * <p>Can be easily transformed to complete JWT token mechanism if any custom provider for login will be created.</p>
 */
@RequiredArgsConstructor
@Component
public class AccessTokenFilter extends OncePerRequestFilter {

    private static final String HEADER_KEY = "accessToken";
    private static final Collection<GrantedAuthority> DEFAULT_AUTHORITIES = List.of(new SimpleGrantedAuthority("ROLE_USER"));

    private final UserRepository userRepository;
    private final HandlerExceptionResolver handlerExceptionResolver;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(HEADER_KEY);
        if (token != null) {
            try {
                authenticate(token);
            } catch (AccessTokenAuthorizationException e) {
                handlerExceptionResolver.resolveException(request, response, null, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void authenticate(String token) throws AccessTokenAuthorizationException {
        User user = userRepository.findByAccessToken(token).orElseThrow(() -> new AccessTokenAuthorizationException("The '{}' is invalid.", HEADER_KEY));

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), null, DEFAULT_AUTHORITIES);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}