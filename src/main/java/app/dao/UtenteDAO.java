package app.dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import app.entities.Utente;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UtenteDAO {
	private final EntityManager em;

	public UtenteDAO(EntityManager em) {
		this.em = em;
	}

	public void saveUtente(Utente u) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(u);
		t.commit();
		log.info("Utente salvato!");
	}

	public Utente findById(String numTessera) {
		Utente found = em.find(Utente.class, UUID.fromString(numTessera));
		return found;
	}

	public List<Utente> findAll() {
		TypedQuery<Utente> getAllQuery = em.createQuery("SELECT u FROM Utente u", Utente.class);
		// SELECT * FROM Utente
		return getAllQuery.getResultList();
	}
}
