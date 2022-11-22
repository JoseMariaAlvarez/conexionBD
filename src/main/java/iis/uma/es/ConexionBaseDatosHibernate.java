package iis.uma.es;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ConexionBaseDatosHibernate extends ConexionConBasedeDatos {
	private static ConexionBaseDatosHibernate instanciaInterfaz = null;
	private SessionFactory sessionFactory = null;

	public ConexionBaseDatosHibernate() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			sessionFactory = new Configuration().configure().buildSessionFactory();
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

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

	@Override
	public List<Equipo> listaEquipos() {

		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		List<Equipo> result = session.createQuery("from Equipo", Equipo.class).getResultList();
		for (Equipo e : (List<Equipo>) result) {
			System.out.println("E (" + e.getNombre() + ")");
		}
		session.getTransaction().commit();
		session.close();

		return result;
	}

	@Override
	public List<Jugador> listaJugadores() {

		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		List<Jugador> result = session.createQuery("from Jugador", Jugador.class).getResultList();
		for (Jugador j : (List<Jugador>) result) {
			System.out.println("Jugador (" + j.getNombre() + ") : " + j.getEdad());
		}
		session.getTransaction().commit();
		session.close();

		return result;
	}

	@Override
	public List<Jugador> listaJugadoresDeUnEquipo(int idEq) {

		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Query<Jugador> query = session.createQuery("from Jugador where idEquipo=?1", Jugador.class);
		query.setParameter(1, idEq);
		List<Jugador> result = query.getResultList();

		for (Jugador j : (List<Jugador>) result) {
			System.out.println("Jugador (" + j.getNombre() + ") : " + j.getEdad());
		}
		session.getTransaction().commit();
		session.close();

		return result;
	}

	@Override
	public int inscribirNuevoJugador(Jugador j) {
		Session session = getSessionFactory().openSession();

		session.beginTransaction();

		session.saveOrUpdate(j);
		session.getTransaction().commit();
		session.close();
		return 0;
	}

	// Equipo e = null para quitarlo de un equipo
	public int actualizarJugador(Jugador j) {
		Session session = getSessionFactory().openSession();

		session.beginTransaction();
        session.saveOrUpdate(j);
		session.getTransaction().commit();
		session.close();
		return 0;
	}

	@Override
	public int borrarJugador(Jugador j) {
		Session session = getSessionFactory().openSession();

		session.beginTransaction();
        session.delete(j);
		session.getTransaction().commit();
		session.close();
		return 0;
	}
}
