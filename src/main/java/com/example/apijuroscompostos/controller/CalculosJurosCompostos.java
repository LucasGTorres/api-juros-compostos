package com.example.apijuroscompostos.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import com.example.apijuroscompostos.model.JurosCompostosModel;

public class CalculosJurosCompostos {
		
	public Map<String, BigDecimal> calculaJurosCompostosAporteMensal(JurosCompostosModel data) {
		
		Map<String, BigDecimal> resultado = new HashMap<String, BigDecimal>();

		BigDecimal totalJuros    = new BigDecimal("0");
		BigDecimal totalInvestido = new BigDecimal("0");
		BigDecimal totalFinal         = new BigDecimal("0");
		
		BigDecimal totalParcial1 = new BigDecimal("0");
		BigDecimal totalParcial2 = new BigDecimal("0");
		
		BigDecimal taxaMensal = new BigDecimal("0");
		
		
		data.setJuros(data.getJuros().divide(new BigDecimal("100")));
		taxaMensal = new BigDecimal("1").add(data.getJuros());
		taxaMensal = potencia(taxaMensal, new BigDecimal("0.0833"));		
		taxaMensal = taxaMensal.subtract(new BigDecimal("1")); 
				
		totalParcial1 = data.getValorInicial().multiply((new BigDecimal("1").add(taxaMensal)).pow(data.getPeriodo()));
		totalParcial2 = data.getAporteMensal().multiply((new BigDecimal("1").add(taxaMensal)).pow(data.getPeriodo()).subtract(new BigDecimal("1"))).divide(taxaMensal, RoundingMode.HALF_EVEN);
		totalFinal = totalParcial1.add(totalParcial2);
		
		totalInvestido = data.getValorInicial().add(data.getAporteMensal().multiply(BigDecimal.valueOf(data.getPeriodo())));
		
		totalJuros = totalFinal.subtract(totalInvestido);
		
    	resultado.put("totalJuros", totalJuros.setScale(2, RoundingMode.HALF_EVEN));
    	resultado.put("totalInvestido", totalInvestido.setScale(2, RoundingMode.HALF_EVEN));
    	resultado.put("totalFinal", totalFinal.setScale(2, RoundingMode.HALF_EVEN));
    	
		return resultado;
	}
	
	 public static BigDecimal potencia(BigDecimal base, BigDecimal exponent) {
	        BigDecimal result = BigDecimal.ZERO;
	        int signOf2 = exponent.signum();

	        double dn1 = base.doubleValue();
	        BigDecimal n2 = exponent.multiply(new BigDecimal(signOf2));
	        BigDecimal remainderOf2 = n2.remainder(BigDecimal.ONE);
	        BigDecimal n2IntPart = n2.subtract(remainderOf2);
	        BigDecimal intPow = base.pow(n2IntPart.intValueExact());
	        BigDecimal doublePow = new BigDecimal(Math.pow(dn1, remainderOf2.doubleValue()));
	        result = intPow.multiply(doublePow);

	        if (signOf2 == -1)
	            result = BigDecimal.ONE.divide(result, RoundingMode.HALF_UP);
	        return result;
	    }
}
