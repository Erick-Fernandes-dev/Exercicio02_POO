package entities;

import java.util.ArrayList;
import java.util.List;

public class SistemaAmigo {
	
	private List<Amigo> amigo;
	private List<Mensagem> mensagem;
	
	private int maxMensagens;
	
	public static final int DEFAULT_MAX_MENSAGENS = 1000;
	
	public SistemaAmigo(int maxMensagens) {
		this.amigo = new ArrayList<>();
		this.mensagem = new ArrayList<>();
		this.maxMensagens = maxMensagens;
	}
	
	public SistemaAmigo() {
		this(DEFAULT_MAX_MENSAGENS);
		
	}
	
	public int getMaxMensagens() {
		return maxMensagens;
	}
	
	public void setMaxMensagens(int maxMensagens) {
		this.maxMensagens = maxMensagens;
	}
	
	public void cadastraAmigo(String nomeAmigo, String emailAmigo) throws AmigoJaExistenteException {
		
		Amigo amigo = new Amigo(nomeAmigo, emailAmigo);
		
		if (!(amigo.getEmail().equalsIgnoreCase(emailAmigo))) {
			this.amigo.add(amigo);
		}
		
		else {
			throw new AmigoJaExistenteException("Amigo ja existente");
		}
		
	}
	
	public Amigo pesquisarAmigo(String email) throws AmigoInexistenteException {
		Amigo amig = null;
		
		for (Amigo a : amigo) {
			if (a.getEmail().equalsIgnoreCase(email)) {
				amig = a;
				
			}
		}
		throw new AmigoInexistenteException("Amigo inexistente");
	}
	
	public void enviarMensagemParaTodos(String texto, String emailRemetente, boolean ehAnonima) {
		this.mensagem.add(new MensagemParaTodos(texto, emailRemetente, ehAnonima));
	}
	
	public void enviarMensagemParaAlguem(String texto, String emailRemetente, String emailDestinatario, boolean ehAnonima) {
		this.mensagem.add(new MensagemParaAlguem(texto, emailRemetente, emailDestinatario, ehAnonima));
	}
	
	public List<Mensagem> pesquisaTodasAsMensagens() {
		return this.mensagem;
	}
	
	public List<Mensagem> pesquisaMensagensAnonimas() {
		ArrayList<Mensagem> mensagensAnonimas = new ArrayList<>();
		
		for (Mensagem msg : mensagem) {
			if (msg.ehAnonima()) {
				mensagensAnonimas.add(msg);
			}
		}
		
		return mensagensAnonimas;
	}
	
	public String pesquisaAmigoSecretoDe(String emailDaPessoa) 
			throws AmigoInexistenteException, AmigoNaoSorteadoException {
		
		boolean amigoAchado = false;
		
		for (Amigo a : amigo) {
			if (a.getEmail().equalsIgnoreCase(emailDaPessoa)) {
				amigoAchado = true;
				
				if (a.getEmailAmigoSorteado() == null) {
					throw new AmigoNaoSorteadoException("Amigo não sorteado");
				}
				
				return a.getEmailAmigoSorteado();
			}
			
			if (!amigoAchado) {
				throw new AmigoInexistenteException("Amigo inexistente");
			}
		}
		
		return null;
		
	}
	
	public void configuraAmigoSecretoDe(String emialDaPessoa, String emailAmigoSorteado) 
		throws AmigoInexistenteException {
		
		boolean amigoAchado = false;
		
		for (Amigo a : amigo) {
			if (a.getEmail().equalsIgnoreCase(emialDaPessoa)) {
				a.setEmail(emailAmigoSorteado);
				amigoAchado = true;
			}
		}
		
		if (!amigoAchado) {
			throw new AmigoInexistenteException("Amigo Inexistente no sistema.");
		}
	}
	
	public List<Amigo> todosOsParticipantes() throws AmigoInexistenteException {
		List<Amigo> amig = new ArrayList<Amigo>();
		
		for (Amigo a : amig) {
			System.out.println(a.getNome());
		}
		
		throw new AmigoInexistenteException("Amigos inexistentes");
	}

	public List<Amigo> pesquisaTodosOsParticipantes() {
		// TODO Auto-generated method stub
		return null;
	}

}