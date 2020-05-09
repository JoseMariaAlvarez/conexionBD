package iis.uma.es;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
	int identificador;
	String nombre;
	List<Jugador> plantilla;

	public Equipo() {
		identificador = 0;
		nombre = null;
		plantilla = new ArrayList<>();
	}

	public Equipo(int id, String n) {
		identificador = id;
		nombre = n;
		plantilla = new ArrayList<>();
	}
	
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Jugador> getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(List<Jugador> lj) {
		plantilla = lj;
	}
	
	public void inscribirJugador(Jugador j) {
		plantilla.add(j);
	}
	
	public void dardeBajaJugador(Jugador j) {
		plantilla.remove(j);
	}
	
	public String toString() {
		return nombre;
	}
	
}
