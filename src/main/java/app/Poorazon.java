package app;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import app.dao.LibroDAO;
import app.dao.PrestitoDAO;
import app.dao.ProdottoDAO;
import app.dao.RivistaDAO;
import app.dao.UtenteDAO;
import app.entities.Libro;
import app.entities.Periodicita;
import app.entities.Prestito;
import app.entities.Rivista;
import app.entities.Utente;
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

		Utente user1 = new Utente("Martina", "Romano", LocalDate.of(1989, 10, 29));
		Utente user2 = new Utente("Manuele", "Forte", LocalDate.of(1992, 07, 15));
		Utente user3 = new Utente("Orlando", "Esposito", LocalDate.of(1987, 01, 07));

		ud.saveUtente(user1);
		ud.saveUtente(user2);
		ud.saveUtente(user3);

		Prestito p1 = new Prestito(user1, l2, LocalDate.of(2023, 05, 01), LocalDate.of(2023, 05, 01).plusDays(30),
				LocalDate.of(2023, 05, 19));

		Prestito p2 = new Prestito(user2, l3, LocalDate.of(2023, 04, 26), LocalDate.of(2023, 04, 26).plusDays(30),
				LocalDate.of(2023, 05, 19));

		Prestito p3 = new Prestito(user3, l1, LocalDate.of(2023, 03, 14), LocalDate.of(2023, 03, 14).plusDays(30),
				LocalDate.of(2023, 04, 30));

		Prestito p4 = new Prestito(user3, r1, LocalDate.of(2023, 05, 14), LocalDate.of(2023, 05, 14).plusDays(30),
				null);

		Prestito p5 = new Prestito(user2, r2, LocalDate.of(2023, 04, 11), LocalDate.of(2023, 04, 11).plusDays(30),
				null);

		Prestito p6 = new Prestito(user1, r3, LocalDate.of(2023, 04, 11), LocalDate.of(2023, 04, 11).plusDays(30),
				null);

		prd.savePrestito(p1);
		prd.savePrestito(p2);
		prd.savePrestito(p3);
		prd.savePrestito(p4);
		prd.savePrestito(p5);
		prd.savePrestito(p6);

		// Ricerca prodotto per ISBN

//		Libro riv = ld.findById("58b3123c-adef-4e95-8a98-500235a2691e");
//		log.info(riv.toString());

		// Ricerca per autore
		log.info("Ricerca per autore in corso: " + pd.getByAutore("Laura Sartori").toString());

		// Ricerca per titolo o parte di esso
		log.info("Ricerca per titolo in corso: " + pd.getByTitolo("Manuale di bighellonaggio").toString());

		// Ricerca per anno pubblicazione
		log.info("Ricerca per anno di pubblicazione in corso: " + pd.getByAnno(1970).toString());

		// Ricerca prestiti attivi per numero tessera
		log.info("L'utente " + user3.getNome() + " " + user3.getCognome() + " ha i seguenti prestiti in corso: "
				+ pd.getByNumeroTesseraAndPrestitoAttivo(user3.getNumTessera()).toString());

		// Ricerca prestiti scaduti di prodotti non restituiti
		log.info("Prestiti scaduti e non risoluti: " + pd.getPrestitiScadutiAndNonConsegnati().toString());

		em.close();
		emf.close();

	}

}
