package Util;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gioele on 10/06/2016.
 */
public class MessageGenerator {


    String messaggio;

    public MessageGenerator() {}


    public void importaDaFile() throws IOException {

        File name = new File("prova.txt");
        if (name.isFile()) {
            try {
                BufferedReader input = new BufferedReader((new InputStreamReader(new FileInputStream(name),"ISO-8859-1")));
                StringBuffer buffer = new StringBuffer();
                String text;
                while ((text = input.readLine()) != null)
                buffer.append(text);
                input.close();
                messaggio = buffer.toString();
            } catch (IOException ioException) {
            }
        }

        messaggio = TextNumericEncripter.getSingletonInstance().trasformazioneNumerica(messaggio);


    }


    public List<BigInteger> getMessaggioBlocchi(){

        List<BigInteger> messaggioBlocchi ;

        int lunghezzaBlocchi = KeyGenerator.getSingletonInstance().getLunghezzaN();
        while(lunghezzaBlocchi % 3 != 0) {
            lunghezzaBlocchi--;
        }

        messaggioBlocchi = getParts(messaggio,lunghezzaBlocchi);

        return messaggioBlocchi;

    }

    private static List<BigInteger> getParts(String string, int partitionSize) {
        List<BigInteger> parts = new ArrayList<>();
        int len = string.length();
        for (int i=0; i<len; i+=partitionSize)
        {
            parts.add(new BigInteger(string.substring(i, Math.min(len, i + partitionSize))));
        }
        return parts;
    }


    public String getMessaggio() {

        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }
}
