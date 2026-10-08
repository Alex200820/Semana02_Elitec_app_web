package controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entity.Director;
import entity.Tipo;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import model.DirectorModel;

@WebServlet("/crudDirectorAlias")
public class CrudDirectorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

		@Override
		public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
			String metodo = req.getParameter("metodo");
			switch (metodo) {
			case "listaPorNombre": 		listaPorNombre(req, res);break;
			case "registra": 			registra(req, res); break;
			case "actualiza": 			actualiza(req, res);	break;
			case "eliminacionFisica":	eliminacionFisica(req, res);break;
			case "eliminacionLogica":	eliminacionLogica(req, res);break;
			default:
				res.getWriter().write("{\"mensajeSalida\":\"Método no encontrado\"}");
			}
		}
		
		public void listaPorNombre(ServletRequest req, ServletResponse res) throws ServletException, IOException {
			//1 Recibir el parametro del nombre
			String nombre = req.getParameter("nombre");
			
			//2 Crear un objeto LibroModel
			DirectorModel model = new DirectorModel();
			List<Director> lista = model.listaPorNombreLike(nombre);
			
			//3 Enviar la lista de libros al cliente en JSON
			res.setContentType("application/json");
			
			//4 Construir el JSON mmediante Gson modo pretty print
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonSalida = gson.toJson(lista);
			
			System.out.println("Respuesta JSON: " + jsonSalida);
			
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jsonSalida);
			
		}
		public void registra(ServletRequest req, ServletResponse res) throws ServletException, IOException {
			//1 Recibir los datos del formulario del JSP
			String nombre = req.getParameter("nombre");
			String email = req.getParameter("email");
			String tipo = req.getParameter("tipo");
			
			//2 Crear un objeto Director y tipo
			Tipo objTipo = new Tipo();
			objTipo.setIdTipo(Integer.parseInt(tipo));
			
			Director objDirector = new Director();
			objDirector.setNombre(nombre);
			objDirector.setEmail(email);
			objDirector.setTipo(objTipo);
			objDirector.setEstado(1); // 1 = activo, 0 = inactivo	
			
			//3 Crear un objeto DirectorModel
			DirectorModel model = new DirectorModel();
			model.registrarDirector(objDirector);
			List<Director> lista = model.listaPorNombreLike("%");
			
			res.setContentType("application/json");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonSalida = gson.toJson(lista);
			
			System.out.println("Respuesta JSON: " + jsonSalida);
			
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jsonSalida);
			
		}
		public void actualiza(ServletRequest req, ServletResponse res) throws ServletException, IOException {
			//1 Recibir los datos del formulario del JSP
			String idDirector = req.getParameter("id");
			String nombre = req.getParameter("nombre");
			String email = req.getParameter("email");
			String tipo = req.getParameter("tipo");
			String estado = req.getParameter("estado");
			
			//2 Crear un objeto Director y tipo
			Tipo objTipo = new Tipo();
			objTipo.setIdTipo(Integer.parseInt(tipo));
			
			Director objDirector = new Director();
			objDirector.setIdDirector(Integer.parseInt(idDirector));
			objDirector.setNombre(nombre);
			objDirector.setEmail(email);
			objDirector.setTipo(objTipo);
			objDirector.setEstado(Integer.parseInt(estado)); // 1 = activo, 0 = inactivo	
			
			//3 Crear un objeto DirectorModel
			DirectorModel model = new DirectorModel();
			model.actualizarDirector(objDirector);
			List<Director> lista = model.listaPorNombreLike("%");
			
			res.setContentType("application/json");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonSalida = gson.toJson(lista);
			
			System.out.println("Respuesta JSON: " + jsonSalida);
			
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jsonSalida);
			
		}
		public void eliminacionFisica(ServletRequest req, ServletResponse res) throws ServletException, IOException {
			String idDirector = req.getParameter("idDirector");
			DirectorModel model = new DirectorModel();
			model.eliminarDirector(Integer.parseInt(idDirector));
			
			List<Director> lista = model.listaPorNombreLike("%");
			
			res.setContentType("application/json");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonSalida = gson.toJson(lista);
			
			System.out.println("Respuesta JSON: " + jsonSalida);
			
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jsonSalida);
			
		}
		public void eliminacionLogica(ServletRequest req, ServletResponse res) throws ServletException, IOException {
			String idDirector = req.getParameter("idDirector");
			DirectorModel model = new DirectorModel();
			Director obj  = model.buscarPorId(Integer.parseInt(idDirector));
			obj.setEstado(obj.getEstado() == 1 ? 0 : 1);
			model.actualizarDirector(obj);
			
			List<Director> lista = model.listaPorNombreLike("%");
			
			res.setContentType("application/json");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonSalida = gson.toJson(lista);
			
			System.out.println("Respuesta JSON: " + jsonSalida);
			
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jsonSalida);
			
		}
	
}