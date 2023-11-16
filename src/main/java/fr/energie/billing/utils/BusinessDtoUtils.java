package fr.energie.billing.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import fr.energie.billing.domain.Business;
import fr.energie.billing.domain.Tariff;
import fr.energie.billing.dto.BusinessDTO;

public class BusinessDtoUtils {

	/**
	 * 
	 * @param businesses
	 * @return
	 */
	public static List<BusinessDTO> convertToDTOs(List<Business> businesses) {
		List<BusinessDTO> result = new ArrayList<>();
		for (Business business : businesses) {
			result.add(convertToDTO(business));
		}
		return result;
	}

	/**
	 * 
	 * @param business
	 * @return
	 */
	public static BusinessDTO convertToDTO(Business business) {
		BusinessDTO result = new BusinessDTO();
		BeanUtils.copyProperties(business, result);
		result.setReference(business.getCustomer().getReference());
		result.setCapital(Tariff.CURRENCY_FORMAT.format(business.getCapital()));
		return result;
	}
}
