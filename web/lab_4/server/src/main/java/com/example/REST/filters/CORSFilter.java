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

@Provider
@PreMatching
@WebFilter("/*")
public class CORSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Инициализация фильтра
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println(request.getHeaderNames());
        // Устанавливаем заголовки CORS
        response.setHeader("Access-Control-Allow-Origin", "*"); // Разрешаем доступ с любых источников
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"); // Разрешенные методы
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept"); // Разрешенные заголовки
        response.setHeader("Access-Control-Max-Age", "3600"); // Время кеширования предварительных запросов в секундах

        // Продолжаем цепочку фильтров
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        // Уничтожение фильтра
    }
//
//    private final static String ORIGIN_HEADER = "Origin";
//    private final static String OPTIONS_METHOD = "OPTIONS";
//
//    @Override
//    public void filter(
//            ContainerRequestContext requestContext
//    ) throws IOException {
//        if (isPreflightRequest(requestContext)) {
//            Response response = Response.ok().build();
//            requestContext.abortWith(response);
//            return;
//        }
//    }
//
//    @Override
//    public void filter(
//            ContainerRequestContext requestContext,
//            ContainerResponseContext responseContext
//    ) throws IOException {
//        if (!isPreflightRequest(requestContext)) {
//            responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
//            responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
//            responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
//            responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
//            responseContext.getHeaders().add("Access-Control-Max-Age", "1209600");
//        }
//    }
//
//    private static boolean isPreflightRequest(ContainerRequestContext request) {
//        String origin = request.getHeaderString(ORIGIN_HEADER);
//        String method = request.getMethod();
//
//        return Objects.nonNull(origin)
//                && Objects.nonNull(method)
//                && method.equalsIgnoreCase(OPTIONS_METHOD);
//    }
}
