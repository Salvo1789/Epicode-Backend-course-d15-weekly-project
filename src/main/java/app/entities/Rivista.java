package app.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Rivista extends Prodotto {
	@Enumerated(EnumType.STRING)
	private Periodicita periodicita;

	public Rivista(String nome, Integer anno, Integer pagine, Periodicita periodo) {
		super(nome, anno, pagine);
		this.setPeriodicita(periodo);
	}

}
