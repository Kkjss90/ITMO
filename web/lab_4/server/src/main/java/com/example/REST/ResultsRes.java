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
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
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
    public Response getResults(
            @Context SecurityContext securityContext
    ) {
        UserPrincipal user = (UserPrincipal) securityContext.getUserPrincipal();
        List<Result> results = resultsBean.getResults(user);
        return Response.ok().entity(results).build();
    }

    @Secured
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkCoordinates(
            @Context SecurityContext securityContext,
            Coordinates coordinates
    ) {
        UserPrincipal user = (UserPrincipal) securityContext.getUserPrincipal();
        CheckResponse response = resultsBean.check(coordinates, user);
        return Response.ok().entity(response).build();
    }

    @Secured
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response clearResults(
            @Context SecurityContext securityContext
    ) {
        UserPrincipal user = (UserPrincipal) securityContext.getUserPrincipal();
        boolean success = resultsBean.clearResults(user);
        if (success) {
            return Response.ok().build();
        } else {
            return Response.serverError().build();
        }
    }
}
