package Programacion.PROGRAMACION.controller;
import javax.ws.rs.Consumes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Programacion.PROGRAMACION.Model.Devoluciones;
import Programacion.PROGRAMACION.Model.Libro;
import Programacion.PROGRAMACION.Model.jsonResult;
import Programacion.PROGRAMACION.manager.DevolucionMagener;
import Programacion.PROGRAMACION.manager.LibroManager;
@Path("devolucion")
public class DevolucionController {

	DevolucionMagener manager = new DevolucionMagener();
	 @POST
	    @Path("/insDevoluciones")
	    @Produces("application/json")
	    public Response insDevoluciones(Devoluciones devoluciones){
			try {
				return Response.ok(manager.insDevoluciones(devoluciones)).build();
				
			} catch (Exception e) {
				e.printStackTrace(); 
				return Response.ok(new jsonResult(-1,"Error",e.getMessage())).build();
			}
	    }
		

	    
	    @GET
	    @Path("/buscarDpi/{dpi}/buscarPorDpi")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response getlibro(@PathParam("dpi") String Devoluciones) {
	    	try {
	    		return Response.ok(manager.buscarPorDpi(Devoluciones)).build();
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error --> " + e).build();
	    	}
	    }

	
}
