package Util;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by Gioele on 10/06/2016.
 */
public class KeyGenerator {

    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger n = new BigInteger("100000000000000000000000000000000");

    private static KeyGenerator singletonInstance = null;

    private static BigInteger P;
    private static BigInteger Q;
    private static BigInteger N;
    private static BigInteger E;
    private static BigInteger D;

    private static ChiavePubblicaInterlocutore chiavePubblicaInterlocutore = null;




    public static KeyGenerator getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new KeyGenerator();
        }
        return singletonInstance;
    }


    public void generaChiavi(){

        P = getPrime();
        BigInteger PmenoUno = P.subtract(new BigInteger("1"));

        Q = getPrime();
        BigInteger QmenoUno = Q.subtract(new BigInteger("1"));

        BigInteger PQmenoUno= PmenoUno.multiply(QmenoUno);
        N = P.multiply(Q);

        E=PQmenoUno.subtract(new BigInteger("1"));
        //E=new BigInteger("65537");

        D = E.modInverse(PQmenoUno);



    }

    public BigInteger getPrime(){

        MillerRabin millerRabin = new MillerRabin();
        Random random = new Random();
        BigInteger r;

        do {
            r = new BigInteger(n.bitLength(), random);
        }while (!(r.mod(new BigInteger("2")) != ZERO));


        while(millerRabin.isProbablePrime(r,2) == false) {
            do {
                do {
                    r = new BigInteger(n.bitLength(), random);
                }while (!(r.mod(new BigInteger("2")) != ZERO));
            } while (r.compareTo(n) >= 0);
        }

        System.out.println("Il Numero Casuale    :  " + r + " è primo ");
        return r;
    }



    public static Map<String,BigInteger> getChiavePrivata(){

        Map<String,BigInteger> chiavePrivata = new HashMap<>();

        chiavePrivata.put("D",D);
        chiavePrivata.put("N",N);

        return chiavePrivata;

    }

    public static Map<String,BigInteger> getChiavePubblica(){

        Map<String,BigInteger> chiavePubblica = new HashMap<>();

        chiavePubblica.put("E",E);
        chiavePubblica.put("N",N);

        return chiavePubblica;

    }

    public void setChiavePrivata(Map<String,BigInteger> chiave){
        D = chiave.get("D");
        N = chiave.get("N");
    }


    public void setChiavePubblica(Map<String,BigInteger> chiave){
        E = chiave.get("E");
        N = chiave.get("N");
    }

    public void salvaChiaviSuFile(){

        String chiavePrivataFile = "ChiavePrivata";
        String chiavePubblicaFile = "ChiavePubblica";

        List<String> nomiFile = new ArrayList<>();
        nomiFile.add(chiavePrivataFile);
        nomiFile.add(chiavePubblicaFile);

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        for(int i=0;i<nomiFile.size();i++){
            try {
                fileOutputStream = new FileOutputStream(nomiFile.get(i)+".bin");
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
                Method ChiaveMetodo = this.getClass().getMethod("get"+nomiFile.get(i));
                objectOutputStream.writeObject(ChiaveMetodo.invoke(this));
                objectOutputStream.close();
                fileOutputStream.close();
                System.out.println("Oggetto correttamente salvato su file.");
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

    }

    public void caricaChiaveDaFile(String nomeFile) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        FileInputStream inFile = new FileInputStream(nomeFile+".bin");
        ObjectInputStream inStream = new ObjectInputStream(inFile);

        Map chiave = (Map)inStream.readObject();

        Method ChiaveMetodo = this.getClass().getMethod("set"+nomeFile,Map.class);
        ChiaveMetodo.invoke(this,chiave);

        inStream.close();
        inFile.close();



    }


    public int getLunghezzaN(){
        String Nstringa = N.toString();
        int lunghezza = Nstringa.length();
        return lunghezza;
    }

    public static ChiavePubblicaInterlocutore getChiavePubblicaInterlocutore() {
        return chiavePubblicaInterlocutore;
    }

    public void setChiavePubblicaInterlocutore(ChiavePubblicaInterlocutore chiavePubblicaInterlocutore) {
        this.chiavePubblicaInterlocutore = chiavePubblicaInterlocutore;
    }
}
