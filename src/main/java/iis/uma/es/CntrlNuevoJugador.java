package iis.uma.es;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CntrlNuevoJugador implements ActionListener{
	
	ConexionConBasedeDatos conexionBD;
	InterfazNuevoJugador njPanel;
	List<Jugador> ljugadores;
	
	public CntrlNuevoJugador(ConexionConBasedeDatos connbd, InterfazNuevoJugador nj, List<Jugador> lj) {
		conexionBD = connbd;
		njPanel = nj;
		ljugadores = lj;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// El 0 como identificador es provisional hasta que se inserte en la base de datos.
		Jugador j = new Jugador(0, njPanel.getNombreTf().getText(), 
				Integer.parseInt(njPanel.getEdadTf().getText()), 
				null, 0);
		ljugadores.add(j);
		int jugadorId = conexionBD.inscribirNuevoJugador(j);
		j.setIdentificador(jugadorId);
		njPanel.getNombreTf().setText("");
		njPanel.getEdadTf().setText("");
		System.out.println("nuevo jugador añadido");
		
	}

}
