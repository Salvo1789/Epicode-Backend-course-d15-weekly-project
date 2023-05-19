package app.dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import app.entities.Rivista;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RivistaDAO {
	private final EntityManager em;

	public RivistaDAO(EntityManager em) {
		this.em = em;
	}

	public void saveRivista(Rivista r) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(r);
		t.commit();
		log.info("Libro salvato!");
	}

	public Rivista findById(String ISBN) {
		Rivista found = em.find(Rivista.class, UUID.fromString(ISBN));
		return found;
	}

	public List<Rivista> findAll() {
		TypedQuery<Rivista> getAllQuery = em.createQuery("SELECT r FROM Rivista r", Rivista.class);
		// SELECT * FROM Rivista
		return getAllQuery.getResultList();
	}
}
