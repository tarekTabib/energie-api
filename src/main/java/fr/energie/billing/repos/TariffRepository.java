package fr.energie.billing.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.energie.billing.domain.Tariff;


public interface TariffRepository extends JpaRepository<Tariff, Integer> {
}
