package com.client;

import javax.ws.rs.*;

@Consumes({"text/plain", "application/json"})
@Produces({"text/plain", "application/json"})
public interface HelloProxyController {
    @GET
    @Path("/hello/{message}")
    String hello(@PathParam("message") String message);
}
