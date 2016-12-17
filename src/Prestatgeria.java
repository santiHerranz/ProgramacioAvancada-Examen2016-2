import java.util.ArrayList;
import java.util.List;

public class Prestatgeria {
    public static int capacitatTotal;
    private int espaiOcupat;
    private List<Tematica> contingut;

    public Prestatgeria() {
        espaiOcupat = 0;
        contingut = new ArrayList<Tematica>();
    }

    public void addTematica(String tematica, int mida) {
        this.addTematica(new Tematica(tematica, mida));
    }

    public void addTematica(Tematica p) {
        contingut.add(p);
    }

    public int getEspaiOcupat() {
        return espaiOcupat;
    }

    public void ocuparEspai(int espai) {
        espaiOcupat += espai;
    }

    public String toString() {
        String r = "";
        for (int i = 0; i < contingut.size(); i++) {
            r += contingut.get(i) + "\n";
        }
        return r;
    }
}