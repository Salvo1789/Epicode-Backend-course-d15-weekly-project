package app.dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import app.entities.Libro;
import app.entities.Prestito;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrestitoDAO {
	private final EntityManager em;

	public PrestitoDAO(EntityManager em) {
		this.em = em;
	}

	public void savePrestito(Prestito p) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(p);
		t.commit();
		log.info("Prestito salvato!");
	}

	public Libro findById(String ISBN) {
		Libro found = em.find(Libro.class, UUID.fromString(ISBN));
		return found;
	}

	public List<Prestito> findAll() {
		TypedQuery<Prestito> getAllQuery = em.createQuery("SELECT p FROM Prestito p", Prestito.class);
		// SELECT * FROM Libro
		return getAllQuery.getResultList();
	}

}
