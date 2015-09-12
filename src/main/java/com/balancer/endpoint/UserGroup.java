package com.balancer.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/group")
public class UserGroup {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayhello() {
        return "hello";
    }
}
