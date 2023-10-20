package iis.uma.es;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ConexionBaseDatosHibernate extends ConexionConBasedeDatos {
	private static ConexionBaseDatosHibernate instanciaInterfaz = null;
	private Session session = null;

	public ConexionBaseDatosHibernate() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			session = new Configuration().configure().buildSessionFactory().openSession();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static ConexionBaseDatosHibernate getInstance() {
		if (instanciaInterfaz == null) {
			instanciaInterfaz = new ConexionBaseDatosHibernate();
		}
		return instanciaInterfaz;
	}

	public Session getSession() {
		return session;
	}

	public void shutdown() {
		// Close caches and connection pools
		getSession().close();
		System.out.println("Session closed.");
	}

	@Override
	public List<Equipo> listaEquipos() {

		Transaction t = session.beginTransaction();
//		List<Equipo> result = session.createQuery("from Equipo", Equipo.class).getResultList();
		CriteriaBuilder crBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Equipo> crQuery = crBuilder.createQuery(Equipo.class);
		crQuery.from(Equipo.class);
		List<Equipo> result = session.createQuery(crQuery).getResultList();
 		for (Equipo e : (List<Equipo>) result) {
			System.out.println("E (" + e.getNombre() + ")");
		}
		t.commit();

		return result;
	}

	@Override
	public List<Jugador> listaJugadores() {

		Transaction t = session.beginTransaction();
		List<Jugador> result = session.createQuery("from Jugador", Jugador.class).getResultList();
		for (Jugador j : (List<Jugador>) result) {
			System.out.println("Jugador (" + j.getNombre() + ") : " + j.getEdad());
		}
		t.commit();

		return result;
	}

	@Override
	public List<Jugador> listaJugadoresDeUnEquipo(int idEq) {

		Transaction t = session.beginTransaction();
		Query<Jugador> query = session.createQuery("from Jugador where idEquipo=?1", Jugador.class);
		query.setParameter(1, idEq);
		List<Jugador> result = query.getResultList();

		for (Jugador j : (List<Jugador>) result) {
			System.out.println("Jugador (" + j.getNombre() + ") : " + j.getEdad());
		}
		t.commit();

		return result;
	}

	@Override
	public int inscribirNuevoJugador(Jugador j) {
		Transaction t = session.beginTransaction();

		session.persist(j);
		t.commit();
		return 0;
	}

	@Override
	public int actualizarEquipo(Equipo e) {
		Transaction t = session.beginTransaction();
		session.saveOrUpdate(e);
		t.commit();
		return 0;
	}

	@Override
	// Equipo e = null para quitarlo de un equipo
	public int actualizarJugador(Jugador j) {
		Transaction t = session.beginTransaction();
		session.saveOrUpdate(j);
		t.commit();
		return 0;
	}

	@Override
	public int borrarJugador(Jugador j) {
		Transaction t = session.beginTransaction();
		session.delete(j);
		t.commit();
		return 0;
	}
}
