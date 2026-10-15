package controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entity.Universidad;
import entity.Pais;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import model.UniversidadModel;

@WebServlet("/crudUniversidadAlias")
public class CrudUniversidadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String metodo = req.getParameter("metodo");
		switch (metodo) {
		case "listaPorNombre": 		listaPorNombre(req, res); break;
		case "registra": 			registra(req, res); break;
		case "actualiza": 			actualiza(req, res); break;
		case "eliminacionFisica":	eliminacionFisica(req, res); break;
		case "eliminacionLogica":	eliminacionLogica(req, res); break;
		default:
			res.getWriter().write("{\"mensajeSalida\":\"Método no encontrado\"}");
		}
	}
	
	public void listaPorNombre(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// 1. Recibir el parámetro del nombre
		String nombre = req.getParameter("nombre");
		
		// 2. Crear un objeto UniversidadModel
		UniversidadModel model = new UniversidadModel();
		List<Universidad> lista = model.listaPorNombreLike(nombre);
		
		// 3. Construir el JSON mediante Gson modo pretty print
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonSalida = gson.toJson(lista);
		
		System.out.println("Respuesta JSON: " + jsonSalida);
		res.getWriter().write(jsonSalida);
	}
	
	public void registra(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// 1. Recibir los datos del formulario del JSP
		String nombre = req.getParameter("nombre");
		String siglas = req.getParameter("siglas");
		String direccion = req.getParameter("direccion");
		String tipoGestion = req.getParameter("tipoGestion");
		String idPais = req.getParameter("idPais");
		
		// 2. Crear un objeto Pais y Universidad
		Pais objPais = new Pais();
		objPais.setIdPais(Integer.parseInt(idPais));
		
		Universidad objUniversidad = new Universidad();
		objUniversidad.setNombre(nombre);
		objUniversidad.setSiglas(siglas);
		objUniversidad.setDireccion(direccion);
		objUniversidad.setTipoGestion(tipoGestion);
		objUniversidad.setEstado(1); // 1 = activo, 0 = inactivo	
		objUniversidad.setPais(objPais);
		
		// 3. Crear un objeto UniversidadModel
		UniversidadModel model = new UniversidadModel();
		model.registrarUniversidad(objUniversidad);
		List<Universidad> lista = model.listaTodos();
		
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonSalida = gson.toJson(lista);
		
		System.out.println("Respuesta JSON: " + jsonSalida);
		res.getWriter().write(jsonSalida);
	}
	
	public void actualiza(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// 1. Recibir los datos del formulario del JSP
		String idUniversidad = req.getParameter("id");
		String nombre = req.getParameter("nombre");
		String siglas = req.getParameter("siglas");
		String direccion = req.getParameter("direccion");
		String tipoGestion = req.getParameter("tipoGestion");
		String estado = req.getParameter("estado");
		String idPais = req.getParameter("idPais");
		
		// 2. Crear un objeto Pais y Universidad
		Pais objPais = new Pais();
		objPais.setIdPais(Integer.parseInt(idPais));
		
		Universidad objUniversidad = new Universidad();
		objUniversidad.setIdUniversidad(Integer.parseInt(idUniversidad));
		objUniversidad.setNombre(nombre);
		objUniversidad.setSiglas(siglas);
		objUniversidad.setDireccion(direccion);
		objUniversidad.setTipoGestion(tipoGestion);
		objUniversidad.setEstado(Integer.parseInt(estado)); // 1 = activo, 0 = inactivo	
		objUniversidad.setPais(objPais);
		
		// 3. Crear un objeto UniversidadModel
		UniversidadModel model = new UniversidadModel();
		model.actualizarUniversidad(objUniversidad);
		List<Universidad> lista = model.listaTodos();
		
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonSalida = gson.toJson(lista);
		
		System.out.println("Respuesta JSON: " + jsonSalida);
		res.getWriter().write(jsonSalida);
	}
	
	public void eliminacionFisica(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String idUniversidad = req.getParameter("idUniversidad");
		UniversidadModel model = new UniversidadModel();
		model.eliminarUniversidad(Integer.parseInt(idUniversidad));
		
		List<Universidad> lista = model.listaTodos();
		
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonSalida = gson.toJson(lista);
		
		System.out.println("Respuesta JSON: " + jsonSalida);
		res.getWriter().write(jsonSalida);
	}
	
	public void eliminacionLogica(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String idUniversidad = req.getParameter("idUniversidad");
		UniversidadModel model = new UniversidadModel();
		Universidad obj = model.buscarPorId(Integer.parseInt(idUniversidad));
		if (obj != null) {
			obj.setEstado(obj.getEstado() == 1 ? 0 : 1);
			model.actualizarUniversidad(obj);
		}
		
		List<Universidad> lista = model.listaTodos();
		
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonSalida = gson.toJson(lista);
		
		System.out.println("Respuesta JSON: " + jsonSalida);
		res.getWriter().write(jsonSalida);
	}
}