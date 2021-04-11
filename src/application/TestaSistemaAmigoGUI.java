package application;

import java.util.List;

import javax.swing.JOptionPane;

import entities.Amigo;
import entities.AmigoInexistenteException;
import entities.AmigoJaExistenteException;
import entities.SistemaAmigo;

public class TestaSistemaAmigoGUI {
	
	public static void main(String[] args) {
		
		int maxMensagens = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade máxima de mensagem? "));
		
		SistemaAmigo sistema = new SistemaAmigo(maxMensagens);
		
		int numAmigos = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade de participantes da brincadeira? "));
		
		for (int k = 0; k < numAmigos; k++) {
			String nomeAmigo = JOptionPane.showInputDialog("Digite o nome da pessoa: ");
			String emailAmigo = JOptionPane.showInputDialog("Digite o e-mail da pesso: ");
			
			try {
				sistema.cadastraAmigo(nomeAmigo, emailAmigo);
			} catch (AmigoJaExistenteException e) {
				JOptionPane.showMessageDialog(null, "Não foi possível cadastrar");
				e.printStackTrace();
			}
		}
		
		List<Amigo> todosOsParticipantes = sistema.pesquisaTodosOsParticipantes();
		
		for (Amigo a : todosOsParticipantes) {
			String emailSorteado = JOptionPane.showInputDialog("Quem é amigo de " + a.getEmail());
			
			try {
				sistema.configuraAmigoSecretoDe(a.getEmail(), emailSorteado);
			} catch (AmigoInexistenteException e) {
				JOptionPane.showMessageDialog(null, "Problema no sorteio");
				e.printStackTrace();
			}
		}
		
		String texto = JOptionPane.showInputDialog("Qual o texto da mensagem?");
		String emailRemetente = JOptionPane.showInputDialog("Qual o seu email cadastrado no sistema? ");
		String mensagemEhAnonima = JOptionPane.showInputDialog("A mesagem é anônima? Sim (S) ou Não (N)? ");
		boolean ehAnonima;
		
		if (mensagemEhAnonima.equalsIgnoreCase("S")) {
			ehAnonima = true;
		} else {
			ehAnonima = false;
		}
		
		sistema.enviarMensagemParaTodos(texto, emailRemetente, ehAnonima);
		
		
		
			
			
			
			
			
			
			
			
			
			
			
	}
}
