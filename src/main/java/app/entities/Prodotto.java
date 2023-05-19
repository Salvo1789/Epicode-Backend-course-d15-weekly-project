package app.entities;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQuery(name = "Prodotto.getByTitolo", query = "SELECT p FROM Prodotto p WHERE p.titolo LIKE :titolo")
public abstract class Prodotto {
	@Id
	@GeneratedValue
	protected UUID ISBN;
	protected String titolo;
	protected Integer annoPubblicazione;
	protected Integer numPagine;

	@OneToMany(mappedBy = "prodottoPrestato")
	private List<Prestito> prestiti;

	public Prodotto(String nome, Integer anno, Integer pagine) {
		super();
		this.titolo = nome;
		this.annoPubblicazione = anno;
		this.numPagine = pagine;
	}

}
