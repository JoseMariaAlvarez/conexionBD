package iis.uma.es;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InterfazNuevoJugador extends JPanel {

	private static final long serialVersionUID = 1L;

	public static String BT_NEW_JUGADOR_ACCION_COMMAND = "BT_NEW_JUGADOR_ACCION_COMMAND";
	
	JLabel nombreLb;
	JTextField nombreTf;
	JLabel edadLb;
	JTextField edadTf;
	JButton newJugadorBt;
	List<Jugador> jugadores;
	
	public InterfazNuevoJugador(List<Jugador> lj) {
		jugadores = lj;
		setLayout(new GridLayout(0,1));
		nombreLb = new JLabel("Nombre");
		nombreTf = new JTextField(30);
		edadLb = new JLabel("Edad");
		edadTf = new JTextField(5);
		newJugadorBt = new JButton("Añadir Jugador");
		add(nombreLb);
		add(nombreTf);
		add(edadLb);
		add(edadTf);
		add(newJugadorBt);
	}

	public JTextField getNombreTf() {
		return nombreTf;
	}

	public JTextField getEdadTf() {
		return edadTf;
	}

	public void controlador (CntrlNuevoJugador c) {
		newJugadorBt.addActionListener(c);
		newJugadorBt.setActionCommand(BT_NEW_JUGADOR_ACCION_COMMAND);
	}
}
