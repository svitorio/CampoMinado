package br.ufpi.poo.campominado.enums;

/**
 * @author alcemirsantos
 *
 */
public enum Acao {
	MARCAR("Marcar"), INVESTIGAR("Investigar"),VAZIA("Vazia");

	private String descricao;

	private Acao(String umaAcao) {
		this.descricao = umaAcao;
	}

	public String getDescicao() {
		return this.descricao;
	}
}
