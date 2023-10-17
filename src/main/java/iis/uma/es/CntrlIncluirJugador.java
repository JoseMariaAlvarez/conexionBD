package iis.uma.es;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CntrlIncluirJugador implements ActionListener {
	ConexionConBasedeDatos conexionBD;
	InterfazIncluirJugador ijPanel;
	List<Equipo> equipos;
	List<Jugador> jugadoresDisponibles;

	public CntrlIncluirJugador(	ConexionConBasedeDatos connbd, InterfazIncluirJugador ijp, List<Equipo> le, List<Jugador> lj) {
		conexionBD = connbd;
		ijPanel = ijp;
		equipos = le;
		jugadoresDisponibles = lj;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Equipo equipoSeleccionado = (Equipo) (ijPanel.getCbSeleccEquipo().getSelectedItem());
		if (e.getActionCommand().equals(InterfazIncluirJugador.CB_SELECCION_EQUIPO_ACTION_COMMAND)) {
			System.out.println(equipoSeleccionado.toString());
			ijPanel.actualizarListaJugadoresEquipoSeleccionado(equipoSeleccionado.getPlantilla());
		} else if (e.getActionCommand().equals(InterfazIncluirJugador.BT_ADSCRIBIR_JUGADOR_ACTION_COMMAND)) {
			for (Jugador j : ijPanel.getJugadoresSeleccionadosSinEquipo()) {
				j.setIdEquipo(equipoSeleccionado.getIdentificador());
				jugadoresDisponibles.remove(j);
				equipoSeleccionado.inscribirJugador(j);
				conexionBD.actualizarJugador(j);
			}
			ijPanel.actualizarListaJugadoresEquipoSeleccionado(equipoSeleccionado.getPlantilla());
			ijPanel.actualizarListaJugadoresSinEquipo();
		} else if (e.getActionCommand().equals(InterfazIncluirJugador.BT_DARDEBAJA_JUGADOR_ACTION_COMMAND)) {
			for (Jugador j : ijPanel.getJugadoresSeleccionadosDelEquipoSeleccionado()) {
				j.setIdEquipo(null);
				equipoSeleccionado.dardeBajaJugador(j);
				jugadoresDisponibles.add(j);
				conexionBD.actualizarJugador(j);
			}
			ijPanel.actualizarListaJugadoresEquipoSeleccionado(equipoSeleccionado.getPlantilla());
			ijPanel.actualizarListaJugadoresSinEquipo();
		}
	}

}
