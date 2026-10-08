package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Director;
import entity.Tipo;
import util.MySqlDBConexion;

public class DirectorModel {

	public int registrarDirector(Director director) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "insert into director(nombre,email,idTipo,estado) values (?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, director.getNombre());
			pstm.setString(2, director.getEmail());
			pstm.setInt(3, director.getTipo().getIdTipo());
			pstm.setInt(4, director.getEstado());
			salida = pstm.executeUpdate();
			System.out.println("SQL ==> " + pstm);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}
	
	//listar todos los directores por Npmbre
	public List<Director> listaPorNombreLike(String nombre){
		ArrayList<Director> salida = new ArrayList<Director>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "select d.idDirector,d.nombre,d.email,t.idTipo,t.descripcion, d.estado from director d inner join tipo t on d.idTipo=t.idTipo where d.nombre like ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + nombre + "%");
			System.out.println("SQL ==> " + pstm);
			rs = pstm.executeQuery();
			Director director = null;
			Tipo tipo = null;
			while (rs.next()) {
				director = new Director();
				director.setIdDirector(rs.getInt(1));
				director.setNombre(rs.getString(2));
				director.setEmail(rs.getString(3));
				tipo = new Tipo();
				tipo.setIdTipo(rs.getInt(4));
				tipo.setDescripcion(rs.getString(5));
				director.setTipo(tipo);
				director.setEstado(rs.getInt(6));
				salida.add(director);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		 }
		return salida;
	}
	
	//Actualizar director
	public int actualizarDirector(Director director) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "update director set nombre=?, email=?, idTipo=?, estado=? where idDirector=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, director.getNombre());
			pstm.setString(2, director.getEmail());
			pstm.setInt(3, director.getTipo().getIdTipo());
			pstm.setInt(4, director.getEstado());
			pstm.setInt(5, director.getIdDirector());
			System.out.println("SQL ==> " + pstm);
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}
	
	//eliminar director fisicamente
	public int eliminarDirector(int idDirector) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "delete from director where idDirector=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idDirector);
			System.out.println("SQL ==> " + pstm);
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}
	
	//Busca POr ID
	public Director buscarPorId(int idDirector) {
		Director director = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "select d.idDirector,d.nombre,d.email,t.idTipo,t.descripcion, d.estado from director d inner join tipo t on d.idTipo=t.idTipo where d.idDirector=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idDirector);
			System.out.println("SQL ==> " + pstm);
			rs = pstm.executeQuery();
			Tipo tipo = null;
			if (rs.next()) {
				director = new Director();
				director.setIdDirector(rs.getInt(1));
				director.setNombre(rs.getString(2));
				director.setEmail(rs.getString(3));
				tipo = new Tipo();
				tipo.setIdTipo(rs.getInt(4));
				tipo.setDescripcion(rs.getString(5));
				director.setTipo(tipo);
				director.setEstado(rs.getInt(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return director;
	}
	
}