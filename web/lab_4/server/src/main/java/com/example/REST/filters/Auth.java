package com.example.REST.filters;


import com.example.util.HTTPHeaderExtractor;
import com.example.util.JWTutil;
import jakarta.ws.rs.core.MultivaluedMap;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

/**
 * Authentication filter for processing incoming requests.
 */
@Secured
@Provider
public class Auth implements ContainerRequestFilter {
    /**
     * Filters the incoming request context for authentication.
     *
     * @param requestContext The container request context.
     * @throws IOException if an I/O exception occurs.
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Optional<UserPrincipal> optionalUser = HTTPHeaderExtractor
                .extractJWT((MultivaluedMap<String, String>) requestContext.getHeaders())
                .flatMap(JWTutil::verify);

        if (!optionalUser.isPresent()) {
            Error error = new Error("UNAUTHORIZED", null);
            Response response = Response.status(Response.Status.UNAUTHORIZED).entity(error).build();
            requestContext.abortWith(response);
            return;
        }

        UserPrincipal user = optionalUser.get();

        requestContext.setSecurityContext(new SecurityContext() {
            @Override
            public Principal getUserPrincipal() { return user; }
            @Override
            public boolean isUserInRole(String role) { return true; }
            @Override
            public boolean isSecure() { return true; }
            @Override
            public String getAuthenticationScheme() { return null;}
        });
    }
}
