package fr.energie.billing;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import fr.energie.billing.domain.Business;
import fr.energie.billing.domain.Customer;
import fr.energie.billing.domain.MonthlyConsumption;
import fr.energie.billing.domain.Tariff;
import fr.energie.billing.repos.CustomerRepository;
import fr.energie.billing.repos.MonthlyConsumptionRepository;
import fr.energie.billing.repos.TariffRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BillingApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public class ConsumptionInitialization {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private MonthlyConsumptionRepository consumptionRepository;

	@Autowired
	private TariffRepository tariffRepository;

	// <PRS, <electricityPrice, gazPrice>>
	// <PME, <electricityPrice, gazPrice>>
	// <TGE, <electricityPrice, gazPrice>>
	private Map<String, Set<Float>> tariffs;

	@BeforeEach
	public void loadTariffs() {
		this.tariffs = new HashMap<>();
		List<Tariff> list = tariffRepository.findAll();
		for (Tariff tariff : list) {
			tariffs.put(tariff.getCode(), Set.of(tariff.getElectricityPrice(), tariff.getGazPrice()));
		}
	}

	@Test
	public void createConsumptions2023() {

		List<Customer> customers = customerRepository.findAll();

		for (Customer customer : customers) {
			Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);
			cal.clear();
			cal.set(Calendar.YEAR, year);

			for (int currentMonth = month; currentMonth > 0; currentMonth--) {
				// first day and last day of the month
				LocalDate firstDay = LocalDate.of(year, currentMonth, 1);
				LocalDate lastDay = firstDay.plusDays(firstDay.lengthOfMonth() - 1);

				// Init consumption
				Integer electricityConsumed = Integer.valueOf(RandomStringUtils.randomNumeric(3));
				Integer gazConsumed = Integer.valueOf(RandomStringUtils.randomNumeric(3));
				MonthlyConsumption consumed = new MonthlyConsumption(firstDay, lastDay, electricityConsumed,
						gazConsumed, customer);

				// Set prices
				Float[] prices = getTariff(customer);
				Float electricityPrice = prices[0];
				Float gazPrice = prices[1];
				consumed.setTotalElectricityAmount(electricityPrice * electricityConsumed);
				consumed.setTotalGazAmount(gazPrice * gazConsumed);

				consumptionRepository.save(consumed);

			}
		}
	}

	/**
	 * 
	 * @param customer
	 * @return
	 */
	private Float[] getTariff(Customer customer) {
		if (customer == null) {
			return null;
		}

		if (customer.getPerson() != null) {
			return tariffs.get(Tariff.TARIFF_PRS).toArray(new Float[2]);
		}

		if (customer.getBusiness() != null && (customer.getBusiness().getCapital() < Business.PME_MAX_CAPITAL)) {
			return tariffs.get(Tariff.TARIFF_PME).toArray(new Float[2]);
		}

		return tariffs.get(Tariff.TARIFF_TGE).toArray(new Float[2]);
	}

}
