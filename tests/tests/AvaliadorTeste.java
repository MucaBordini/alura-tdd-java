package tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class AvaliadorTeste {

	@Test
	public void deveEntenderLeilaoOrdemCrescente() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 5");

		leilao.propoe(new Lance(joao, 3000));
		leilao.propoe(new Lance(jose, 4000));
		leilao.propoe(new Lance(maria, 5000));

		Avaliador leiloeiro = new Avaliador();

		leiloeiro.avalia(leilao);

		assertEquals(5000, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(3000, leiloeiro.getMenorLance(), 0.00001);

	}

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		Usuario joao = new Usuario("Joao");

		Leilao leilao = new Leilao("Playstation 5");

		leilao.propoe(new Lance(joao, 3000));

		Avaliador leiloeiro = new Avaliador();

		leiloeiro.avalia(leilao);

		assertEquals(3000, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(3000, leiloeiro.getMenorLance(), 0.00001);

	}
	
	@Test
	public void deveEntenderLeilaoValoresDecrescentes() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 5");

		leilao.propoe(new Lance(joao, 5000));
		leilao.propoe(new Lance(jose, 3000));
		leilao.propoe(new Lance(maria, 2000));

		Avaliador leiloeiro = new Avaliador();

		leiloeiro.avalia(leilao);

		assertEquals(5000, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(2000, leiloeiro.getMenorLance(), 0.00001);

	}
	
	@Test
	public void deveEntenderLeilaoComValoresRandomicos() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 5");

		leilao.propoe(new Lance(joao, 3000));
		leilao.propoe(new Lance(jose, 2000));
		leilao.propoe(new Lance(maria, 4100));
		leilao.propoe(new Lance(joao, 1500));
		leilao.propoe(new Lance(jose, 2200));
		leilao.propoe(new Lance(maria, 7000));
		leilao.propoe(new Lance(maria, 1000));
		leilao.propoe(new Lance(joao, 5100));
		leilao.propoe(new Lance(jose, 3300));
		leilao.propoe(new Lance(maria, 1100));

		Avaliador leiloeiro = new Avaliador();

		leiloeiro.avalia(leilao);

		assertEquals(7000, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000, leiloeiro.getMenorLance(), 0.00001);

	}

	@Test
	public void deveEncontrarTresMaioresLances() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 5");

		leilao.propoe(new Lance(joao, 3000));
		leilao.propoe(new Lance(jose, 2000));
		leilao.propoe(new Lance(maria, 4100));
		leilao.propoe(new Lance(joao, 1500));
		leilao.propoe(new Lance(jose, 2200));
		leilao.propoe(new Lance(maria, 7000));
		
		Avaliador leiloeiro = new Avaliador();

		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		assertEquals(3, maiores.size());
		assertEquals(7000, maiores.get(0).getValor(), 0.00001);
		assertEquals(4100, maiores.get(1).getValor(), 0.00001);
		assertEquals(3000, maiores.get(2).getValor(), 0.00001);
	}
	
	@Test
	public void deveEncontrarTresMaioresComApenasDoisLances() {
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jose");

		Leilao leilao = new Leilao("Playstation 5");

		leilao.propoe(new Lance(joao, 3000));
		leilao.propoe(new Lance(jose, 2000));
		
		Avaliador leiloeiro = new Avaliador();

		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		assertEquals(2, maiores.size());
		assertEquals(3000, maiores.get(0).getValor(), 0.00001);
		assertEquals(2000, maiores.get(1).getValor(), 0.00001);
	}
	
	@Test
	public void deveEncontrarTresMaioresComNenhumLance() {

		Leilao leilao = new Leilao("Playstation 5");

		Avaliador leiloeiro = new Avaliador();

		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		assertEquals(0, maiores.size());
	}

}
