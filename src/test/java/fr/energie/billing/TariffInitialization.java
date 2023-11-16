package fr.energie.billing;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import fr.energie.billing.domain.Tariff;
import fr.energie.billing.repos.TariffRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BillingApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public class TariffInitialization {

	@Autowired
	private TariffRepository tariffRepository;
	
	/**
	 * Pour les particuliers, 
	 * le prix du kWh est de 0,133 € pour l'électricité et 0,108€ pour le gaz
	 */
	@Test
	public void initializePersonalTariff() {
		Float electricityPrice = Float.valueOf(0.133f);
		Float gazPrice = Float.valueOf(0.108f);
		Tariff particularTariff = new Tariff(Tariff.TARIFF_PRS, "Tarif pour les particuliers", electricityPrice, gazPrice);
		tariffRepository.save(particularTariff);
	}
	
	/**
	 * Pour les pro, ayant un CA inférieur à 1 000 000 €, 
	 * le prix du kWh est de 0,112 € pour l'électricité et 0,117€ pour le gaz
	 */
	@Test
	public void initializePmeTariff() {
		Float electricityPrice = Float.valueOf(0.112f);
		Float gazPrice = Float.valueOf(0.117f);
		Tariff particularTariff = new Tariff(Tariff.TARIFF_PME, "Tarif pour les sociétés dont le capital <= 1 000 000 ", electricityPrice, gazPrice);
		tariffRepository.save(particularTariff);
	}
	
	/**
	 * Pour les pro, ayant un CA supérieur à 1 000 000 €, 
	 * le prix du kWh est de 0,110 € pour l'électricité et 0,123€ pour le gaz
	 */
	@Test
	public void initializeGeTariff() {
		Float electricityPrice = Float.valueOf(0.110f);
		Float gazPrice = Float.valueOf(0.123f);
		Tariff particularTariff = new Tariff(Tariff.TARIFF_TGE, "Tarif pour les sociétés dont le capital > 1 000 000 €", electricityPrice, gazPrice);
		tariffRepository.save(particularTariff);
	}
}
