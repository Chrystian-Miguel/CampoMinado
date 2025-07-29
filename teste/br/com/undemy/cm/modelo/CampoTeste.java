package br.com.undemy.cm.modelo;

import br.com.undemy.cm.excecao.ExplosaoException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CampoTeste {
	private Campo campo;

	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);

	}

	@Test
	void testeVizinhoRealDistancia1Esquerda() {

		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);

		assertTrue(resultado);

	}

	@Test
	void testeVizinhoRealDistancia1Direita() {

		Campo vizinho = new Campo(3, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);

		assertTrue(resultado);

	}

	@Test
	void testeVizinhoRealDistancia1Encima() {

		Campo vizinho = new Campo(2, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);

		assertTrue(resultado);

	}

	@Test
	void testeVizinhoRealDistancia1Embaixo() {

		Campo vizinho = new Campo(4, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);

		assertTrue(resultado);

	}

	@Test
	void testeVizinhoRealDistancia1DiagonalSD() {

		Campo vizinho = new Campo(2, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);

		assertTrue(resultado);

	}

	@Test
	void testeVizinhoRealDistancia1DiagonalSE() {

		Campo vizinho = new Campo(2, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);

		assertTrue(resultado);

	}

	@Test
	void testeVizinhoRealDistancia1DiagonalID() {

		Campo vizinho = new Campo(4, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);

		assertTrue(resultado);

	}

	@Test
	void testeVizinhoRealDistancia1DiagonalIE() {

		Campo vizinho = new Campo(4, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);

		assertTrue(resultado);

	}

	@Test
	void testeNaoVizinho() {

		Campo vizinho = new Campo(1, 1);
		boolean resultado = campo.adicionarVizinho(vizinho);

		assertFalse(resultado);

	}

	@Test
	void testeValorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}

	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}

	@Test
	void testeAlternarMarcacaoDuasChamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}

	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}

	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}

	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});

	}

	@Test
	void testeAbririnadoNaoarcado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}

	@Test
	void testeAbririComVizinhos() {
		Campo campo11 = new Campo(1, 1);

		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinho(campo11);

		campo.adicionarVizinho(campo22);
		campo.abrir();

		assertTrue(campo22.isAberto() && campo11.isAberto());

	}
	@Test
	void testeAbririComVizinhos2() {
		Campo campo11 = new Campo(1, 1);
		Campo campo12 = new Campo(1, 2);
		campo12.minar();
		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);

		campo.adicionarVizinho(campo22);
		campo.abrir();

		assertTrue(campo22.isAberto() && campo12.isFechado());

	}
}

