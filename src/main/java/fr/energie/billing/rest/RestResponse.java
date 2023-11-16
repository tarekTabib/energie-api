package fr.energie.billing.rest;

import lombok.Data;

@Data
public class RestResponse {

	private Integer statusCode;
	private Object body;

	public RestResponse(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public RestResponse(Integer statusCode, Object body) {
		this.statusCode = statusCode;
		this.body = body;
	}

}