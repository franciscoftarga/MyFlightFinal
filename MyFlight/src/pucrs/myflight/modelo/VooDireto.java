package pucrs.myflight.modelo;

import java.time.Duration;
import java.time.LocalDateTime;

public class VooDireto extends Voo {
    private Rota rota;
    public VooDireto(LocalDateTime dh, Rota rota) {
        super(dh);
        this.rota = rota;
    }

    @Override
    public Duration getDuracao() {
        double dist = Geo.distanciaEntrePontos(rota.getOrigem().getLocal(),
                                               rota.getDestino().getLocal()) / 805;
        return Duration.ofHours((long) dist + 1);
    }

    @Override
    public Rota getRota() {
        return rota;
    }
    @Override
    public String toString() {
        return String.format("--\n%s\n--\n%s", rota, super.toString());
    }
}
