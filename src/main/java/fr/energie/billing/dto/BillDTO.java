package fr.energie.billing.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_EMPTY)
public class BillDTO {

	private LocalDate startConsumptionDate;
	private LocalDate endConsumptionDate;
	private Integer gazConsumed;
	private Integer electricityConsumed;
	private String totalElectricityAmount;
	private String totalGazAmount;
	private String totalAmount;
}
