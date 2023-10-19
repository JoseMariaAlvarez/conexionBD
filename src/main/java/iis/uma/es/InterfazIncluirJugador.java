package iis.uma.es;


import java.awt.GridLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class InterfazIncluirJugador extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public static String CB_SELECCION_EQUIPO_ACTION_COMMAND = "CB_SELECCION_EQUIPO_ACTION_COMMAND";
	public static String BT_ADSCRIBIR_JUGADOR_ACTION_COMMAND = "BT_ADSCRIBIR_JUGADOR_ACTION_COMMAND";
	public static String BT_DARDEBAJA_JUGADOR_ACTION_COMMAND = "BT_DARDEBAJA_JUGADOR_ACTION_COMMAND";
	
	List<Equipo> equipos;
	List<Jugador> jugadores;

	JLabel lbSlcEquipo;
	JComboBox<Equipo> cbSeleccEquipo;

	JPanel pnSlcEquipo;

	JLabel lbJugadoresSinEquipo;
	DefaultListModel<Jugador> lmJugadoresSinEquipo;
	JList<Jugador> lstJugadoresSinEquipo;
	JLabel lbJugadoresEquipoSeleccionado;
	DefaultListModel<Jugador> lmJugadoresEquipoSeleccionado;
	JList<Jugador> lstJugadoresEquipoSeleccionado;
	JPanel pnListasJugadores;

	JButton btAdscribirJugador;
	JButton btDardeBajaJugador;
	JPanel pnBotones;

	public InterfazIncluirJugador(List<Equipo> le, List<Jugador> lj) {
		equipos = le;
		jugadores = lj;

		setLayout(new GridLayout(0, 1));
		// panel de seleccion de equipos
		lbSlcEquipo = new JLabel("Selecciona Equipo");
		cbSeleccEquipo = new JComboBox<Equipo>();
		for (Equipo e : equipos) {
			cbSeleccEquipo.addItem(e);
		}
		pnSlcEquipo = new JPanel();
		pnSlcEquipo.add(lbSlcEquipo);
		pnSlcEquipo.add(cbSeleccEquipo);
		add(pnSlcEquipo);

		// panel de listas de jugadores
		pnListasJugadores = new JPanel();
		pnListasJugadores.setLayout(new GridLayout(2, 2));
		lbJugadoresSinEquipo = new JLabel("Jugadores sin equipo");
		lbJugadoresEquipoSeleccionado = new JLabel("Jugadores del equipo seleccionado");
		lmJugadoresSinEquipo = new DefaultListModel<>();
		lstJugadoresSinEquipo = new JList<Jugador>(lmJugadoresSinEquipo);
		for (Jugador j : jugadores) {
			if (j.getIdEquipo() == null) {
				lmJugadoresSinEquipo.addElement(j);
			}
		}
		lmJugadoresEquipoSeleccionado = new DefaultListModel<>();
		Equipo eqSel = (Equipo) cbSeleccEquipo.getItemAt(0);
		List<Jugador> plant = eqSel.getPlantilla();
		for (Jugador j : plant) {
			lmJugadoresEquipoSeleccionado.addElement(j);
		}
		lstJugadoresEquipoSeleccionado = new JList<Jugador>(lmJugadoresEquipoSeleccionado);
		pnListasJugadores.add(lbJugadoresSinEquipo);
		pnListasJugadores.add(lbJugadoresEquipoSeleccionado);
		pnListasJugadores.add(new JScrollPane(lstJugadoresSinEquipo));
		pnListasJugadores.add(new JScrollPane(lstJugadoresEquipoSeleccionado));
		add(pnListasJugadores);

// panel de botones
		pnBotones = new JPanel();
		this.btAdscribirJugador = new JButton("Adscribir jugador");
		this.btDardeBajaJugador = new JButton("Dar de baja jugador");
		pnBotones.add(btAdscribirJugador);
		pnBotones.add(btDardeBajaJugador);
		add(pnBotones);
	}
	
	public void controlador(CntrlIncluirJugador c) {
		cbSeleccEquipo.addActionListener(c);
		cbSeleccEquipo.setActionCommand(CB_SELECCION_EQUIPO_ACTION_COMMAND);
		btAdscribirJugador.addActionListener(c);
		btAdscribirJugador.setActionCommand(BT_ADSCRIBIR_JUGADOR_ACTION_COMMAND);
		btDardeBajaJugador.addActionListener(c);
		btDardeBajaJugador.setActionCommand(BT_DARDEBAJA_JUGADOR_ACTION_COMMAND);

	}

	public JComboBox<Equipo> getCbSeleccEquipo() {
		return cbSeleccEquipo;
	}

	public JButton getBtAdscribirJugador() {
		return btAdscribirJugador;
	}

	public JButton getBtDardeBajaJugador() {
		return btDardeBajaJugador;
	}

	public void actualizarListaJugadoresEquipoSeleccionado(List<Jugador>jugadores) {
		lmJugadoresEquipoSeleccionado.removeAllElements();
		for (Jugador j : jugadores) {
			lmJugadoresEquipoSeleccionado.addElement(j);
		}
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

	public List<Jugador> getJugadoresSeleccionadosDelEquipoSeleccionado(){
		return lstJugadoresEquipoSeleccionado.getSelectedValuesList();
	}
	
}
