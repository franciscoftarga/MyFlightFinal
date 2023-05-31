package pucrs.myflight.modelo;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class GerenciadorAeroportos {
    private HashMap<String, Aeroporto> aeroportos;
    private static GerenciadorAeroportos instance = null;

    private GerenciadorAeroportos() { aeroportos = new HashMap<>(); }

    public static GerenciadorAeroportos getInstance() {
        if (instance == null)
            instance = new GerenciadorAeroportos();
        return instance;
    }

    public void adicionar(String cod, Aeroporto aero) { aeroportos.put(cod, aero); }

    
    /* Desnecessário agora, I guess?
     * public void ordenarPorNome() { Collections.sort(aeroportos); }
     */

    public Aeroporto buscarPorCodigo(String cod) { return this.aeroportos.get(cod.toUpperCase()); }

    public void carregaDados() throws IOException {
        Path path = Paths.get("src/pucrs/myflight/dados/airports.dat");
        BufferedReader br = Files.newBufferedReader(path, Charset.forName("utf8"));
        String line = br.readLine();
        while ((line = br.readLine()) != null) {
            String[] aux = line.split(";", 5);
            Geo geo = new Geo(Double.parseDouble(aux[1]), Double.parseDouble(aux[2]));
            this.adicionar(aux[0], new Aeroporto(aux[0], aux[3] + " " + aux[4], geo));
        }
    }
    public ArrayList<Aeroporto> listarTodos() { return new ArrayList<Aeroporto>(aeroportos.values()); }
}
