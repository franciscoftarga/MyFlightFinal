package pucrs.myflight.modelo;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
public class GerenciadorAeronaves {
    private HashMap<String, Aeronave> aeronaves;
    private static GerenciadorAeronaves instance = null;

    private GerenciadorAeronaves() { aeronaves = new HashMap<>(); }

    public static GerenciadorAeronaves getInstance() {
        if (instance == null)
            instance = new GerenciadorAeronaves();
        return instance;
    }

    public void adicionar(String cod, String desc) { this.aeronaves.put(cod, new Aeronave(cod, desc)); }

    public Aeronave buscarPorCodigo(String cod) { return this.aeronaves.get(cod.toUpperCase()); }

    public void carregaDados() throws IOException {        
        Path path = Paths.get("src/pucrs/myflight/dados/equipment.dat");
        BufferedReader br = Files.newBufferedReader(path, Charset.forName("utf8"));
        String line = br.readLine();
        while ((line = br.readLine()) != null) {
            String[] aux = line.split(";", 3);
            this.adicionar(aux[0], aux[1]);
        }
    }
    public ArrayList<Aeronave> listarTodas() { return new ArrayList<Aeronave>(aeronaves.values()); }
}
