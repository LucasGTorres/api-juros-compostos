package com.example.apijuroscompostos.model;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class JurosCompostosModel {

	@Min(value = 0, message = "O valor inicial não pode ser negativo.")
	private BigDecimal valorInicial;

	@Min(value = 0, message = "O aporte mensal não pode ser negativo." )
	private BigDecimal aporteMensal;

	@Min(value = 0, message = "O periodo não pode ser negativo.")
	@NotNull(message = "O periodo não pode ser nulo.")
	private int periodo;

	@NotNull(message = "A taxa de juros não pode ser nula.")
	private BigDecimal juros;
	
}