package br.com.undemy.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.undemy.cm.excecao.ExplosaoException;

public class Campo {

	private final int linha;
	private final int coluna;

	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;

	private List<Campo> vizinhos = new ArrayList<>();

	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	boolean adicionarVizinho(Campo vizinho) {

		boolean linhaDiferente = linha != vizinho.linha;
		boolean colunaDiferente = linha != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;

		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		int deltaGeral = deltaColuna + deltaLinha;

		if (deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;

		} else if (deltaGeral == 2 && diagonal) {

			vizinhos.add(vizinho);
			return true;

		} else {
			return false;
		}

	}

	void alternarMarcacao() {

		if (!aberto) {

			marcado = !marcado;
		}

	}

	boolean abrir() {

		if (!aberto && !marcado) {
			
			aberto = true;

			if (minado) {

				throw new ExplosaoException();
			}

			if (vizinhacaSegura()) {

				vizinhos.forEach(v -> v.abrir());

			}
			return true;
		} else {
			return false;
		}

	}

	boolean vizinhacaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}

	public boolean isMarcado() {
		return marcado;
	}

	void minar() {
		minado = true;
	}
	
	boolean isMinado() {
		return minado;
	}
	
	public boolean isAberto() {
		return aberto;

	}
	
	

	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}

	public boolean isFechado() {
		return !aberto;

	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}

	long minasNaVizinhanca() {

		return vizinhos.stream().filter(v -> v.minado).count();
	}

	void reiniciar() {

		aberto = false;
		minado = false;
		marcado = false;

	}
	
	public String toString() {
		if(marcado) {
			return "x";
		} else if (aberto && minado) {
			
			return "*";
			
		}else if (aberto && minasNaVizinhanca() > 0) {
			return Long.toString(minasNaVizinhanca());
			
		} else if(aberto) {
			return " ";
		} else {
			return "?";
		}
	}
	
}
