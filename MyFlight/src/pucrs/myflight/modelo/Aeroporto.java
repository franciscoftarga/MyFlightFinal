package pucrs.myflight.modelo;

public class Aeroporto implements Comparable<Aeroporto> {
	private String codigo;
	private String nome;
	private Geo loc;
	
	public Aeroporto(String codigo, String nome, Geo loc) {
		this.codigo = codigo;
		this.nome = nome;
		this.loc = loc;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Geo getLocal() {
		return loc;
	}

	public boolean equals(Aeroporto aeroporto) {
		return this.codigo.equals(aeroporto.codigo) &&
			   this.nome.equals(aeroporto.nome) &&
			   this.loc.equals(aeroporto.loc);
	}

	@Override
	public int compareTo(Aeroporto o) {
		return this.nome.compareTo(o.nome);
	}

	@Override
	public String toString() {
		return String.format("%s - %s, %s", codigo, nome, loc);
	}
}
