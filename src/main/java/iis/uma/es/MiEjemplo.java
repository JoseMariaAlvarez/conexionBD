package iis.uma.es;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MiEjemplo {
	
	private static void createAndShowGUI() {

		ConexionConBasedeDatos accesoBD;

		//		accesoBD = ConexionBaseDatosJDBC.getInstance();
		accesoBD = ConexionBaseDatosHibernate.getInstance();
		List<Equipo> listEquipos = accesoBD.listaEquipos();
		List<Jugador> listJugadores = accesoBD.listaJugadores();
		ListIterator<Jugador> it = listJugadores.listIterator();
		while(it.hasNext()) {
			if (it.next().getIdEquipo() != null) {
				it.remove();
			}
		}
		for (Jugador jgd : listJugadores) {
			System.out.print(jgd.identificador);
			System.out.print(" " + jgd.nombre);
			System.out.print(" " + jgd.edad);
			System.out.println(" " + jgd.idEquipo);
		}
		// Create and set up the window.
		JFrame frame = new JFrame("HelloWorldJBDCHibernate");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(createJTabbedPane(accesoBD, listEquipos, listJugadores));

		// Display the window.
		frame.pack();
		frame.setVisible(true);
		
	}

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	public static JTabbedPane createJTabbedPane(ConexionConBasedeDatos connbd, List<Equipo> le, List<Jugador> lj) {
		JTabbedPane tabbedPane = new JTabbedPane();

		InterfazNuevoJugador panel1 = new InterfazNuevoJugador(lj);
		CntrlNuevoJugador controladorNuevoJugador = new CntrlNuevoJugador(connbd, panel1, lj);
		panel1.controlador(controladorNuevoJugador);
		tabbedPane.addTab("Añadir nuevo jugador", null, panel1, "Añadir nuevo jugador");

		InterfazBorrarJugador panel2 = new InterfazBorrarJugador(lj);
		CntrlBorrarJugador controladorBorrarJugador = new CntrlBorrarJugador(connbd, panel2, lj);
		panel2.controlador(controladorBorrarJugador);
		tabbedPane.addTab("Borrar jugador", null, panel2, "Borrar jugador");
		panel2.addComponentListener ( new ComponentAdapter ()
	    {
	        public void componentShown ( ComponentEvent e ){
	            panel2.actualizarListaJugadoresSinEquipo();
	        }

	        public void componentHidden ( ComponentEvent e ) {}
	    } );

		InterfazIncluirJugador panel3 = new InterfazIncluirJugador(le, lj);
		CntrlIncluirJugador controladorIncluirJugador = new CntrlIncluirJugador(connbd, panel3, le, lj);
		panel3.controlador(controladorIncluirJugador);
		tabbedPane.addTab("Adscribir/Dar de baja jugadores", null, panel3, "Does nothing");
		panel3.addComponentListener ( new ComponentAdapter ()
	    {
	        public void componentShown ( ComponentEvent e ){
	            panel3.actualizarListaJugadoresSinEquipo();
	        }

	        public void componentHidden ( ComponentEvent e ) {}
	    } );

		return tabbedPane;
	}

}
