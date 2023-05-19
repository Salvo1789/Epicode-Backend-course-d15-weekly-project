package app.dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import app.entities.Libro;
import app.entities.Prodotto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProdottoDAO {
	private final EntityManager em;

	public ProdottoDAO(EntityManager em) {
		this.em = em;
	}

	public void saveProdotto(Prodotto a) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(a);
		t.commit();
		log.info("Prodotto salvato!");
	}

	public Prodotto findById(String ISBN) {
		Prodotto found = em.find(Prodotto.class, UUID.fromString(ISBN));
		return found;
	}

	public List<Prodotto> findAll() {
		TypedQuery<Prodotto> getAllQuery = em.createQuery("SELECT p FROM Prodotto p", Prodotto.class);
		// SELECT * FROM Prodotto
		return getAllQuery.getResultList();
	}

	public Libro getByAutore(String autore) {
		TypedQuery<Libro> q = em.createNamedQuery("Libro.getByAutore", Libro.class);
		q.setParameter("autore", autore);
		return q.getSingleResult();
	}

	public Prodotto getByTitolo(String titolo) {
		TypedQuery<Prodotto> q = em.createNamedQuery("Prodotto.getByTitolo", Prodotto.class);
		q.setParameter("titolo", titolo);
		return q.getSingleResult();

	}

	public Prodotto getByAnno(int anno) {
		TypedQuery<Prodotto> q = em.createNamedQuery("Prodotto.getByAnno", Prodotto.class);
		q.setParameter("anno", anno);
		return q.getSingleResult();
	}

}
