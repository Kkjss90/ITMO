package com.example.REST.filters;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.container.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.util.Objects;

/**
 * Filter for handling Cross-Origin Resource Sharing (CORS) requests.
 */
@Provider
@PreMatching
@WebFilter(urlPatterns = "*")
public class CORSFilter implements ContainerRequestFilter, ContainerResponseFilter, Filter {
    private final static String ORIGIN_HEADER = "Origin";
    private final static String OPTIONS_METHOD = "OPTIONS";

    /**
     * Filters the incoming request context for CORS preflight requests.
     *
     * @param requestContext The container request context.
     * @throws IOException if an I/O exception occurs.
     */
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

    /**
     * Filters the outgoing response context to add CORS headers.
     *
     * @param requestContext  The container request context.
     * @param responseContext The container response context.
     * @throws IOException if an I/O exception occurs.
     */
    @Override
    public void filter(
            ContainerRequestContext requestContext,
            ContainerResponseContext responseContext
    ) throws IOException {
        String origin = requestContext.getHeaderString(ORIGIN_HEADER);

        if (origin == null)
            origin = "*";

        responseContext.getHeaders().add(
                "Access-Control-Allow-Credentials",
                "true");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Private-Network",
                "true");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Methods",
                "*");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Headers",
                "*");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Origin", origin);
    }

    /**
     * Checks if the request is a CORS preflight request.
     *
     * @param request The container request context.
     * @return True if the request is a preflight request, otherwise false.
     */
    private static boolean isPreflightRequest(ContainerRequestContext request) {
        String origin = request.getHeaderString(ORIGIN_HEADER);
        String method = request.getMethod();

        return Objects.nonNull(origin)
                && Objects.nonNull(method)
                && method.equalsIgnoreCase(OPTIONS_METHOD);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println(request.getHeaderNames());
        // Устанавливаем заголовки CORS
        response.setHeader(
                "Access-Control-Allow-Credentials",
                "true");
        response.setHeader(
                "Access-Control-Allow-Private-Network",
                "true");
        response.setHeader("Access-Control-Allow-Origin", "*"); // Разрешаем доступ с любых источников
        response.setHeader("Access-Control-Allow-Methods", "*"); // Разрешенные методы
        response.setHeader("Access-Control-Allow-Headers", "*"); // Разрешенные заголовки
        response.setHeader("Access-Control-Max-Age", "3600"); // Время кеширования предварительных запросов в секундах

        // Продолжаем цепочку фильтров
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}