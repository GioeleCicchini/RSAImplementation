package src;

import Soggetti.Alice;
import Soggetti.Bob;
import src.Util.KeyGenerator;
import src.Util.MessageGenerator;
import src.Util.NumericTextDecripter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by Gioele on 09/06/2016.
 */
public class Main  {


    public static void main(String [] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {


        KeyGenerator GeneratoreChiavi = KeyGenerator.getSingletonInstance();




        if(new File("ChiavePrivata.bin").exists()){
            GeneratoreChiavi.caricaChiaveDaFile("ChiavePrivata");
        }
        if(new File("ChiavePubblica.bin").exists()){
            GeneratoreChiavi.caricaChiaveDaFile("ChiavePubblica");
        }
        if(!new File("ChiavePubblica.bin").exists()){
            if(!new File("ChiavePrivata.bin").exists()){
                GeneratoreChiavi.generaChiavi();
                GeneratoreChiavi.salvaChiaviSuFile();
            }
        }

        MessageGenerator messageGenerator = new MessageGenerator();
        messageGenerator.importaDaFile();
        List blocchiMessaggio = messageGenerator.getMessaggioBlocchi();



        Bob bob = new Bob();
        Alice alice = new Alice();


        for(int i=0;i<blocchiMessaggio.size();i++){
            alice.setMessaggioNumerico(blocchiMessaggio);
        }


        bob.inviaChiavePubblica(GeneratoreChiavi.getChiavePubblica(),alice);


        System.out.println("messaggio da criptare: "+messageGenerator.getMessaggio());
        String messaggioCifrato = alice.criptaMessaggio();

        System.out.println("Messaggio Criptato: "+messaggioCifrato);

        NumericTextDecripter textDecripter = new NumericTextDecripter(bob.decifraMessaggio(messaggioCifrato));

        System.out.println("Messaggio Decifrato  : "+bob.decifraMessaggio(messaggioCifrato));

        try
        {
            FileOutputStream prova = new FileOutputStream("Output.txt");
            PrintStream scrivi = new PrintStream(prova);

            scrivi.print(textDecripter.getMessaggioDecriptatoStringa());
        }
        catch (IOException e)
        {
            System.out.println("Errore: " + e);
            System.exit(1);
        }



        System.out.println(textDecripter.getMessaggioDecriptatoStringa());





    }


}
