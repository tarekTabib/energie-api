package fr.energie.billing.controller;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.energie.billing.domain.MonthlyConsumption;
import fr.energie.billing.dto.BillDTO;
import fr.energie.billing.repos.MonthlyConsumptionRepository;
import fr.energie.billing.rest.RestError;
import fr.energie.billing.rest.RestResponse;
import fr.energie.billing.utils.BillDtoUtils;

@RestController
@RequestMapping(path = BillingController.BILLING)
public class BillingController {

	protected static final String BILLING = "v1/billing";

	@Autowired
	private MonthlyConsumptionRepository consumptionRepository;

	@GetMapping("customer/{reference}")
	public RestResponse getBillsOfCustomer(@PathVariable String reference, @RequestParam("year") Optional<Integer> year,
			@RequestParam("month") Optional<Integer> month) {

		// find All
		if (!year.isPresent() && !month.isPresent()) {
			List<MonthlyConsumption> consumptions = consumptionRepository.findByCustomerReference(reference);
			List<BillDTO> bills = BillDtoUtils.convertToDTOs(consumptions);
			return new RestResponse(HttpStatus.OK.value(), bills);
		}

		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Calendar.YEAR, year.get());
		LocalDate firstDay = LocalDate.of(year.get(), month.get(), 1);

		Optional<MonthlyConsumption> consumption = consumptionRepository
				.findByStartConsumptionDateAndCustomerReference(firstDay, reference);

		// Check bill existence
		if (consumption.isEmpty()) {
			RestError error = new RestError();
			error.setError("Couldn’t find Bill.");
			error.setMessage("No Bill found for the period " + month + "/" + year + "for the customer with reference "
					+ reference);
			return new RestResponse(HttpStatus.BAD_REQUEST.value(), error);
		}

		BillDTO result = BillDtoUtils.convertToDTO(consumption.get());
		return new RestResponse(HttpStatus.OK.value(), result);
	}

}
