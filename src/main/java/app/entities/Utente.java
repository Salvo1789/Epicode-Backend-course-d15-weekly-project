package app.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Utente {
	@Id
	@GeneratedValue
	private UUID numTessera;
	private String nome;
	private String cognome;
	private LocalDate dataNascita;

	@OneToMany(mappedBy = "utente")
	private List<Prestito> prestiti;

	public Utente(String nome, String cognome, LocalDate data) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = data;
	}
}
