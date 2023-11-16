package fr.energie.billing.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.energie.billing.domain.Business;
import fr.energie.billing.dto.BusinessDTO;
import fr.energie.billing.repos.BusinessRepository;
import fr.energie.billing.rest.RestError;
import fr.energie.billing.rest.RestResponse;
import fr.energie.billing.utils.BusinessDtoUtils;

@RestController
@RequestMapping(path = B2BController.B2C_V1)
public class B2BController {

	protected static final String B2C_V1 = "v1/b2b";

	@Autowired
	private BusinessRepository businessRepository;

	@GetMapping("business")
	public RestResponse index(@RequestParam("reference") Optional<String> reference) {

		// find All
		if (!reference.isPresent()) {
			List<Business> result = businessRepository.findAll();
			List<BusinessDTO> persons = BusinessDtoUtils.convertToDTOs(result);
			return new RestResponse(HttpStatus.OK.value(), persons);
		}

		// find by reference
		Optional<Business> businessOptional = businessRepository.findByCustomerReference(reference.get());

		// Check user existence
		if (businessOptional.isEmpty()) {
			RestError error = new RestError();
			error.setError("Couldn’t find Personal Account.");
			error.setMessage("No Personal Account found with the reference " + reference.get() + " !");
			return new RestResponse(HttpStatus.BAD_REQUEST.value(), error);
		}

		BusinessDTO result = BusinessDtoUtils.convertToDTO(businessOptional.get());
		return new RestResponse(HttpStatus.OK.value(), result);
	}
}
