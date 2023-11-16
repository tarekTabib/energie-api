package fr.energie.billing.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.energie.billing.domain.Business;


public interface BusinessRepository extends JpaRepository<Business, String> {
	
	/**
	 * 
	 * @param reference
	 * @return
	 */
	Optional<Business> findByCustomerReference(String reference);
}
