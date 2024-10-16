package com.example.J2EE_project.response;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ResponseBuilder {

    String DATA = "data";
    String MESSAGE = "message";
    String SIZE = "total_results";
    String PAGE = "page";
    String MAX_PAGE = "total_page";
    String STATUS = "status";
    String HTTP_STATUS = "http_status";
    String SUCCESS_MESSAGE = "success";

    static ResponseEntity<Object> buildResponse(String message, HttpStatus httpStatus){
        Map<String, Object> response = new HashMap<>();
		response.put(MESSAGE, message);
		response.put(HTTP_STATUS, httpStatus);
		return new ResponseEntity<>(response, httpStatus);
    };

    static ResponseEntity<Object> buildResponse(Page objectsPage, HttpStatus httpStatus) {
        List list = objectsPage.getContent();
        Map<String, Object> response = new HashMap<>();
		response.put(STATUS, httpStatus.value());
		response.put(HTTP_STATUS, httpStatus.toString());
        response.put(PAGE, objectsPage.getNumber() + 1);
		response.put(SIZE, objectsPage.getNumberOfElements());
		response.put(MAX_PAGE, objectsPage.getTotalPages());
		response.put(DATA, list);
		return new ResponseEntity<>(response, httpStatus);
    }

	static ResponseEntity<Object> buildResponse(List list, HttpStatus httpStatus) {
        Map<String, Object> response = new HashMap<>();
		response.put(STATUS, httpStatus.value());
		response.put(HTTP_STATUS, httpStatus.toString());
		response.put(DATA, list);
		return new ResponseEntity<>(response, httpStatus);
    }
}
