public class Tematica implements Comparable {
    private String nom;
    private int numLlibres;

    public Tematica(String nom, int num) {
        this.nom = nom;
        this.numLlibres = num;
    }

    public String getNom() {
        return nom;
    }

    public int getNumLlibres() {
        return numLlibres;
    }

    public int compareTo(Object o) {
        Tematica t = (Tematica) o;
        if (numLlibres < t.numLlibres) return -1;
        if (numLlibres == t.numLlibres) return 0;
        return 1;
    }

    public String toString() {
        return "Nom de la tematica " + nom + " amb: " + numLlibres;
    }
}
