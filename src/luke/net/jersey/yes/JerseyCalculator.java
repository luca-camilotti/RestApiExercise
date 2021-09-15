/*
	Realizzazione di un web service RESTful con JAX-RS 
    (Jersey)
    
    Base URI:
    http://your_domain:port/display-name/url-pattern/path_from_rest_class
    Es:
    http://localhost:8080/JerseyJson/calculator/json/add?X=2&Y=4

*/
package luke.net.jersey.yes;

import javax.ws.rs.core.*;


import javax.ws.rs.*;

/*
 * Esempi di web api:
 * 
http://localhost:8080/JerseyJson/calculator/json/mul?X=6&Y=8
http://localhost:8080/JerseyJson/calculator/json/add?X=6&Y=8
	
*/



//@ApplicationPath("/")
@Path("calculator")
public class JerseyCalculator { /*extends Application {
    public JerseyCalculator() {
        super();
    } */
    
    // Metodo che restituisce il risultato in json
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.TEXT_XML)
    @Path("json/{operation}")
    public calcResult getResultJson( @PathParam(value="operation") String operation, @QueryParam("X") double x, @QueryParam("Y") double y) {
        double r;

        if ( operation.equalsIgnoreCase("add"))
            r = x + y;
        else if ( operation.equalsIgnoreCase("sub"))
            r = x - y;
        else if ( operation.equalsIgnoreCase("mul"))
            r = x * y;
        else if ( operation.equalsIgnoreCase("div")) {
            if (y != 0.0)
                r = x / y;
            else
                throw new WebApplicationException(400); // bad request
        }
        else
            throw new WebApplicationException(400); // bad request
        
        calcResult result = new calcResult();
        result.setOp(operation);
        result.setRes(r);
        result.setX(x);
        result.setY(y);
        return result;
    }
    
    
    // Metodo di esempio (restituisce un xml "fatto a mano")
    @GET
    @Produces("text/xml")
    @Path("{operation}")
    public String getResult( @PathParam(value="operation") String operation, @QueryParam("X") double x, @QueryParam("Y") double y) {
        double r;

        if ( operation.equalsIgnoreCase("add"))
            r = x + y;
        else if ( operation.equalsIgnoreCase("sub"))
            r = x - y;
        else if ( operation.equalsIgnoreCase("mul"))
            r = x * y;
        else if ( operation.equalsIgnoreCase("div")) {
            if (y != 0.0)
                r = x / y;
            else
                throw new WebApplicationException(400); // bad request
        }
        else
            throw new WebApplicationException(400); // bad request
        return ("<?xml version=\"1.0\" encoding=\"UTF-8\"?><result>" + Double.toString(r) + "</result>");
    }
}
