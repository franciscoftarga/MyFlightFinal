package pucrs.myflight.modelo;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
public class GerenciadorRotas {
    //Rotas indexadas pelo aeroporto de ORIGEM
    private HashMap<Aeroporto, HashSet<Rota>> rotas;
    private static GerenciadorRotas instance = null;

    private GerenciadorRotas() { rotas = new HashMap<>(); }

    public static GerenciadorRotas getInstance() {
        if (instance == null)
            instance = new GerenciadorRotas();
        return instance;
    }

    public void adicionar(Aeroporto origem, Rota rota) {
        if (!rotas.containsKey(origem))
            rotas.put(origem, new HashSet<>());
        rotas.get(origem).add(rota);
    }

    /* Desnecessário também?
     *public void ordenarPorNomeCia() { Collections.sort(rotas); }
     */

    public ArrayList<Rota> buscarPorOrigem(Aeroporto origem) {
        ArrayList<Rota> routes = new ArrayList<>();
        if (origem != null)
            return routes;
        for (Aeroporto k : rotas.keySet())
            if (k.equals(origem))
                routes.addAll(rotas.get(k));
        return routes;
    }

    public void carregaDados() throws IOException {
        Path path = Paths.get("src/pucrs/myflight/dados/routes.dat");
        BufferedReader br = Files.newBufferedReader(path, Charset.forName("utf8"));
        String line = br.readLine();
        GerenciadorAeronaves naves_temp = GerenciadorAeronaves.getInstance();
        GerenciadorCias cias_temp = GerenciadorCias.getInstance();
        GerenciadorAeroportos portos_temp = GerenciadorAeroportos.getInstance();
        while ((line = br.readLine()) != null) {
            String[] aux = line.split(";", 6);
            CiaAerea cia = cias_temp.buscarCodigo(aux[0]);
            Aeroporto de = portos_temp.buscarPorCodigo(aux[1]);
            Aeroporto para = portos_temp.buscarPorCodigo(aux[2]);
            String navCod = aux[5];
            if (aux[5].length() > 3) {
                navCod = navCod.split(" ")[0];
            }
            Aeronave aviao = naves_temp.buscarPorCodigo(navCod);
            this.adicionar(de, new Rota(cia, de, para, aviao));
        }
    }
    public ArrayList<Rota> listarTodas() {
        ArrayList<Rota> aux = new ArrayList<>();
        for (Aeroporto k : rotas.keySet())
            aux.addAll(rotas.get(k));
        return aux;
    }
}
