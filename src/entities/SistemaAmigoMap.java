package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaAmigoMap {
	
	private Map<String, Amigo> amigo;
	private List<Mensagem> mensagem;
	
	private int maxMensagens;
	
	public static final int DEFAULT_MAX_MEANSAGENS = 1000;
	
	public SistemaAmigoMap(int maxMensagens) {
		this.amigo = new HashMap<>();
		this.mensagem = new ArrayList<>();
	}
	
	public SistemaAmigoMap() {
		this(DEFAULT_MAX_MEANSAGENS);
	}
	
	public void cadastraAmigo(String nomeAmigo, String emailAmigo) throws AmigoJaExistenteException {
		Amigo amigo = new Amigo(nomeAmigo, emailAmigo);
		
		if (this.amigo.containsKey(emailAmigo)) {
			throw new AmigoJaExistenteException("Amigo já cadastrado no sistema");
		}
		this.amigo.put(emailAmigo, amigo);
	}
	
	public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexistenteException {
		Amigo amigoAchado = this.amigo.get(emailAmigo);
		
		if (amigoAchado == null) {
			throw new AmigoInexistenteException("Amigo não encontrado no sistema");
			
		}
		
		return amigoAchado;
		
	}
	
	public void enviarMensagemParaTodos(String nome, String email, boolean ehAnonima) {
		this.mensagem.add(new MensagemParaTodos(nome, email, ehAnonima));
	}
	
	public void enviarMensagemParaAlguem(String nome, String emailRemetente, String emailDestinatario, boolean ehAnonima) {
		this.mensagem.add(new MensagemParaAlguem(nome, emailRemetente, emailDestinatario, ehAnonima));
	}
	
	public List<Mensagem> pesquisaTodasAsMensagens() {
		return this.mensagem;
	}
	
	public List<Mensagem> pesquisaMensagensAnonimas() {
		List<Mensagem> mensagemAnonima = new ArrayList();
		
		for (Mensagem msg : this.mensagem) {
			if (msg.ehAnonima()) {
				mensagemAnonima.add(msg);
			}
		}
		return mensagemAnonima;
	}
	
	public String pesquisaAmigoSecretoDe(String emailDaPessoa)
			throws AmigoInexistenteException, AmigoNaoSorteadoException {
		
		Amigo amigoSecreto = this.amigo.get(emailDaPessoa);
		
		if (amigoSecreto.getEmailAmigoSorteado() == null) {
			throw new AmigoNaoSorteadoException("Não existe nenhum amigo sorteado.");
		}
		
		return amigoSecreto.getEmailAmigoSorteado();
	}
	
	public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado)
			throws AmigoNaoSorteadoException {
		Amigo amigoSorteado = this.amigo.get(emailAmigoSorteado);
		
		if (amigoSorteado == null) {
			throw new AmigoNaoSorteadoException("Amigo não foi sorteado");
			
		}
		
		amigoSorteado.setEmailAmigoSorteado(emailAmigoSorteado);
	}

	public Map<String, Amigo> getAmigo() {
		return amigo;
	}

	public void setAmigo(Map<String, Amigo> amigo) {
		this.amigo = amigo;
	}

	public List<Mensagem> getMensagem() {
		return mensagem;
	}

	public void setMensagem(List<Mensagem> mensagem) {
		this.mensagem = mensagem;
	}

	public int getMaxMensagens() {
		return maxMensagens;
	}

	public void setMaxMensagens(int maxMensagens) {
		this.maxMensagens = maxMensagens;
	}
 
}
