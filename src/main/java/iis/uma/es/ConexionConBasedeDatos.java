package iis.uma.es;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public abstract class ConexionConBasedeDatos {
	protected static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	protected static String DB_URL = "jdbc:mysql://127.0.0.1";
	protected static String DB_SCHEMA = "pruebaLigadb";
	protected static String TABLE_EQUIPO = "Equipo";
	protected static String TABLE_JUGADOR = "Jugador";

	//  Database credentials
	protected static String USER = "usuarioPrueba";
	protected static String PASS = "pruebausuario";

	public ConexionConBasedeDatos() {
		final String DEFAULT_PROPERTIES_FILE = "defaultProperties";

			//https://docs.oracle.com/javase/tutorial/essential/environment/properties.html
			// create and load default properties
			Properties defaultProps = new Properties();
			InputStream in;
			try {
//				in = new FileInputStream(DEFAULT_PROPERTIES_FILE);
				in = getClass().getClassLoader().getResourceAsStream(DEFAULT_PROPERTIES_FILE);
				defaultProps.load(in);
				in.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JDBC_DRIVER = (String)defaultProps.getOrDefault("JDBC_DRIVER", null);
			DB_URL = (String)defaultProps.getOrDefault("DB_URL", null);
			DB_SCHEMA = (String)defaultProps.getOrDefault("DB_SCHEMA", null);
			TABLE_EQUIPO = (String)defaultProps.getOrDefault("TABLE_EQUIPO", null);
			TABLE_JUGADOR = (String)defaultProps.getOrDefault("TABLE_JUGADOR", null);
			USER = (String)defaultProps.getOrDefault("USER", null);
			PASS = (String)defaultProps.getOrDefault("PASS", null);
			for (Object k : defaultProps.keySet()) {
				System.out.println(k + " " + defaultProps.getProperty((String) k));
			}
			System.out.println(JDBC_DRIVER);
			System.out.println(DB_URL);
			System.out.println(DB_SCHEMA);
			System.out.println(TABLE_EQUIPO);
			System.out.println(TABLE_JUGADOR);
			System.out.println(USER);
			System.out.println(PASS);

		}

		public abstract List<Equipo> listaEquipos();
		public abstract List<Jugador> listaJugadores();
		public abstract List<Jugador> listaJugadoresDeUnEquipo(int idEq);
		public abstract int inscribirNuevoJugador(Jugador j);
		// Equipo e = null para quitarlo de un equipo
		public abstract int actualizarJugador(Jugador j);
		public abstract int borrarJugador(Jugador j);

	}
