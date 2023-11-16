package fr.energie.billing.domain;

import java.text.NumberFormat;
import java.util.Locale;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Tariff {

	public static final String TARIFF_PRS = "PRS";
	public static final String TARIFF_PME = "PME";
	public static final String TARIFF_TGE = "TGE";

	private final static Locale FRANCE = new Locale("fr", "FR");
	public final static NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(FRANCE);

	@Id
	@Column(nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 45)
	private String code;

	@Column(length = 200)
	private String label;

	private Float electricityPrice;

	private Float gazPrice;

	/**
	 * 
	 * @param code
	 * @param label
	 * @param electricityPrice
	 * @param gazPrice
	 */
	public Tariff(String code, String label, Float electricityPrice, Float gazPrice) {
		super();
		this.code = code;
		this.label = label;
		this.electricityPrice = electricityPrice;
		this.gazPrice = gazPrice;
	}

}
