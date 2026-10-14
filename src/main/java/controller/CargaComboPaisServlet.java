package controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entity.Pais;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PaisModel;

@WebServlet("/cargaComboPaisAlias")
public class CargaComboPaisServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1 Traer los tipos de la base de datos y enviarlos al cliente en JSON
		PaisModel model = new PaisModel();
		List<Pais> lista = model.listaPais();
		
		//2 Construir el JSON mmediante Gson modo pretty print
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonSalida = gson.toJson(lista);
		System.out.println("Respuesta JSON: " + jsonSalida);
		
		//3 Enviar la respuesta al cliente
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(jsonSalida);
		
	}
	
}
