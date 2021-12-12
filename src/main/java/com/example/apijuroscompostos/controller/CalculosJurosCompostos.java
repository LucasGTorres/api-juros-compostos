package com.example.apijuroscompostos.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import com.example.apijuroscompostos.model.JurosCompostosModel;

public class CalculosJurosCompostos {
		
	public Map<String, BigDecimal> calculaJurosCompostosAporteMensal(JurosCompostosModel data) {
		
		Map<String, BigDecimal> resultado = new HashMap<String, BigDecimal>();

		BigDecimal jurosTotal    = new BigDecimal("0");
		BigDecimal totalSemJuros = new BigDecimal("0");
		BigDecimal total         = new BigDecimal("0");
		
		BigDecimal totalParcial1 = new BigDecimal("0");
		BigDecimal totalParcial2 = new BigDecimal("0");

		totalParcial1 = data.getValorInicial().multiply((new BigDecimal("1").add(data.getJuros())).pow(data.getPeriodo()));
		totalParcial2 = data.getAporteMensal().multiply((new BigDecimal("1").add(data.getJuros())).pow(data.getPeriodo()).subtract(new BigDecimal("1"))).divide(data.getJuros(), RoundingMode.HALF_EVEN);
		total = totalParcial1.add(totalParcial2);
		
		totalSemJuros = data.getValorInicial().add(data.getAporteMensal().multiply(BigDecimal.valueOf(data.getPeriodo())));
		
		jurosTotal = total.subtract(totalSemJuros);
		
    	resultado.put("jurosTotal", jurosTotal.setScale(2));
    	resultado.put("montanteSemJuros", totalSemJuros.setScale(2));
    	resultado.put("montanteComJuros", total.setScale(2));
    	
		return resultado;
	}
}
