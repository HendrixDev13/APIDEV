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
import Programacion.PROGRAMACION.Model.Libro;
import Programacion.PROGRAMACION.Model.ListaLibro;
import Programacion.PROGRAMACION.manager.LibroManager;
import Programacion.PROGRAMACION.Model.jsonResult;

@Path("libro")
public class LibroController {

	LibroManager manager = new LibroManager();

	
	/*Guardar un libro nuevo*/
	@POST
    @Path("/InsertarLibro")
    @Produces("application/json")
    public Response insLibro(Libro nuevoLibro){
		try {
			return Response.ok(manager.insLibro(nuevoLibro)).build();
			
		} catch (Exception e) {
			e.printStackTrace(); 
			return Response.ok(new jsonResult(-1,"Error",e.getMessage())).build();
		}
    }
	
	/*Guardar un libro nuevo*/
	@POST
    @Path("/insListaLibro")
    @Produces("application/json")
    public Response insListaLibro(ListaLibro nuevoListaLibro){
		try {
			return Response.ok(manager.insListaLibro(nuevoListaLibro)).build();
			
		} catch (Exception e) {
			e.printStackTrace(); 
			return Response.ok(new jsonResult(-1,"Error",e.getMessage())).build();
		}
    }
	
	/*Consultar todos los libros registrados*/
	@GET
	@Path("/getLibrosRegistrados")
	@Produces("application/json")
	public Response getLibrosRegistrados() {
		try {
			return Response.ok(manager.getLibrosRegistrados()).build();
		}catch (Exception e) {
			e.printStackTrace(); 
			return Response.ok(new jsonResult(-1,"Error",e.getMessage())).build();
		}
		
	}
	//Buscar libro por nombre
	/*Consultar libro por nombre*/
	@GET
	@Path("nombrelibro/{p_nombre_libro}/getLibroNombre")
	@Produces("application/json")
	public Response getLibroNombre(@PathParam("p_nombre_libro") String nombre_libro) {
		try {
			return Response.ok(manager.getLibroNombre(nombre_libro)).build();
		}catch (Exception e) {
			e.printStackTrace(); 
			return Response.ok(new jsonResult(-1,"Error",e.getMessage())).build();
		}
		
	}
	
	/*Consultar libro por autor*/
	@GET
	@Path("autorlibro/{p_nombre_autor}/getLibroAutor")
	@Produces("application/json")
	public Response getLibroAutor(@PathParam("p_nombre_autor") String nombre_autor) {
		
		try {
			return Response.ok(manager.getLibroAutor(nombre_autor)).build();
		}catch (Exception e) {
			e.printStackTrace(); 
			return Response.ok(new jsonResult(-1,"Error",e.getMessage())).build();
		}
		
	}
	 @POST
	    @Path("/guardaArchivo")
	    @Consumes(MediaType.MULTIPART_FORM_DATA)
	    public Response guardaArch(@FormDataParam("pdfFile") InputStream archivoPDF) {
	     
	        try {
	        	return Response.ok(manager.guardaArchivo(archivoPDF)).build();
	           
	        } catch (Exception e) {
	            e.printStackTrace();
	            return Response.ok(new jsonResult(-1, "Error", e.getMessage())).build();
	        }
	 }
	    
	
	 @POST
	    @Path("/guardar")
	    @Consumes(MediaType.MULTIPART_FORM_DATA)
	    public Response guardar(@FormDataParam("pdfFile") InputStream archivoPDF, @FormDataParam("Nombre") String Nombre)
	                            {
	     
	        try {
	        	return Response.ok(manager.Guardar(archivoPDF,Nombre)).build();
	           
	        } catch (Exception e) {
	            e.printStackTrace();
	            return Response.ok(new jsonResult(-1, "Error", e.getMessage())).build();
	        }
	 }
	
	
	@GET
	@Path("/GetRutasarchivo")
	@Produces("application/json")
	public Response getrutasdearchivos() {
		try {
			return Response.ok(manager.getRutadearchivos()).build();
		}catch (Exception e) {
			e.printStackTrace(); 
			return Response.ok(new jsonResult(-1,"Error",e.getMessage())).build();
		}
		
	}
	
	// CONSULTAR METODOS POR CATEGORIAS CUALES TENGO 
	@GET
	@Path("/MisCategorias")
	@Produces("application/json")
	public Response miscategorias() {
		try {
			return Response.ok(manager.getcategorias()).build();
		}catch (Exception e) {
			e.printStackTrace(); 
			return Response.ok(new jsonResult(-1,"Error",e.getMessage())).build();
		}
		
	}
	
	
	// CONSULTA UNA EN ESPESIFICO
	@GET
	@Path("Busca/{Categoria}/getBuscaCategoria")
	@Produces("application/json")
	public Response buscacategorias(@PathParam("Categoria") String Categorias) {
		try {
			return Response.ok(manager.buscaCategoria(Categorias)).build();
		}catch (Exception e) {
			e.printStackTrace(); 
			return Response.ok(new jsonResult(-1,"Error",e.getMessage())).build();
		}
		
	}
	
	//Buscar por id de el libro 
	@GET
	@Path("Busca/{Codigo}/getBuscarCodigo")
	@Produces("application/json")
	public Response buscarcodigo(@PathParam("codigo") String codigo) {
		try {
			return Response.ok(manager.buscarporcodigo(codigo)).build();
		}catch (Exception e) {
			e.printStackTrace(); 
			return Response.ok(new jsonResult(-1,"Error",e.getMessage())).build();
		}
	}
}
	
