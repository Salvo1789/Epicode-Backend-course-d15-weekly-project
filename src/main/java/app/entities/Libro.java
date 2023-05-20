package app.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "Libro.getByAutore", query = "SELECT l from Libro l WHERE l.autore = :autore")
public class Libro extends Prodotto {
	private String autore;
	private String genere;

	public Libro(String nome, Integer anno, Integer pagine, String autore, String gen) {
		super(nome, anno, pagine);
		this.autore = autore;
		this.genere = gen;
	}

	@Override
	public String toString() {
		return "Libro [autore=" + autore + ", genere=" + genere + ", ISBN=" + ISBN + ", titolo=" + titolo
				+ ", annoPubblicazione=" + annoPubblicazione + ", numPagine=" + numPagine + "]";
	}

}
