package fr.energie.billing.rest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestError {

	private String error;
	private String message;
}
