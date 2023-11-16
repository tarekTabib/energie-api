package fr.energie.billing.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class MonthlyConsumption {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private LocalDate startConsumptionDate;
    
    @Column
    private LocalDate endConsumptionDate;

    @Column
    private Integer gazConsumed;
    
    @Column
    private Integer electricityConsumed;
    
    @Column
    private Float totalElectricityAmount;

    @Column
    private Float totalGazAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

	public MonthlyConsumption(LocalDate startConsumptionDate, LocalDate endConsumptionDate, Integer electricityConsumed,
			Integer gazConsumed, Customer customer) {
		super();
		this.startConsumptionDate = startConsumptionDate;
		this.endConsumptionDate = endConsumptionDate;
		this.electricityConsumed = electricityConsumed;
		this.gazConsumed = gazConsumed;
		this.customer = customer;
	}
    
    
}
