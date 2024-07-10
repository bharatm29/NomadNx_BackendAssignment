package com.bharat.backendAssignment.filters;

import com.bharat.backendAssignment.exceptions.EmailNotVerifiedException;
import com.bharat.backendAssignment.exceptions.TokenVerificationException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Optional;

/**
 * Intercepts requests to secure endpoints and verify the id token with Firebase Auth
 */
@Component
@RequiredArgsConstructor
public class FirebaseTokenAuthFilter extends OncePerRequestFilter {
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // continue the chain if it's a request to unsecure endpoint
        if (request.getRequestURI().contains("/api/v1/users/")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && !authorizationHeader.isBlank() && authorizationHeader.startsWith("Bearer ")) {
            final String jwtToken = authorizationHeader.substring(7);

            final FirebaseToken firebaseToken;
            try {
                firebaseToken = FirebaseAuth.getInstance().verifyIdToken(jwtToken);

                final var userId = Optional.ofNullable(firebaseToken.getClaims().get("user_id")).orElseThrow(IllegalStateException::new);
                final var user = FirebaseAuth.getInstance().getUser((String) userId);

                if (!user.isEmailVerified()) {
                    throw new EmailNotVerifiedException();
                }

                final var authentication = new UsernamePasswordAuthenticationToken(userId, null, null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (FirebaseAuthException e) {
                handlerExceptionResolver.resolveException(request, response, null, new TokenVerificationException());
                return;
            } catch (EmailNotVerifiedException e) {
                handlerExceptionResolver.resolveException(request, response, null, e);
                return;
            } catch (
                    Exception e) { // TODO: Remove this extra catch and configure a custom AuthenticationEntryPoint to catch all exceptions thrown in filters
                handlerExceptionResolver.resolveException(request, response, null, new TokenVerificationException());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
