package Util.Attack;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Gioele on 14/06/2016.
 */
public class FrazioneContinua {

    List<BigInteger> frazioneContinuaCifre = new ArrayList<>();
    List<Frazione> frazioni = new ArrayList<>();
    Frazione frazionecercata = null;

    public FrazioneContinua() {
    }



    public List<BigInteger> calcolaFrazioneContinua(BigInteger e,BigInteger  n){

        System.out.println("E : "+e);
        System.out.println("N : "+n);
       BigDecimal Nvirgola;

        String eString = e.toString();
        String nString = n.toString();

        BigDecimal eDouble = new BigDecimal(eString);
        BigDecimal nDouble = new BigDecimal(nString);


        Nvirgola= eDouble.divide(nDouble,50, RoundingMode.UP);


        String NIntero = Nvirgola.toString();
        String NvirgolaStringa = Nvirgola.toString();

        String[] NInteroArray = NIntero.split(Pattern.quote("."));

        NIntero = NInteroArray[0];

        BigInteger intero = new BigInteger(NIntero);



        frazioneContinuaCifre.add(intero);

       calcolaIterazione(Nvirgola,1);


        return frazioneContinuaCifre;
    }



    public void calcolaIterazione(BigDecimal i,int j){

        if(j<100 && (i.compareTo(new BigDecimal(0.000000000000009)) == 1)) {


            BigDecimal DecimalNumer = new BigDecimal(i.toString());

            BigDecimal Nvirgola = BigDecimal.ONE.divide(DecimalNumer, 20, RoundingMode.UP);


            String NIntero = Nvirgola.toString();

            String[] NInteroArray = NIntero.split(Pattern.quote("."));

            NIntero = NInteroArray[0];

            BigInteger intero = new BigInteger(NIntero);

            Nvirgola = Nvirgola.subtract(new BigDecimal(NIntero));

            Frazione ultimaFrazione = new Frazione(BigInteger.ONE,intero);




/*
            System.out.println("intero "+intero);
            System.out.println("virgola "+Nvirgola);
*/

            j= j+1;
            frazioneContinuaCifre.add(intero);
            calcolaIterazione(Nvirgola,j );

        }
    }


    public void OttieniFrazione(int i,Frazione frazioneIterazione){

        Frazione frazioneFine = frazioneIterazione;

        if(i>0) {

            int j= i-1;
            Frazione frazionePrecedente = new Frazione(frazioneContinuaCifre.get(j),BigInteger.ONE);

            Frazione frazionesomma = frazioneIterazione.somma(frazionePrecedente);

            frazioneFine = new Frazione(frazionesomma.get_denominatore(),frazionesomma.get_numeratore());

            frazionecercata = frazioneFine;


            OttieniFrazione(j,frazioneFine);



        }else {
            frazionecercata = new Frazione(frazioneFine.get_denominatore(),frazioneFine.get_numeratore());
        }


    }


    public List<BigInteger> getFrazioneContinuaCifre() {
        return frazioneContinuaCifre;
    }

    public void setFrazioneContinuaCifre(List<BigInteger> frazioneContinuaCifre) {
        this.frazioneContinuaCifre = frazioneContinuaCifre;
    }

    public Frazione getFrazionecercata() {
        return frazionecercata;
    }

    public void setFrazionecercata(Frazione frazionecercata) {
        this.frazionecercata = frazionecercata;
    }
}
