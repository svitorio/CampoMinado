package br.ufpi.poo.campominado.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import br.ufpi.poo.campominado.enums.Acao;
import br.ufpi.poo.campominado.enums.EstadoZona;
import br.ufpi.poo.campominado.exceptions.AcaoInvalidaException;
import br.ufpi.poo.campominado.exceptions.BombaExplodiuException;
import br.ufpi.poo.campominado.exceptions.PosicaoInvalidaException;
import br.ufpi.poo.campominado.model.CampoMinado;
import br.ufpi.poo.campominado.model.Coordenada;
import br.ufpi.poo.campominado.model.Jogada;
import br.ufpi.poo.campominado.model.Tabuleiro;
import br.ufpi.poo.campominado.model.Zona;

public class TabuleiroTests {


	@Test
	public void testTabuleiroLimpo() throws PosicaoInvalidaException {
		CampoMinado cm = new CampoMinado();
		cm.reseta();
		Tabuleiro t = cm.getTabuleiro();

		EstadoZona estado;
		for (int x = 0; x < t.getComprimento(); x++) {
			for (int y = 0; y < t.getLargura(); y++) {
				estado = t.getEstado(new Coordenada(x,y));
				assertEquals(EstadoZona.VAZIO, estado);
			}
		}
	}
	@Test(expected = BombaExplodiuException.class)
	public void testInvestigarBomba()  throws PosicaoInvalidaException, AcaoInvalidaException, BombaExplodiuException{
		CampoMinado cm = new CampoMinado();
		cm.reseta();
		
		Tabuleiro t = cm.getTabuleiro();
		for(int x = 0; x < 4; x++) {
			for(int y = 0; y < 4; y++) {
				if(t.getZona(new Coordenada(x,y)).temBomba()) { 
					//System.out.println("Estado:: "+t.getZona(new Coordenada(x,y)).temBomba()+"\nPosicao("+x+","+y+") ::Tembomba\n");
					cm.executa(new Jogada(Acao.INVESTIGAR, new Coordenada(x,y)));
					
				}
			}
		}
		/*if(t.getQtdeZonas(EstadoZona.MARCADO) == t.getQtdeBombas() && t.getQtdeZonasLivres() == 0) {
			assertTrue(cm.temVencedor());
			System.out.println("Deu certo");
		}*/
		
	}
	
	@Test
	public void testMarcarBomba()  throws PosicaoInvalidaException, AcaoInvalidaException, BombaExplodiuException{
		CampoMinado cm = new CampoMinado();
		cm.reseta();
		
		Tabuleiro t = cm.getTabuleiro();
		EstadoZona estado;
		for(int x = 0; x < 4; x++) {
			for(int y = 0; y < 4; y++) {
				if(t.getZona(new Coordenada(x,y)).temBomba()) { 
					//System.out.println("Estado:: "+t.getZona(new Coordenada(x,y)).temBomba()+"\nPosicao("+x+","+y+") ::Tembomba\n");
					cm.executa(new Jogada(Acao.MARCAR, new Coordenada(x,y)));				
				}
			}
		}
		
		assertEquals(t.getQtdeZonas(EstadoZona.MARCADO),t.getQtdeBombas());
	}
	/*@Test
	public void testInvestigarNaoBombas() throws PosicaoInvalidaException, AcaoInvalidaException, BombaExplodiuException{
		CampoMinado cm = new CampoMinado();
		cm.reseta();
		
		Tabuleiro t = cm.getTabuleiro();
		EstadoZona estado;
		for(int x = 0; x < 4; x++) {
			for(int y = 0; y < 4; y++) {
				if(!t.getZona(new Coordenada(x,y)).temBomba()) { 	
					System.out.println("Estado:: "+t.getZona(new Coordenada(x,y)).temBomba()+"\nPosicao("+x+","+y+") ::Tembomba\n");
					cm.executa(new Jogada(Acao.INVESTIGAR, new Coordenada(x,y)));	
				}
			}
		}
		
		assertEquals(t.getQtdeZonasLivres(),0);
	}*/


}
