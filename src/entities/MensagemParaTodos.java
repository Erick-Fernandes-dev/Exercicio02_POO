package entities;

public class MensagemParaTodos extends Mensagem {

	public MensagemParaTodos(String texto, String emailRemetente, boolean anonima) {
		super(texto, emailRemetente, anonima);

	}

	@Override
	public String getTextoCompletoAExibir() {
		
		if (super.ehAnonima()) {
			return "Mensagem para todos\nTexo: " + getTexto();
		} else {
			return "Mensagem de " + super.getEmailRemetente() +
					" para todos\n" + "Texto: " + super.getTexto(); 
		}
	}
	
	

}
