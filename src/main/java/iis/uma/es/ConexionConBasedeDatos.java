package iis.uma.es;

import java.util.List;

public abstract class ConexionConBasedeDatos {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://database-iis.cobadwnzalab.eu-central-1.rds.amazonaws.com";
	static final String DB_SCHEMA = "pruebaLigadb";

	//  Database credentials
	static final String USER = "usuarioPrueba";
	static final String PASS = "pruebausuario";

	public abstract List<Equipo> listaEquipos();
	public abstract List<Jugador> listaJugadores();
	public abstract List<Jugador> listaJugadoresDeUnEquipo(int idEq);
	public abstract int inscribirNuevoJugador(Jugador j);
	// Equipo e = null para quitarlo de un equipo
	public abstract void actualizarJugador(Jugador j);
	public abstract void borrarJugador(Jugador j);

}
