package entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SistemaAmigoMapTest {
	
	SistemaAmigoMap sistema;
	
	private static final String EMAIL_AMIGO = "erick@gmail.com";

	@BeforeEach
	void setUp() throws Exception {
		this.sistema = new SistemaAmigoMap();
		
	}

	@Test
	void testSistemaAmigoMapInt() {
		assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
		assertThrows(AmigoInexistenteException.class, 
				() -> sistema.pesquisaAmigo("erick@test.com"));
	}

	@Test
	void testPesquisaAmigo() {
		try {
			assertEquals(EMAIL_AMIGO, sistema.pesquisaAmigo("erick@test.com"));
		} catch (AmigoInexistenteException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testCadastraAmigo() {
		try {
			sistema.cadastraAmigo("Mariano", "mario@gmail.com");
			assertEquals("Mariano", sistema.pesquisaAmigo("mario@gmail.com"));
		} catch (AmigoJaExistenteException | AmigoInexistenteException e) {
			fail("Deveria falhar pois não ainda");
		}
	}

	@Test
	void testEnviarMensagemParaTodos() {
		assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
		sistema.enviarMensagemParaTodos("texto", "erick@dcx.com", true);
		List<Mensagem> mensagensAchadas = sistema.pesquisaTodasAsMensagens();
		assertTrue(mensagensAchadas.size() == 1);
		assertTrue(mensagensAchadas.get(0).getEmailRemetente().equals("erick@dcx.com"));
	}

	@Test
	void testEnviarMensagemParaAlguem() {
		assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
		sistema.enviarMensagemParaAlguem("texto", "erick@dcx.com", "jose@dcx.COM", true);
		List<Mensagem> mensagensAchadas = sistema.pesquisaTodasAsMensagens();
		assertEquals(1, mensagensAchadas.size());
		assertTrue(mensagensAchadas.get(0) instanceof MensagemParaAlguem);
		assertTrue(mensagensAchadas.get(0).getTexto().equals("texto"));
		
	}

	@Test
	void testPesquisaTodasAsMensagens() {
		assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
		sistema.enviarMensagemParaAlguem("texto 01", "erick@dcx.com", "jose@dcx.com", false);
		assertTrue(sistema.pesquisaTodasAsMensagens().size() == 1);
		sistema.enviarMensagemParaAlguem("texto 02", "erick@dcx.com", "jose@dcx.com", true);
		assertTrue(sistema.pesquisaTodasAsMensagens().size() == 2);
		
	}

	@Test
	void testPesquisaMensagensAnonimas() {
		assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
		sistema.enviarMensagemParaAlguem("texto 01", "erick@dcx.com", "jose@dcx.com", false);
		assertTrue(sistema.pesquisaMensagensAnonimas().isEmpty());
		sistema.enviarMensagemParaAlguem("Texto 02", "erick@dcx.com", "jose@dcx.com", true);
		assertTrue(sistema.pesquisaTodasAsMensagens().size()==1);
		
	}

	@Test
	void testPesquisaAmigoSeEConfiguraAmigoSecretoDe() {
		assertThrows(AmigoInexistenteException.class,
				() -> sistema.pesquisaAmigoSecretoDe("erick@dcx.com"));	
		
		try {
			sistema.cadastraAmigo("Erick", "erick@dcx.com");
			sistema.cadastraAmigo("Antonio", "antonio@dcx.com");
			sistema.configuraAmigoSecretoDe("erick@dcx.com", "antonio@dcx.com");
			sistema.configuraAmigoSecretoDe("antonio@dcx.com", "erick@dcx.com");
			assertEquals("antonio@dcx.com", sistema.pesquisaAmigoSecretoDe("antonio@dcx.com"));
			assertEquals("erick@dcx.com", sistema.pesquisaAmigoSecretoDe("erick@dcx.com"));
		} catch (AmigoJaExistenteException | AmigoInexistenteException | AmigoNaoSorteadoException e)  {
			fail("não deveria lançar exceção");
		}
		
		}

}