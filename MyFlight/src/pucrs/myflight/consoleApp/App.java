package pucrs.myflight.consoleApp;
import java.io.IOException;
import java.time.LocalDateTime;

import pucrs.myflight.modelo.*;
public class App {

	public static void main(String[] args) {
		System.out.println("\nMyFlight project...");

		GerenciadorCias gerCias = GerenciadorCias.getInstance();
		GerenciadorAeronaves gerAvioes = GerenciadorAeronaves.getInstance();
		GerenciadorAeroportos gerAeroportos = GerenciadorAeroportos.getInstance();
		GerenciadorRotas gerRotas = GerenciadorRotas.getInstance();
		GerenciadorVoos gerVoos = GerenciadorVoos.getInstance();
		try {
			gerCias.carregaDados();
			gerAvioes.carregaDados();
			gerAeroportos.carregaDados();
			gerRotas.carregaDados();

		}
		catch (IOException e) {
			System.out.println("Oops!");
			e.printStackTrace();
		}
		//gerCias.listarTodas().forEach(System.out::println);
		//gerAvioes.listarTodas().forEach(System.out::println);
		//gerAeroportos.listarTodos().forEach(System.out::println);
		//gerRotas.listarTodas().forEach(System.out::println);

		Rota rota = gerRotas.listarTodas().get(0);
		VooEscalas a = new VooEscalas(LocalDateTime.now());
		VooEscalas b = new VooEscalas(LocalDateTime.of(2022, 12, 30, 15, 0, 0));
		VooDireto c = new VooDireto(LocalDateTime.now(), rota);
		a.adicionarRota(rota);
		a.adicionarRota(gerRotas.listarTodas().get(10));
		gerVoos.adicionar(a);
		gerVoos.adicionar(b);
		Geo dis1 = rota.getOrigem().getLocal();
		Geo dis2 = rota.getDestino().getLocal();
		System.out.println(rota + "\n\n" + Geo.distanciaEntrePontos(dis1, dis2) + " KM\n\n" + c.getDuracao());
	}
}
