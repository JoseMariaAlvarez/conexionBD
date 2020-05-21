package iis.uma.es;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.JTabbedPane;

public class InterfazPanelContenido extends JTabbedPane {

	InterfazNuevoJugador panel1;
	InterfazBorrarJugador panel2;
	InterfazIncluirJugador panel3;

	public InterfazPanelContenido(ConexionConBasedeDatos connbd, List<Equipo> le, List<Jugador> lj) {

		panel1 = new InterfazNuevoJugador(lj);
		CntrlNuevoJugador controladorNuevoJugador = new CntrlNuevoJugador(connbd, panel1, lj);
		panel1.controlador(controladorNuevoJugador);
		addTab("Añadir nuevo jugador", null, panel1, "Añadir nuevo jugador");

		panel2 = new InterfazBorrarJugador(lj);
		CntrlBorrarJugador controladorBorrarJugador = new CntrlBorrarJugador(connbd, panel2, lj);
		panel2.controlador(controladorBorrarJugador);
		addTab("Borrar jugador", null, panel2, "Borrar jugador");
		panel2.addComponentListener ( new ComponentAdapter ()
	    {
	        public void componentShown ( ComponentEvent e ){
	            panel2.actualizarListaJugadoresSinEquipo();
	        }

	        public void componentHidden ( ComponentEvent e ) {}
	    } );

		panel3 = new InterfazIncluirJugador(le, lj);
		CntrlIncluirJugador controladorIncluirJugador = new CntrlIncluirJugador(connbd, panel3, le, lj);
		panel3.controlador(controladorIncluirJugador);
		addTab("Adscribir/Dar de baja jugadores", null, panel3, "Does nothing");
		panel3.addComponentListener ( new ComponentAdapter ()
	    {
	        public void componentShown ( ComponentEvent e ){
	            panel3.actualizarListaJugadoresSinEquipo();
	        }

	        public void componentHidden ( ComponentEvent e ) {}
	    } );

	}

	
}
