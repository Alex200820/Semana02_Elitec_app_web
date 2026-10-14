package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Universidad {

	private int idUniversidad;
	private String nombre;
	private String siglas;
	private String direccion;
	private int estado;
	private Pais pais;
}
