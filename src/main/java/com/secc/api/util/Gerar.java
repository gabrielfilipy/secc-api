package com.secc.api.util;

import org.springframework.stereotype.Component;

@Component
public class Gerar {

	private Long dado;
    private Long cont = 1L;
    private String num = "";

    public void gerarSigla(Long inicio) {
        this.dado = inicio;
           
           if((this.dado >= 9) || (this.dado<100)) {
        	   Long can = cont + this.dado;
               num = "000" + can; 
           }
           if (this.dado < 9) {
        	   Long can = cont + this.dado;
               num = "0000" + can; 
           }
          
    }

    public String sigla() {
        return "SECC-" + this.num;
    }
	
}
