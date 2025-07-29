package br.com.undemy.cm;

import br.com.undemy.cm.modelo.Tabuleiro;
import br.com.undemy.cm.visao.TabuleiroConsole;

public class Aplicacao {
	
	public static void main(String[] args) {
		
		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
	
		new TabuleiroConsole(tabuleiro);
		
	}

}
