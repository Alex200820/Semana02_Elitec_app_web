package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Universidad;
import entity.Pais;
import util.MySqlDBConexion;

public class UniversidadModel {

	public int registrarUniversidad(Universidad universidad) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "insert into universidad(nombre, siglas, direccion, estado, idPais, tipoGestion) values (?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, universidad.getNombre());
			pstm.setString(2, universidad.getSiglas());
			pstm.setString(3, universidad.getDireccion());
			pstm.setInt(4, universidad.getEstado());
			pstm.setInt(5, universidad.getPais().getIdPais());
			pstm.setString(6, universidad.getTipoGestion());
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
	
	// Listar todas las universidades por Nombre (Like)
	public List<Universidad> listaPorNombreLike(String nombre){
		ArrayList<Universidad> salida = new ArrayList<Universidad>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "select u.idUniversidad, u.nombre, u.siglas, u.direccion, u.estado, u.tipoGestion, p.idPais, p.iso, p.nombre from universidad u inner join pais p on u.idPais = p.idPais where u.nombre like ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + nombre + "%");
			System.out.println("SQL ==> " + pstm);
			rs = pstm.executeQuery();
			Universidad universidad = null;
			Pais pais = null;
			while (rs.next()) {
				universidad = new Universidad();
				universidad.setIdUniversidad(rs.getInt(1));
				universidad.setNombre(rs.getString(2));
				universidad.setSiglas(rs.getString(3));
				universidad.setDireccion(rs.getString(4));
				universidad.setEstado(rs.getInt(5));
				universidad.setTipoGestion(rs.getString(6));
				
				pais = new Pais();
				pais.setIdPais(rs.getInt(7));
				pais.setIso(rs.getString(8));
				pais.setNombre(rs.getString(9));
				
				universidad.setPais(pais);
				salida.add(universidad);
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
	
	// Actualizar universidad
	public int actualizarUniversidad(Universidad universidad) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "update universidad set nombre=?, siglas=?, direccion=?, estado=?, idPais=?, tipoGestion=? where idUniversidad=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, universidad.getNombre());
			pstm.setString(2, universidad.getSiglas());
			pstm.setString(3, universidad.getDireccion());
			pstm.setInt(4, universidad.getEstado());
			pstm.setInt(5, universidad.getPais().getIdPais());
			pstm.setString(6, universidad.getTipoGestion());
			pstm.setInt(7, universidad.getIdUniversidad());
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
	
	// Eliminar universidad físicamente
	public int eliminarUniversidad(int idUniversidad) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "delete from universidad where idUniversidad=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idUniversidad);
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
	
	// Buscar por ID
	public Universidad buscarPorId(int idUniversidad) {
		Universidad universidad = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "select u.idUniversidad, u.nombre, u.siglas, u.direccion, u.estado, u.tipoGestion, p.idPais, p.iso, p.nombre from universidad u inner join pais p on u.idPais = p.idPais where u.idUniversidad=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idUniversidad);
			System.out.println("SQL ==> " + pstm);
			rs = pstm.executeQuery();
			Pais pais = null;
			if (rs.next()) {
				universidad = new Universidad();
				universidad.setIdUniversidad(rs.getInt(1));
				universidad.setNombre(rs.getString(2));
				universidad.setSiglas(rs.getString(3));
				universidad.setDireccion(rs.getString(4));
				universidad.setEstado(rs.getInt(5));
				universidad.setTipoGestion(rs.getString(6));
				
				pais = new Pais();
				pais.setIdPais(rs.getInt(7));
				pais.setIso(rs.getString(8));
				pais.setNombre(rs.getString(9));
				
				universidad.setPais(pais);
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
		return universidad;
	}
	
	public List<Universidad> listaTodos(){
		ArrayList<Universidad> salida = new ArrayList<Universidad>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "select u.idUniversidad, u.nombre, u.siglas, u.direccion, u.estado, u.tipoGestion, p.idPais, p.iso, p.nombre from universidad u inner join pais p on u.idPais = p.idPais";
			pstm = conn.prepareStatement(sql);
			System.out.println("SQL ==> " + pstm);
			rs = pstm.executeQuery();
			Universidad universidad = null;
			Pais pais = null;
			while (rs.next()) {
				universidad = new Universidad();
				universidad.setIdUniversidad(rs.getInt(1));
				universidad.setNombre(rs.getString(2));
				universidad.setSiglas(rs.getString(3));
				universidad.setDireccion(rs.getString(4));
				universidad.setEstado(rs.getInt(5));
				universidad.setTipoGestion(rs.getString(6));
				
				pais = new Pais();
				pais.setIdPais(rs.getInt(7));
				pais.setIso(rs.getString(8));
				pais.setNombre(rs.getString(9));
				
				universidad.setPais(pais);
				salida.add(universidad);
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
}