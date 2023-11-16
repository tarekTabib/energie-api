package fr.energie.billing.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import fr.energie.billing.domain.Person;
import fr.energie.billing.dto.PersonDTO;

public class PersonDtoUtils {
	
	/**
	 * 
	 * @param persons
	 * @return
	 */
	public static List<PersonDTO> convertToDTOs(List<Person> persons) {
		List<PersonDTO> result = new ArrayList<>();
		for (Person person : persons) {
			result.add(convertToDTO(person));
		}
		return result;
	}
	
	/**
	 * 
	 * @param person
	 * @return
	 */
	public static PersonDTO convertToDTO(Person person) {
		PersonDTO result = new PersonDTO();
		BeanUtils.copyProperties(person, result);
		result.setReference(person.getCustomer().getReference());
		return result;
	}
}
