/**
 * Created by santi on 16/12/2016.
 */
public class Descodificador {

    private static String codigo = "903900439651484";


    private char lletres[]={'H','A','R','Y','P','O','T','E','S','L'};
    //diferents caràcters a codificar. Domini decisions. Es pot omplenar al constructor
    private int missatge[];
    //missatge a descodificar
    private char solucio[];
    // la posició 0 emmagatzema el caràcter amb codi 0 i així ...
    private boolean marcats[];
    // marcatge per no repetició
    private char operand1[];
    private char operand2[];
    private char suma[];

    private static int contador;

    public static void main(String[] args) {

        int []missatge= {9,0,3,9,0,0,4,3,9,6,5,1,4,8,4};
        char []operand1={'H','A','R','R','Y'};
        char []operand2={'P','O','T','T','E','R'};
        char []suma={'T','R','O','L','L','S'};
        Descodificador m=new Descodificador(missatge, operand1, operand2, suma);
        if (m.back1Solucio(0))
            System.out.println(m);
        else System.out.println("No hi ha solució");


        System.out.println("contador: "+ contador +" iteracions,  (si Iteracio = 1 seg -> "+ contador/60/60/24 +" dies)");


    }

    public boolean back1Solucio(int k){ //crida principal back1Solucio(0)
        //k és correspon amb el digit
        int i=0; //index de la taula de caracters. Domini del problema
        boolean trobat=false;
        while (i<10 && !trobat){ //amplada caracters
            if (!marcats[i] && acceptable(lletres[i], k)){ //acceptable. Assignar al digit k el
            //caràcter lletres[i]
                marcats[i]=true;
                solucio[k]=lletres[i];
                if (k==9 && sumaOK()){
                    trobat=true;
                }
                else if (k<9)trobat=back1Solucio(k+1);
                if (!trobat)marcats[i]=false;
            }
            i++;
            contador++;
        }
        return trobat;
    }

    private boolean acceptable(char car, int valor){
        // restricció: els caràcters H, P, T no poden ser el 0
        return ((valor==0 && car!='H' && car!='P' && car!='T') || (valor!=0));
    }

    private boolean sumaOK(){
        int h=descodificar(operand1);
        int p=descodificar(operand2);
        int t=descodificar(suma);
        return h+p==t;
    }
    private int descodificar(char m[]){
        int i=0, codi;
        int valor=0;
        while (i<m.length){
            codi=donaCodi(m[i]);
            valor=10*valor+codi;
            i++;
        }
        return valor;
    }
    private int donaCodi(char m){
        //sabem segur hi és
        int i=0;
        while (solucio[i]!=m)i++;
        return i;
    }


    public Descodificador(int[] missatge, char[] r1, char[] r2, char[] r3){
        //Exercici 2
        this.missatge=missatge;
        this.operand1=r1;
        this.operand2=r2;
        this.suma=r3;
        solucio=new char[10];
        //índex es correspon amb el codi: solucio[0] hi ha el caràcter del dígit 0
        marcats=new boolean[10];
        for(int i=0; i<10; i++)marcats[i]=false;
    }

    public String toString() {
        String des = taulaDescodificar() +"Missatge descodificat: ";
        for (int i = 0; i < missatge.length; i++) {
            des += solucio[missatge[i]];
        }
        return des;
    }

    private String taulaDescodificar(){
        String resultat=" ";
        for (int i=0; i<solucio.length; i++)
            resultat+="Dígit: "+i+" és el caràcter "+solucio[i]+"\n";
        return resultat;
    }
}
