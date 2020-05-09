package iis.uma.es;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CntrlBorrarJugador implements ActionListener {
	ConexionConBasedeDatos conexionBD;
	InterfazBorrarJugador ijPanel;
	List<Jugador> jugadoresDisponibles;

	public CntrlBorrarJugador(ConexionConBasedeDatos connbd, InterfazBorrarJugador panel2, List<Jugador> lj) {
		conexionBD = connbd;
		ijPanel = panel2;
		jugadoresDisponibles = lj;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(InterfazBorrarJugador.BT_BORRAR_JUGADOR_ACTION_COMMAND)) {
			for (Jugador j : ijPanel.getJugadoresSeleccionadosSinEquipo()) {
				jugadoresDisponibles.remove(j);
				conexionBD.borrarJugador(j);
			}
			ijPanel.actualizarListaJugadoresSinEquipo();
		}
	}

}
