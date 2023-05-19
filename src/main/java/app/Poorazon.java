package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import app.dao.LibroDAO;
import app.dao.PrestitoDAO;
import app.dao.ProdottoDAO;
import app.dao.RivistaDAO;
import app.dao.UtenteDAO;
import app.entities.Libro;
import app.entities.Periodicita;
import app.entities.Rivista;
import app.utils.JpaUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class Poorazon {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();

		ProdottoDAO pd = new ProdottoDAO(em);
		LibroDAO ld = new LibroDAO(em);
		RivistaDAO rd = new RivistaDAO(em);
		UtenteDAO ud = new UtenteDAO(em);
		PrestitoDAO prd = new PrestitoDAO(em);

		Libro l1 = new Libro("Il volo", 1992, 300, "Laura Sartori", "Romanzo");
		Libro l2 = new Libro("Amici", 2018, 215, "Mauro De Mauri", "Autobiografia");
		Libro l3 = new Libro("Il gabbiano Jonathan Livingston", 1970, 195, "Richard Bach", "Romanzo");
		Rivista r1 = new Rivista("Piscine di lusso", 2020, 18, Periodicita.MENSILE);
		Rivista r2 = new Rivista("Manuale di bighellonaggio", 2023, 30, Periodicita.MENSILE);
		Rivista r3 = new Rivista("The Games Machine", 2008, 35, Periodicita.MENSILE);

		ld.saveLibro(l1);
		ld.saveLibro(l2);
		ld.saveLibro(l3);
		rd.saveRivista(r1);
		rd.saveRivista(r2);
		rd.saveRivista(r3);

		// Ricerca prodotto per ISBN
		/*
		 * Rivista riv = rd.findById("5c941bc5-3c8d-4ff8-8d73-abee72896c27");
		 * log.info(riv.toString());
		 */

		// Ricerca per autore
		log.info("Ricerca per autore in corso: " + pd.getByAutore("Laura Sartori").toString());

		// Ricerca per titolo o parte di esso
		log.info("Ricerca per titolo in corso: " + pd.getByTitolo("Manuale di bighellonaggio").toString());

	}

}
