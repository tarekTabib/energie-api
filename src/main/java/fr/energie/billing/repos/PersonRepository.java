package fr.energie.billing.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.energie.billing.domain.Person;


public interface PersonRepository extends JpaRepository<Person, String> {
	
	/**
	 * 
	 * @param reference
	 * @return
	 */
	Optional<Person> findByCustomerReference(String reference);
}
