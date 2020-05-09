package iis.uma.es;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class InterfazBorrarJugador extends JPanel {

	public static String BT_BORRAR_JUGADOR_ACTION_COMMAND = "BT_BORRAR_JUGADOR_ACTION_COMMAND";
	
	List<Jugador> jugadores;

	JLabel lbJugadoresSinEquipo;
	DefaultListModel<Jugador> lmJugadoresSinEquipo;
	JList<Jugador> lstJugadoresSinEquipo;
	JPanel pnListasJugadores;

	JButton btEliminarJugador;
	JPanel pnBotones;

	public InterfazBorrarJugador(List<Jugador> lj) {
		jugadores = lj;

		setLayout(new GridLayout(0, 1));

		// panel de listas de jugadores
		pnListasJugadores = new JPanel();
		pnListasJugadores.setLayout(new GridLayout(2, 2));
		lbJugadoresSinEquipo = new JLabel("Jugadores sin equipo");
		lmJugadoresSinEquipo = new DefaultListModel<>();
		lstJugadoresSinEquipo = new JList<Jugador>(lmJugadoresSinEquipo);
		for (Jugador j : jugadores) {
			if (j.idEquipo == null) {
				lmJugadoresSinEquipo.addElement(j);
			}
		}
		pnListasJugadores.add(lbJugadoresSinEquipo);
		pnListasJugadores.add(new JScrollPane(lstJugadoresSinEquipo));
		add(pnListasJugadores);

// panel de botones
		pnBotones = new JPanel();
		this.btEliminarJugador = new JButton("Eliminar jugador");
		pnBotones.add(btEliminarJugador);
		add(pnBotones);
	}
	
	public void controlador(CntrlBorrarJugador c) {
		btEliminarJugador.addActionListener(c);
		btEliminarJugador.setActionCommand(BT_BORRAR_JUGADOR_ACTION_COMMAND);

	}

	public JButton getBtEliminarJugador() {
		return btEliminarJugador;
	}

	public void actualizarListaJugadoresSinEquipo() {
		lmJugadoresSinEquipo.removeAllElements();
		for (Jugador j : jugadores) {
			lmJugadoresSinEquipo.addElement(j);
		}
	}
	
	public List<Jugador> getJugadoresSeleccionadosSinEquipo(){
		return lstJugadoresSinEquipo.getSelectedValuesList();
	}
	
}
