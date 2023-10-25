package iis.uma.es;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JFrame;

public class MiEjemplo {
	
	private static void createAndShowGUI() {

		ConexionConBasedeDatos accesoBD;

		accesoBD = ConexionBaseDatosJDBC.getInstance();
		List<Equipo> listEquipos = accesoBD.listaEquipos();
		List<Jugador> listJugadores = accesoBD.listaJugadores();
		ListIterator<Jugador> it = listJugadores.listIterator();
		while(it.hasNext()) {
			if (it.next().getIdEquipo() != null) {
				it.remove();
			}
		}
		for (Jugador jgd : listJugadores) {
			System.out.print(jgd.getIdentificador());
			System.out.print(" " + jgd.getNombre());
			System.out.print(" " + jgd.getEdad());
			System.out.println(" " + jgd.getIdEquipo());
		}
		// Create and set up the window.
		JFrame frame = new JFrame("HelloWorldJBDCHibernate");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				shuttingDown();
			}
			
		});

		InterfazPanelContenido ipc = new InterfazPanelContenido(accesoBD, listEquipos, listJugadores); 
		frame.getContentPane().add(ipc);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
		
	}

	protected static void shuttingDown() {
		ConexionConBasedeDatos accesoBD;

		accesoBD = ConexionBaseDatosJDBC.getInstance();
		accesoBD.shutdown();
		System.exit(0);
	}

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}


}
