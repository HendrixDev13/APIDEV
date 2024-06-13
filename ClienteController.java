package Programacion.PROGRAMACION.controller;
import java.math.BigInteger;

import javax.ws.rs.Consumes;
	import javax.ws.rs.GET;
	import javax.ws.rs.POST;
	import javax.ws.rs.Path;
	import javax.ws.rs.PathParam;
	import javax.ws.rs.Produces;
	import javax.ws.rs.core.MediaType;
	import javax.ws.rs.core.Response;
	
	import Programacion.PROGRAMACION.Model.ClienteModel;
	import Programacion.PROGRAMACION.manager.ClienteManager;
	@Path("Cliente")	
	
public class ClienteController {
		ClienteManager manager = new ClienteManager();
		@POST 
		@Path("/insCliente") 
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response insCliente(ClienteModel cliente) {
	        try {
	            return Response.ok(manager.insCliente(cliente)).build();
	        } catch (Exception e) {
	            e.printStackTrace();
	            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al insertar datos").build();
	        }
	    }
		
		@GET
		@Path("/GetCliente/{Dpi}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getCliente(@PathParam("Dpi") BigInteger Dpi) {
		    try {
		        return Response.ok(manager.GetCliente(Dpi)).build();
		    } catch (Exception e) {
		        e.printStackTrace();
		        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error --> " + e).build();
		    }
		}

		
		@GET
		@Path("/GetCliente")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getCliente() {
			try {
				return Response.ok(manager.GetCliente()).build();
			} catch(Exception e) {
				e.printStackTrace();
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error --> " + e).build();
			}
		}
	
}
