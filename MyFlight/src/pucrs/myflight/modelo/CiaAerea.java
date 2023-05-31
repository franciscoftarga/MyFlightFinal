package pucrs.myflight.modelo;

public class CiaAerea implements ContavelInterface {
	private static int numOfInstances = 0;
	private String codigo;
	private String nome;
	
	public CiaAerea(String codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
		numOfInstances++;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getNome() {
		return nome;
	}

	@Override
	public int getTotalInstances() {
		return numOfInstances;
	}
	
	@Override
	public String toString() {
		return String.format("%s - %s", codigo, nome);
	}
}
