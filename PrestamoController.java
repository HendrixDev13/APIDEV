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
import Programacion.PROGRAMACION.Model.Prestamo;
import Programacion.PROGRAMACION.Model.jsonResult;
import Programacion.PROGRAMACION.manager.DevolucionMagener;
import Programacion.PROGRAMACION.manager.LibroManager;
import Programacion.PROGRAMACION.manager.PrestamoManager;
@Path("devolucion")
public class PrestamoController {

	PrestamoManager manager = new PrestamoManager();
	 @POST
	    @Path("/insPrestamo")
	    @Produces("application/json")
	    public Response insPrestamo(Prestamo prestamo){
			try {
				return Response.ok(manager.insPrestamo(prestamo)).build();
				
			} catch (Exception e) {
				e.printStackTrace(); 
				return Response.ok(new jsonResult(-1,"Error",e.getMessage())).build();
			}
	    }
		

	    
	    @GET
	    @Path("/buscardpi/{dpi}/buscarPrestamo")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response getprestamo(@PathParam("dpi") String Prestamo) {
	    	try {
	    		return Response.ok(manager.buscarPrestamo(Prestamo)).build();
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error --> " + e).build();
	    	}
	    }
   
}
