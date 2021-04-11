package application;

import java.util.List;

import entities.AmigoInexistenteException;
import entities.AmigoNaoSorteadoException;
import entities.Mensagem;
import entities.MensagemParaTodos;
import entities.SistemaAmigo;

public class Program {
	
	public static void main(String[] args) throws Exception {
		
		  SistemaAmigo sistema = new SistemaAmigo(1000);
	      Mensagem msg = new MensagemParaTodos(null, null, false);
	      
	      sistema.cadastraAmigo("José", "Jose@gmail.com");
	      sistema.cadastraAmigo("Maria", "Maria@gmail.com");
	        try {
	        	
	            sistema.configuraAmigoSecretoDe("Jose@gmail.com", "Maria@gmail.com");
	            sistema.configuraAmigoSecretoDe("Maria@gmail.com", "Jose@gmail.com");
	            System.out.println("Configurado com sucesso.");
	        }catch(AmigoInexistenteException e) {
	            System.out.println(e.getMessage());
	        }


	        sistema.enviarMensagemParaAlguem("bom dia", "Jose@gmail.com", null, true);
	        sistema.enviarMensagemParaTodos("bom dia amigos", "Maria@gmail.com", true);

	        List<Mensagem> msgs = sistema.pesquisaMensagensAnonimas();

	        for(Mensagem msgA : msgs) {
	            System.out.println(msgA.getTextoCompletoAExibir());
	        }


	        try {
	            sistema.pesquisaAmigoSecretoDe("Jose@gmail.com");
	            System.out.println("OK!");

	        }catch(AmigoNaoSorteadoException e) {
	            System.out.println(e.getMessage());
	        }catch(AmigoInexistenteException e) {
	            System.out.println(e.getMessage());
	        }
	}
}