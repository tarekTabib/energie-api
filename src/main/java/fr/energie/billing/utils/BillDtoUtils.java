package fr.energie.billing.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import fr.energie.billing.domain.MonthlyConsumption;
import fr.energie.billing.domain.Tariff;
import fr.energie.billing.dto.BillDTO;

public class BillDtoUtils {

	/**
	 * 
	 * @param consumptions
	 * @return
	 */
	public static List<BillDTO> convertToDTOs(List<MonthlyConsumption> consumptions) {
		List<BillDTO> result = new ArrayList<>();
		for (MonthlyConsumption consumption : consumptions) {
			result.add(convertToDTO(consumption));
		}
		return result;
	}

	/**
	 * 
	 * @param consumption
	 * @return
	 */
	public static BillDTO convertToDTO(MonthlyConsumption consumption) {
		BillDTO result = new BillDTO();
		BeanUtils.copyProperties(consumption, result);
		result.setTotalElectricityAmount(Tariff.CURRENCY_FORMAT.format(consumption.getTotalElectricityAmount()));
		result.setTotalGazAmount(Tariff.CURRENCY_FORMAT.format(consumption.getTotalGazAmount()));
		result.setTotalAmount(Tariff.CURRENCY_FORMAT
				.format(consumption.getTotalElectricityAmount() + consumption.getTotalGazAmount()));
		return result;
	}
}
