package iis.uma.es;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ConexionBaseDatosHibernate extends ConexionConBasedeDatos {
	private static ConexionBaseDatosHibernate instanciaInterfaz = null;
	private EntityManager entityManager = null;

	public ConexionBaseDatosHibernate() {
		try {
			// Create the SessionFactory from persistence.xml
			EntityManagerFactory entityManagerF = Persistence.createEntityManagerFactory("iis.uma.es.equipos_jugadores");
			entityManager = entityManagerF.createEntityManager();
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

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void shutdown() {
		// Close caches and connection pools
		getEntityManager().close();
		System.out.println("Session closed.");
	}

	@Override
	public List<Equipo> listaEquipos() {

		entityManager.getTransaction().begin();
//		List<Equipo> result = session.createQuery("from Equipo", Equipo.class).getResultList();
		CriteriaBuilder crBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Equipo> crQuery = crBuilder.createQuery(Equipo.class);
		crQuery.from(Equipo.class);
		List<Equipo> result = entityManager.createQuery(crQuery).getResultList();
 		for (Equipo e : (List<Equipo>) result) {
			System.out.println("E (" + e.getNombre() + ")");
		}
		entityManager.getTransaction().commit();

		return result;
	}

	@Override
	public List<Jugador> listaJugadores() {

		entityManager.getTransaction().begin();
		List<Jugador> result = entityManager.createQuery("from Jugador", Jugador.class).getResultList();
		for (Jugador j : (List<Jugador>) result) {
			System.out.println("Jugador (" + j.getNombre() + ") : " + j.getEdad());
		}
		entityManager.getTransaction().commit();

		return result;
	}

	@Override
	public List<Jugador> listaJugadoresDeUnEquipo(int idEq) {

		entityManager.getTransaction().begin();
		TypedQuery<Jugador> query = entityManager.createQuery("from Jugador where idEquipo=?1", Jugador.class);
		query.setParameter(1, idEq);
		List<Jugador> result = query.getResultList();

		for (Jugador j : (List<Jugador>) result) {
			System.out.println("Jugador (" + j.getNombre() + ") : " + j.getEdad());
		}
		entityManager.getTransaction().commit();

		return result;
	}

	@Override
	public int inscribirNuevoJugador(Jugador j) {
		entityManager.getTransaction().begin();
		entityManager.persist(j);
		entityManager.getTransaction().commit();
		return 0;
	}

	@Override
	public int actualizarEquipo(Equipo e) {
		entityManager.getTransaction().begin();
		entityManager.merge(e);
		entityManager.getTransaction().commit();
		return 0;
	}

	@Override
	// Equipo e = null para quitarlo de un equipo
	public int actualizarJugador(Jugador j) {
		entityManager.getTransaction().begin();
		entityManager.merge(j);
		entityManager.getTransaction().commit();
		return 0;
	}

	@Override
	public int borrarJugador(Jugador j) {
		entityManager.getTransaction().begin();
		entityManager.remove(j);
		entityManager.getTransaction().commit();
		return 0;
	}
}
