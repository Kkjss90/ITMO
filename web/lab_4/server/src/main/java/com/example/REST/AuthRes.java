package com.example.REST;

import com.example.models.Error;
import com.example.beans.AuthBean;
import com.example.exceptions.RefreshTokenException;
import com.example.exceptions.RegistrationException;
import com.example.exceptions.LoginException;
import com.example.models.LogReq;
import com.example.models.RefreshTokenReq;
import com.example.models.RegisterReq;
import com.example.models.Token;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.java.Log;
@Log
@Stateless
@Path("/auth")
public class AuthRes {
    @EJB
    private AuthBean authBean;
//    @OPTIONS
//    @Path("/*")
//    public Response optionsForLog() {
//        // Вернуть заголовки CORS для разрешения доступа к этому эндпоинту
//        return Response.ok()
//                .header("Access-Control-Allow-Origin", "*") // Разрешить доступ с любого источника
//                .header("Access-Control-Allow-Methods", "*") // Разрешенный метод
//                .header("Access-Control-Allow-Headers", "*") // Разрешенные заголовки
//                .build();
//    }
    @POST
    @Path("/log")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LogReq request) {
        try {
            log.info("/log");
            Token tokens = authBean.login(request);
            return Response.ok().entity(tokens).build();
        } catch (LoginException exception) {
            Error error = AuthRes.transform(exception);
            return Response.status(Response.Status.UNAUTHORIZED).entity(error).build();
        }
    }

    @POST
    @Path("/refresh")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response refreshToken(RefreshTokenReq request) {
        try {
            log.info("/refresh");
            Token tokens = authBean.refreshToken(request);
            return Response.ok().entity(tokens).build();
        } catch (RefreshTokenException exception) {
            Error error = AuthRes.transform(exception);
            return Response.status(Response.Status.UNAUTHORIZED).entity(error).build();
        }
    }

    @POST
    @Path("/reg")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registration(RegisterReq request) {
        try {
            log.info("/reg");
            authBean.registration(request);
            return Response.ok().build();
        } catch (RegistrationException exception) {
            Error error = AuthRes.transform(exception);
            return Response.status(Response.Status.UNAUTHORIZED).entity(error).build();
        }
    }

    private static Error transform(LoginException exception) {
        String code;
        switch (exception.getCode()) {
            case USER_NOT_FOUND:
                code = "LOGIN_USER_NOT_FOUND";
                break;
            case WRONG_PASSWORD:
                code = "LOGIN_WRONG_PASSWORD";
                break;
            default:
                code = "LOGIN_UNKNOWN";
                break;
        }

        return new Error(code, exception.getMessage());
    }

    private static Error transform(RegistrationException exception) {
        String code;
        switch (exception.getCode()) {
            case NOT_ENOUGH_DATA:
                code = "REGISTRATION_NOT_ENOUGH_DATA";
                break;
            case INVALID_DATA:
                code = "REGISTRATION_INVALID_DATA";
                break;
            case PASSWORDS_NOT_EQUAL:
                code = "REGISTRATION_PASSWORDS_NOT_EQUAL";
                break;
            case USER_ALREADY_EXIST:
                code = "REGISTRATION_USER_ALREADY_EXIST";
                break;
            default:
                code = "REGISTRATION_UNKNOWN";
                break;
        }
        return new Error(code, exception.getMessage());
    }

    private static Error transform(RefreshTokenException exception) {
        return new Error("REFRESH_COMMON", null);
    }
}
