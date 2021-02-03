package tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class AvaliadorTeste {
	
	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;
	
	@Before
	public void criaAvaliador() {
		this.leiloeiro = new Avaliador();
	}
	
	@Before
	public void criaUsuario() {
		this.joao = new Usuario("Joao");
		this.jose = new Usuario("Jose");
		this.maria = new Usuario("Maria");
	}
	
	@Test(expected=RuntimeException.class)
	public void naoDeveAvaliarLeilaoSemLance() {
			Leilao leilao = new CriadorDeLeilao().para("Playstation 5")
					.constroi();
			
			leiloeiro.avalia(leilao);
			Assert.fail();
	}

	@Test
	public void deveEntenderLeilaoOrdemCrescente() {
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation 5")
				.lance(joao, 4000)
				.lance(jose, 4200)
				.lance(maria, 4700)
				.lance(joao, 5000)
				.constroi();

		leiloeiro.avalia(leilao);

		assertEquals(5000, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(4000, leiloeiro.getMenorLance(), 0.00001);

	}

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {

		Leilao leilao = new CriadorDeLeilao().para("Playstation 5")
				.lance(joao, 3000)
				.constroi();

		leiloeiro.avalia(leilao);

		assertEquals(3000, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(3000, leiloeiro.getMenorLance(), 0.00001);

	}
	
	@Test
	public void deveEntenderLeilaoValoresDecrescentes() {
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation 5")
				.lance(joao, 5000)
				.lance(jose, 4000)
				.lance(maria, 3000)
				.constroi();

		leiloeiro.avalia(leilao);

		assertEquals(5000, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(3000, leiloeiro.getMenorLance(), 0.00001);

	}
	
	@Test
	public void deveEntenderLeilaoComValoresRandomicos() {

		Leilao leilao = new CriadorDeLeilao().para("Playstation 5")
				.lance(joao, 5000)
				.lance(jose, 5500)
				.lance(maria, 2300)
				.lance(joao, 1200)
				.lance(jose, 4000)
				.lance(maria, 6000)
				.lance(joao, 7000)
				.lance(jose, 6200)
				.lance(maria, 5700)
				.constroi();
		
		leiloeiro.avalia(leilao);

		assertEquals(7000, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1200, leiloeiro.getMenorLance(), 0.00001);

	}

	@Test
	public void deveEncontrarTresMaioresLances() {

		Leilao leilao = new CriadorDeLeilao().para("Playstation 5")
				.lance(joao, 3000)
				.lance(jose, 1200)
				.lance(maria, 5000)
				.lance(joao, 6000)
				.lance(jose, 1000)
				.lance(maria, 2000)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		assertEquals(3, maiores.size());
		assertEquals(6000, maiores.get(0).getValor(), 0.00001);
		assertEquals(5000, maiores.get(1).getValor(), 0.00001);
		assertEquals(3000, maiores.get(2).getValor(), 0.00001);
	}
	
	@Test
	public void deveEncontrarTresMaioresComApenasDoisLances() {

		Leilao leilao = new CriadorDeLeilao().para("Playstation 5")
				.lance(joao, 3000)
				.lance(jose, 2000)
				.constroi();

		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		assertEquals(2, maiores.size());
		assertEquals(3000, maiores.get(0).getValor(), 0.00001);
		assertEquals(2000, maiores.get(1).getValor(), 0.00001);
	}
	
	@BeforeClass
	public static void testandoBeforeClass() {
	  System.out.println("before class");
	}

	@AfterClass
	public static void testandoAfterClass() {
	  System.out.println("after class");
	}

}
