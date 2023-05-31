package pucrs.myflight.modelo;

public class Aeronave implements ContavelInterface{
	private static int instances = 0;
	private String codigo;
	private String descricao;
	
	public Aeronave(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
		instances++;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}

	@Override
	public int getTotalInstances() {
		return instances;
	}

	@Override
	public String toString() {
		return String.format("%s - %s", codigo, descricao);
	}
}
