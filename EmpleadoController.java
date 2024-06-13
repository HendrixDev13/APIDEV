package Programacion.PROGRAMACION.controller;
import java.io.InputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataParam;

import Programacion.PROGRAMACION.Model.Empleado;
import Programacion.PROGRAMACION.Model.ListaEmpleado;
import Programacion.PROGRAMACION.manager.EmpleadoManager;
import Programacion.PROGRAMACION.Model.jsonResult;

@Path("Empleado")
public class EmpleadoController {
EmpleadoManager manager = new EmpleadoManager();
	
	/*Guardar un empleado nuevo*/
	
	@POST
    @Path("/insEmpleado")
    @Produces("application/json")
    public Response insEmpleado(Empleado nuevoEmpleado){
		try {
			return Response.ok(manager.insEmpleado(nuevoEmpleado)).build();
			
		} catch (Exception e) {
			e.printStackTrace(); 
			return Response.ok(new jsonResult(-1,"Error",e.getMessage())).build();
		}
    }
	//
	/*Guardar un listado de empleados */
	@POST
    @Path("/insListaEmpleado")
    @Produces("application/json")
    public Response insListaEmpleado(ListaEmpleado nuevoListaEmpleado){
		try {
			return Response.ok(manager.insListaEmpleado(nuevoListaEmpleado)).build();
			
		} catch (Exception e) {
			e.printStackTrace(); 
			return Response.ok(new jsonResult(-1,"Error",e.getMessage())).build();
		}
    }
	
	/*Consultar todos los Empleados registrados*/
	@GET
	@Path("/getEmpleadosRegistrados")
	@Produces("application/json")
	public Response getEmpleadoRegistrados() {
		try {
			return Response.ok(manager.getEmpleadosRegistrados()).build();
		}catch (Exception e) {
			e.printStackTrace(); 
			return Response.ok(new jsonResult(-1,"Error",e.getMessage())).build();
		}
		
	}
	
	/*Consultar Empleado por nickname*/
	@GET
	@Path("UserEmpleado/{p_user_empleado}/getEmpleadoUser")
	@Produces("application/json")
	public Response getEmpleadoUser(@PathParam("p_user_empleado") String userEmpleado) {
		try {
			return Response.ok(manager.getEmpleadoUser(userEmpleado)).build();
		}catch (Exception e) {
			e.printStackTrace(); 
			return Response.ok(new jsonResult(-1,"Error",e.getMessage())).build();
		}
		
	}
	
	
}
