package fr.energie.billing;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import fr.energie.billing.domain.Business;
import fr.energie.billing.domain.Person;
import fr.energie.billing.repos.BusinessRepository;
import fr.energie.billing.repos.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BillingApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public class CustomersInitialization {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private BusinessRepository businessRepository;

	@Test
	public void create10Persons() {

		for (int i = 0; i < 10; i++) {
			String civility = (i % 2 == 0) ? Person.CIVILITY_MALE : Person.CIVILITY_FEMALE;
			Person p = new Person(civility, "User" + RandomStringUtils.randomNumeric(3),
					"U" + RandomStringUtils.randomAlphanumeric(9).toUpperCase(),
					"EKL" + RandomStringUtils.randomNumeric(9));
			personRepository.save(p);
		}
	}

	@Test
	public void create10Pme() {

		for (int i = 0; i < 10; i++) {
			Integer capital = Integer.valueOf(RandomStringUtils.randomNumeric(3)) * 1000;
			Business b = new Business("Firm" + RandomStringUtils.randomAlphanumeric(6).toUpperCase(),
					RandomStringUtils.randomNumeric(14), capital, "EKL" + RandomStringUtils.randomNumeric(9));
			businessRepository.save(b);

		}
	}

	@Test
	public void create10Ge() {

		for (int i = 0; i < 10; i++) {
			Integer capital = Integer.valueOf(RandomStringUtils.randomNumeric(3)) * 1000000;
			Business b = new Business("Firm" + RandomStringUtils.randomAlphanumeric(6).toUpperCase(),
					RandomStringUtils.randomNumeric(14), capital, "EKL" + RandomStringUtils.randomNumeric(9));
			businessRepository.save(b);

		}
	}
}
