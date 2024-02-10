package com.example.REST.filters;
import jakarta.ws.rs.container.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.util.Objects;

@Provider
@PreMatching
public class CORSFilter implements ContainerRequestFilter, ContainerResponseFilter {
    private final static String ORIGIN_HEADER = "Origin";
    private final static String OPTIONS_METHOD = "OPTIONS";

    @Override
    public void filter(
            ContainerRequestContext requestContext
    ) throws IOException {
        if (isPreflightRequest(requestContext)) {
            Response response = Response.ok().build();
            requestContext.abortWith(response);
            return;
        }
    }

    @Override
    public void filter(
            ContainerRequestContext requestContext,
            ContainerResponseContext responseContext
    ) throws IOException {
        if (!isPreflightRequest(requestContext)) {
            responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
            responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
            responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
            responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
            responseContext.getHeaders().add("Access-Control-Max-Age", "1209600");
        }
    }

    private static boolean isPreflightRequest(ContainerRequestContext request) {
        String origin = request.getHeaderString(ORIGIN_HEADER);
        String method = request.getMethod();

        return Objects.nonNull(origin)
                && Objects.nonNull(method)
                && method.equalsIgnoreCase(OPTIONS_METHOD);
    }
}
