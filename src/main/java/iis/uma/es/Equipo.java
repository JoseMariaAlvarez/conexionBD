package iis.uma.es;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity  
@Table(name= "Equipo")
public class Equipo {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	private int identificador;
	private String nombre;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="idEquipo")  
	@OrderColumn(name="posIndex")
	private List<Jugador> plantilla;

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
