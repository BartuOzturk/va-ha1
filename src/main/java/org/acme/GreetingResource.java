package org.acme;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;

@Path("/aufgaben/1")
public class GreetingResource {

    private static List<Integer> numbers = new ArrayList<>();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getNumbers() {
        return Response.ok(numbers).build();
    }
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response addNumber (Integer newNumber){
        numbers.add(newNumber);
        return Response.ok(numbers).build();
    }


    @PUT
    @Path("{numberToUpdate}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response updateNumber (
            @PathParam("numberToUpdate") Integer numberToUpdate,
            @QueryParam ("number")Integer updateNumber){
        numbers = numbers.stream().map(number -> {

            if(number.equals(numberToUpdate)){
                return updateNumber;
            }
        return number;
        }).collect(Collectors.toList());
        return Response.ok(numbers).build();
    }


    @DELETE
    @Path("{numberToDelete}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response deleteMovie(@PathParam("numberToDelete") Integer numberToDelete){
      boolean removed = numbers.remove(numberToDelete);
      return removed ? Response.noContent().build() :
              Response.status(Response.Status.BAD_REQUEST).build();

    }



}
