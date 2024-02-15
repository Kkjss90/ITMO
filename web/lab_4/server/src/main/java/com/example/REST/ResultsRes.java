package com.example.REST;

import com.example.REST.filters.Secured;
import com.example.REST.filters.UserPrincipal;
import com.example.beans.ResultBean;
import com.example.models.CheckResponse;
import com.example.models.Coordinates;
import com.example.models.Result;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import lombok.extern.java.Log;

import java.util.List;
import java.util.function.Supplier;

@Log
@Stateless
@Path("/results")
public class ResultsRes {
    @EJB
    private ResultBean resultsBean;

    @Secured
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResults(@Context HttpHeaders headers,
                               @Context SecurityContext securityContext
    ) {
        String authorizationHeader = headers.getHeaderString("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            String[] parts = token.split(" ");
            if (parts.length == 2) {
                Long sessionId = Long.valueOf(parts[0]);
                String username = parts[1];
                UserPrincipal user = new UserPrincipal(sessionId, username);
                List<Result> results = resultsBean.getResults(user);
                return Response.ok().entity(results).build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return null;
    }

    @Secured
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkCoordinates(
            @Context SecurityContext securityContext,
            @Context HttpHeaders headers,
            Coordinates coordinates
    ) {
        String authorizationHeader = headers.getHeaderString("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            String[] parts = token.split(" ");
            if (parts.length == 2) {
                Long sessionId = Long.valueOf(parts[0]);
                String username = parts[1];
                UserPrincipal user = new UserPrincipal(sessionId, username);
                CheckResponse response = resultsBean.check(coordinates, user);
                return Response.ok().entity(response).build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return null;
    }

    @Secured
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response clearResults(
            @Context SecurityContext securityContext,
            @Context HttpHeaders headers
    ) {
        String authorizationHeader = headers.getHeaderString("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            String[] parts = token.split(" ");
            if (parts.length == 2) {
                Long sessionId = Long.valueOf(parts[0]);
                String username = parts[1];
                UserPrincipal user = new UserPrincipal(sessionId, username);
                boolean success = resultsBean.clearResults(user);
                if (success) {
                    return Response.ok().build();
                } else {
                    return Response.serverError().build();
                }
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return null;
    }
}
