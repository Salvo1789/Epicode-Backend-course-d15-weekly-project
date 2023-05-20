package app.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
@NamedQuery(name = "Prestito.getByNumeroTesseraAndPrestitoAttivo", query = "SELECT p.prodottoPrestato FROM Prestito p JOIN p.utente u WHERE u.numTessera = :numTessera AND p.dataRestituzioneReale IS NULL")
@NamedQuery(name = "Prestito.getPrestitiScadutiAndNonConsegnati", query = "SELECT p.prodottoPrestato FROM Prestito p WHERE p.dataRestituzionePrevista < CURRENT_DATE and p.dataRestituzioneReale IS NULL")
public class Prestito implements Serializable {
	@Id
	@ManyToOne
	private Utente utente;
	@Id
	@ManyToOne
	private Prodotto prodottoPrestato;
	private LocalDate dataInizioPrestito;
	private LocalDate dataRestituzionePrevista;
	private LocalDate dataRestituzioneReale;

	public Prestito(Utente utente, Prodotto prodottoPrestato, LocalDate dataInizioPrestito,
			LocalDate dataRestituzionePrevista, LocalDate dataRestituzioneReale) {
		super();
		this.utente = utente;
		this.prodottoPrestato = prodottoPrestato;
		this.dataInizioPrestito = dataInizioPrestito;
		this.dataRestituzionePrevista = dataRestituzionePrevista;
		this.dataRestituzioneReale = dataRestituzioneReale;
	}

	@Override
	public String toString() {
		return "Prestito [utente=" + utente + ", prodottoPrestato=" + prodottoPrestato + ", dataInizioPrestito="
				+ dataInizioPrestito + ", dataRestituzionePrevista=" + dataRestituzionePrevista
				+ ", dataRestituzioneReale=" + dataRestituzioneReale + "]";
	}
}
