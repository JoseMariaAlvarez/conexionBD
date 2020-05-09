package iis.uma.es;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConexionBaseDatosJDBC extends ConexionConBasedeDatos {

	private Connection conn;

	private static ConexionBaseDatosJDBC instanciaInterfaz = null;

	private ConexionBaseDatosJDBC() {
		try {
			// create connection for database called DBB_SCHEMA in a server installed in
			// DB_URL, with a user USER with password PASS
			conn = DriverManager.getConnection(DB_URL + "/" + DB_SCHEMA, USER, PASS);
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static ConexionBaseDatosJDBC getInstance() {
		if (instanciaInterfaz == null) {
			instanciaInterfaz = new ConexionBaseDatosJDBC();
		}
		return instanciaInterfaz;
	}

	public List<Equipo> listaEquipos() {
		ArrayList<Equipo> lEquipos = new ArrayList<>();
		String selectQueryBody = "SELECT * FROM EQUIPO";
		Statement querySt;
		try {
			querySt = conn.createStatement();
			ResultSet rs = querySt.executeQuery(selectQueryBody);
			// position result to first
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					Equipo eq = new Equipo(id, name);
					lEquipos.add(eq);
					System.out.println(eq.identificador + " " + eq.nombre);
					List<Jugador> plantillaEquipo = listaJugadoresDeUnEquipo(eq.identificador);
					for (Jugador jgd : plantillaEquipo) {
						System.out.println(jgd.identificador + " " + jgd.nombre + " " + 
					                     jgd.edad + " " + jgd.idEquipo);
						eq.inscribirJugador(jgd);
					}

				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lEquipos;
	}

	public List<Jugador> listaJugadores() {
		ArrayList<Jugador> lEquipos = new ArrayList<>();
		String selectQueryBody = "SELECT * FROM JUGADOR";
		Statement querySt;
		try {
			querySt = conn.createStatement();
			ResultSet rs = querySt.executeQuery(selectQueryBody);
			// position result to first
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					int edad = rs.getInt(3);
					Integer idEquipo = rs.getInt(4);
					if (rs.wasNull()) {
						idEquipo = null;
					}
					lEquipos.add(new Jugador(id, name, edad, idEquipo));
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lEquipos;
	}

	public List<Jugador> listaJugadoresDeUnEquipo(int idEq) {
		ArrayList<Jugador> lEquipos = new ArrayList<>();
		String selectQueryBody = "SELECT * FROM JUGADOR WHERE idEquipo=?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(selectQueryBody);
			preparedStatement.setInt(1, idEq);
			ResultSet rs = preparedStatement.executeQuery();
			// position result to first
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					int edad = rs.getInt(3);
					int idEquipo = rs.getInt(4);
					lEquipos.add(new Jugador(id, name, edad, idEquipo));
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lEquipos;
	}

	public int inscribirNuevoJugador(Jugador j) {
		int jugadorId = 0;
		String insertBody = "INSERT INTO JUGADOR (nombre, edad, idEquipo ) VALUES (?, ?, null)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(insertBody,
					PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, j.getNombre());
			preparedStatement.setInt(2, j.getEdad());
			int res = preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			while (rs.next()) {
				jugadorId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jugadorId;

	}

	// Equipo e = null para quitarlo de un equipo
	public void actualizarJugador(Jugador j) {
		PreparedStatement preparedStatement = null;
		String updateBody = null;
		try {
			updateBody = "UPDATE JUGADOR SET idEquipo = ? WHERE (identificador = ?)";
			preparedStatement = conn.prepareStatement(updateBody);
			if (j.getIdEquipo() == null) {
				preparedStatement.setNull(1, java.sql.Types.INTEGER);
			} else {
				preparedStatement.setInt(1, j.getIdEquipo());
			}
			preparedStatement.setInt(2, j.getIdentificador());
			int res = preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

	}

	@Override
	public void borrarJugador(Jugador j) {
		int jugadorId = 0;
		String deleteBody = "DELETE FROM JUGADOR WHERE (identificador = ?)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(deleteBody);
			preparedStatement.setInt(1, j.getIdentificador());
			int res = preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
	}

}
