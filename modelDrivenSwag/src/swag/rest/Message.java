package swag.rest;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Stateless
@Path("messages")
public class Message {
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public Message() {
    }

    /**
     * Retrieves representation of an instance of Message
     * @return an instance of String
     */
    @GET
    @Produces("application/json")
    public String getMessages() {
       return "[{\"context\":\""+context.getPath()+"\"}]";
    }

    /**
     * PUT method for updating or creating an instance of Message
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

}