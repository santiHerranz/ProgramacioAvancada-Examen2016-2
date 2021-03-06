package decode;

import java.util.Arrays;

/**
 * Created by santi on 16/12/2016.
 */
public class Descodificador {

    private static String codigo = "903900439651484";


    private char lletres[]={'H','A','R','Y','P','O','T','E','L','S'};
    //private char lletres[]={'L','R','Y','S','O','A','H','P','T','E'}; // minim iteracions posibles
    //diferents caràcters a codificar. Domini decisions. Es pot omplenar al constructor
    private int missatge[];
    //missatge a descodificar
    private char solucio[];
    // la posició 0 emmagatzema el caràcter amb codi 0 i així ...
    private boolean marcats[]; // marcatge per no repetició

    private char operand1[] = {'H','A','R','R','Y'};
    private char operand2[] = {'P','O','T','T','E','R'};
    private char suma[] = {'T','R','O','L','L','S'};

    private static int contador;


    public Descodificador(){
        //Exercici 2
        solucio=new char[10];
        //índex es correspon amb el codi: solucio[0] hi ha el caràcter del dígit 0
        marcats=new boolean[10];
        for(int i=0; i<10; i++)marcats[i]=false;
    }

    public Descodificador(int[] missatge){
        this();
        //Exercici 2
        this.missatge=missatge;
    }

    public static void main(String[] args) {

        int []missatge = {9,0,3,9,0,0,4,3,9,6,5,1,4,8,4};
        Descodificador m = new Descodificador(missatge);
        if (m.back1Solucio(0) == true)
            System.out.println(m);
        else
            System.out.println("No hi ha solució");

        System.out.println("contador: "+ contador +" iteracions,  (si Iteracio = 1 seg -> "+ contador/60/60/24 +" dies)");
    }

    public boolean back1Solucio(int k){ //crida principal back1Solucio(0)
        //k és correspon amb el digit
        int i=0; //index de la taula de caracters. Domini del problema
        boolean trobat=false;
        while (i<lletres.length && !trobat){ //amplada caracters 10
            if (!marcats[i]) {
                if (acceptable(lletres[i], k)){
                    marcats[i]=true;
                    //acceptable. Assignar al digit k de la solució <- la lletra i
                    solucio[k] = lletres[i];
                    if (k==9 && sumaOK()){
                        trobat=true;
                    }
                    else if (k<9)
                        trobat=back1Solucio(k+1);
                    if (!trobat)
                        marcats[i]=false;
                }
            }
            i++;
        }
        return trobat;
    }

    private boolean acceptable(char car, int nivell){
        // restricció: els numeros que apareixen en la suma no poden començar per "0"
        // els caràcters H, P, T no poden ser el 0
        return ((nivell==0 && car!='H' && car!='P' && car!='T') || (nivell!=0));
        //return true;
    }

    public boolean sumaOK(){
        contador++; // comptador d'iteracions
        int h=descodificar(operand1);
        int p=descodificar(operand2);
        int t=descodificar(suma);
        return h+p==t;
    }
    public int descodificar(char m[]){
        // El valor total del conjunt de lletres
        // serà la suma dels valors de cada lletra
        // tenint en compte la posició
        int i=0, valorLletra;
        int valorTotal=0;
        while (i<m.length){
            valorLletra= quinValorTeLaLletra(m[i]);
            valorTotal= 10*valorTotal + valorLletra;
            i++;
        }
        return valorTotal;
    }
    public int quinValorTeLaLletra(char m){
        //sabem segur hi és
        //El valor de la lletra és la posició que ocupa a la solució
        int i=0;
        while (solucio[i]!=m)i++;
        return i;
    }




    public String toString() {
        String s = "";
        s += taulaDescodificar();
        s += "\nMissatge codificat: " + Arrays.toString(missatge);
        s += "\nSolucio: " + Arrays.toString(solucio);
        s += "\nMissatge descodificat:";
        for (int i = 0; i < missatge.length; i++) {
            s += solucio[missatge[i]];
        }
        return s;
    }

    private String taulaDescodificar(){
        String resultat="";
        for (int i=0; i<solucio.length; i++)
            resultat+="Dígit "+i+": ha de ser el caràcter "+solucio[i]+"\n";
        return resultat;
    }
}
