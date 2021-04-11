package entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SistemaAmigoTest {
	
	SistemaAmigo sistema;
	
	//ANTES DE CADA TESTE ELE VAI SEMPRE ZERAR O SISTEMA
	@BeforeEach//FACA ANTES DE TODOS OS TESTES, EU QUERO QUE INICIALIZE A CLASSE SISTEMAaMIGO
	void setUp() {
		this.sistema = new SistemaAmigo();
	}

	@Test
	void testSistemaAmigo() {
		
		assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
		assertThrows(AmigoInexistenteException.class,
				() -> sistema.pesquisarAmigo("erick@teste.com"));
	}
	
	@Test
	void testPesquisaECadastraAmigo() {
		try {
			sistema.pesquisarAmigo("erick@teste.com");
			fail("Deveria falhar pois não existe ainda");
		} catch (AmigoInexistenteException  e) {
			//OK
		}
		
		try {
			sistema.cadastraAmigo("erick", "erick@teste.com");
			Amigo a = sistema.pesquisarAmigo("erick@teste.com");
			assertEquals("erick", a.getNome());
			assertEquals("erick@teste.com", a.getEmail());
		}
		catch (AmigoJaExistenteException | AmigoInexistenteException e) {
			fail("Não deveria lançar exceção");
		}
	}
	
	
	@Test
	void testEnviarMensagemParaTodos() {
		assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
		sistema.enviarMensagemParaTodos("texto", "erick@dcx.ufpb.br", true);
		List<Mensagem> mensagensAchadas = sistema.pesquisaTodasAsMensagens();
		assertTrue(mensagensAchadas.size()==1);
		assertTrue(mensagensAchadas.get(0).getEmailRemetente().equals("erick@dcx.ufpb.com"));
		
	}
	
	@Test
	void testEnviarMensagemParaAlguem() {
		assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
		sistema.enviarMensagemParaAlguem("texto", "erick@dcx.ufpb.br", "jose@dcx.ufpb.br", true);
	}
	
	
	/**
	 * assertEquals --> Veja se esse método é igual a esse
	 * assertThrows --> Veja se esse método lança essa exceção
	 * assertTrue --> Veja se é verdade essa condiçao, se não for, faça o teste falhar
	 * fail --> falha
	 * 
	 */

}








