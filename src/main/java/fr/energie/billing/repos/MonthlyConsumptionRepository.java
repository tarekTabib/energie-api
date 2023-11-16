package fr.energie.billing.repos;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.energie.billing.domain.MonthlyConsumption;

public interface MonthlyConsumptionRepository extends JpaRepository<MonthlyConsumption, Integer> {

	List<MonthlyConsumption> findByCustomerReference(String reference);

	Optional<MonthlyConsumption> findByStartConsumptionDateAndCustomerReference(LocalDate startDate, String reference);
}
