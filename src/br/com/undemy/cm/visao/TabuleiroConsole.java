package br.com.undemy.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.undemy.cm.excecao.ExplosaoException;
import br.com.undemy.cm.excecao.SairException;
import br.com.undemy.cm.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);

	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;

		executarJogo();

	}

	private void executarJogo() {
		try {

			boolean continuar = true;

			while (continuar) {
				cicloDoJogo();
				System.out.println("outra partida? (S/n)");
				String resposta = entrada.nextLine();
				if ("n".equalsIgnoreCase(resposta)) {
					continuar = false;

				} else {
					tabuleiro.reiniciar();
				}
			}

		} catch (SairException e) {
			System.out.println("bay");
		} finally {
			entrada.close();
		}

	}

	private void cicloDoJogo() {
		try {

			while (!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				String digitado = capturarValorDigitado("Digite (x,y): ");

				Iterator<Integer> xy = Arrays.stream(digitado.split(",")).map(e -> Integer.parseInt(e.trim()))
						.iterator();

				digitado = capturarValorDigitado("1 - Abrir 2 - (Des)Marcar");

				if ("1".equalsIgnoreCase(digitado)) {
					tabuleiro.abir(xy.next(), xy.next());
				} else if ("2".equalsIgnoreCase(digitado)) {

					tabuleiro.alternarMarcacao(xy.next(), xy.next());

				}

			}
			System.out.println("Você Ganhou!!!");
		} catch (ExplosaoException e) {
			
			System.out.println("Você Perdeu!!");
			System.out.println(tabuleiro);
		}

	}

	private String capturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();

		if ("sair".equalsIgnoreCase(digitado)) {

			throw new SairException();
		}
		return digitado;
	}
}
