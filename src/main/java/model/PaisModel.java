package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Pais;
import util.MySqlDBConexion;

public class PaisModel {

	public List<Pais> listaPais(){
		ArrayList<Pais> lista = new ArrayList<Pais>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
            conn = MySqlDBConexion.getConexion();
            String sql = "select * from pais";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()) {
                Pais tipo = new Pais();
                tipo.setIdPais(rs.getInt("idPais"));
                tipo.setIso(rs.getString("iso"));
                tipo.setNombre(rs.getString("nombre"));
                lista.add(tipo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			try {
				if (rs != null)		rs.close();
				if (pstm != null)	pstm.close();
				if (conn != null)	conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
        }
		return lista;
	}
}
