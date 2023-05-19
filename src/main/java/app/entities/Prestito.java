package app.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
@ToString
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
}
