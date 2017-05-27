package Llibreria;

import java.util.Arrays;

public class Aplicacio {

    private Tematica[] candidats;
    //atributs afegits
    private Prestatgeria[] prestatgeries;
    private int quantes;
    private String noUbicats;

    public Aplicacio(int quants) {
        candidats = new Tematica[quants];
        omplenaDades(); //Creem i inicialitzem atributs afegits
        prestatgeries = new Prestatgeria[quants]; // cas pitjor: una per cads tema
        quantes = 0;
        noUbicats="";
    }


    public static void main(String args[]) {
        System.out.println("Indica la capacitat que tenen les prestatgeries que tens");
        Prestatgeria.capacitatTotal = 30; //Llibreria.Keyboard.readInt();
        System.out.println(Prestatgeria.capacitatTotal);
        System.out.println("Quantes tematiques diferents vols ubicar? ");
        int quants = 8; //Llibreria.Keyboard.readInt();
        System.out.println(quants);
        //Completar main
        Aplicacio m = new Aplicacio(quants);

        System.out.println("Dades entrades:\n**********");
        for (Tematica c:m.candidats ) {
            System.out.println(c);
        }

        m.solucio();

        System.out.println("\nNecessitem " + m.quantes + " prestatgeries");
        System.out.println("Distribucio:\n"+ m);
        System.out.println("Tematiques no ubicades: ");
        if (m.noUbicats!="")System.out.println("\n"+m.noUbicats);
        else System.out.println("Totes estan ubicades");
    }


    private void omplenaDades() {
        candidats[0] = new Tematica("tema 2", 20);
        candidats[1] = new Tematica("tema 1", 25);
        candidats[2] = new Tematica("tema 0", 30);
        candidats[3] = new Tematica("tema 5", 15);
        candidats[4] = new Tematica("tema 4", 15);
        candidats[5] = new Tematica("tema 3", 5);
        candidats[6] = new Tematica("tema 6", 10);
        candidats[7] = new Tematica("tema 7", 10);
    }


    public void solucio() {
        // ordena de menys a mes capacitat les tematiques
        Arrays.sort(candidats);
        // recorregut invers de tots els candidats
        for (int i = candidats.length - 1; i >= 0; i--) {
            int quina = funcioSeleccio(candidats[i]);
            switch (quina) {
                case -1: // no la ubiquem
                    noUbicats += candidats[i].getNom() + "\n";
                    break;
                case -2: // estanteria nova
                    prestatgeries[quantes] = new Prestatgeria();
                    prestatgeries[quantes].addTematica(candidats[i]);
                    prestatgeries[quantes].ocuparEspai(candidats[i].getNumLlibres());
                    quantes++;
                    break;
                default: // ho possem a quina
                    prestatgeries[quina].addTematica(candidats[i]);
                    prestatgeries[quina].ocuparEspai(candidats[i].getNumLlibres());
                    break;
            }
        }
    }


    private int funcioSeleccio(Tematica p) {
        // buscar la prestatgeria on hi cap i sobre menys espai
        if (p.getNumLlibres() > Prestatgeria.capacitatTotal) return -1;//no hi cap
        int quina = -1;
        int espaiSobrant = Prestatgeria.capacitatTotal;
        // recorregut per les prestatgeries existents per buscar espai
        for (int j = 0; j < quantes; j++) {
            int espai = prestatgeries[j].getEspaiOcupat() + p.getNumLlibres();
            if (espai <= Prestatgeria.capacitatTotal) {
                // comparar l'espai sobrant entre les prestatgeries existents
                if (Prestatgeria.capacitatTotal - espai < espaiSobrant) {
                    quina = j;
                    espaiSobrant = Prestatgeria.capacitatTotal - espai;
                }
            }
        }
        if (quina == -1) return -2; // una nova
        else return quina;
    }

    public String toString() {
        String re = "";
        for (int i = 0; i < quantes; i++) {
            re += "Llibreria.Prestatgeria "+ i +" hi ubiquem: \n"
            + prestatgeries[i].toString() +"\n";
        }
        return re;
    }
}

