package com.example.apijuroscompostos.controller;


import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.apijuroscompostos.model.JurosCompostosModel;
import com.google.gson.Gson;

@RestController
@RequestMapping(value="/api")
public class JurosCompostos extends CalculosJurosCompostos {
	
    @PostMapping("/produto")
    public String jurosCompostosComAporteMensal(@RequestBody @Valid JurosCompostosModel data) throws Exception {
    	Gson gson = new Gson();
    	String json = gson.toJson(calculaJurosCompostosAporteMensal(data));
    	return json;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
    	Map<String, String> custonMessageError = new HashMap<>();
    	ex.getBindingResult().getAllErrors().forEach((error) -> {
    		String fieldNameString = ((FieldError) error).getField();
    		String errorMessage = error.getDefaultMessage();
    		custonMessageError.put(fieldNameString, errorMessage);
    	});
    	
    	return custonMessageError;
    }
}
