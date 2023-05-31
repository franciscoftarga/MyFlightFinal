package pucrs.myflight.modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class GerenciadorCias {
	private HashMap<String, CiaAerea> empresas;
	private static GerenciadorCias instance = null;
	
	private GerenciadorCias() { empresas = new HashMap<>(); }

	public static GerenciadorCias getInstance() {
		if (instance == null)
			instance = new GerenciadorCias();
		return instance;
	}

	public void adicionar(String cod, String nome) { this.empresas.put(cod, new CiaAerea(cod, nome)); }

	public CiaAerea buscarCodigo(String cod) { return this.empresas.get(cod.toUpperCase()); }

	public CiaAerea buscarNome(String cod) {
		for (CiaAerea k : this.listarTodas())
			if (k.getNome().equalsIgnoreCase(cod))
				return k;
		return null;
	}

	public void carregaDados() throws IOException {
		Path path = Paths.get("src/pucrs/myflight/dados/airlines.dat");
		BufferedReader br = Files.newBufferedReader(path, Charset.forName("utf8"));
		String line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] aux = line.split(";", 2);
			this.adicionar(aux[0], aux[1]);
		}
	}
	public ArrayList<CiaAerea> listarTodas() { return new ArrayList<CiaAerea>(empresas.values()); }
}
