package iis.uma.es;

import java.util.List;
import java.util.ListIterator;

import javax.swing.JFrame;

public class MiEjemplo {
	
	private static void createAndShowGUI() {

		ConexionConBasedeDatos accesoBD;

		accesoBD = ConexionBaseDatosJDBC.getInstance();
		//		accesoBD = ConexionBaseDatosHibernate.getInstance();
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

		InterfazPanelContenido ipc = new InterfazPanelContenido(accesoBD, listEquipos, listJugadores); 
		frame.getContentPane().add(ipc);

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


}
