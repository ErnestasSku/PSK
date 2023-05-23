package rest;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/workers")
public class WorkerController {


    @Path("/")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAll() {
        return Response.ok("Sample").build();
    }

}
