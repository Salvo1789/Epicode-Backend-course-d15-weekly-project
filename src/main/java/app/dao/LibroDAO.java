package app.dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import app.entities.Libro;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LibroDAO {
	private final EntityManager em;

	public LibroDAO(EntityManager em) {
		this.em = em;
	}

	public void saveLibro(Libro l) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(l);
		t.commit();
		log.info("Libro salvato!");
	}

	public Libro findById(String ISBN) {
		Libro found = em.find(Libro.class, UUID.fromString(ISBN));
		return found;
	}

	public List<Libro> findAll() {
		TypedQuery<Libro> getAllQuery = em.createQuery("SELECT l FROM Libro l", Libro.class);
		// SELECT * FROM Libro
		return getAllQuery.getResultList();
	}

}
