package src.Util;

import java.math.BigInteger;

/**
 * Created by Gioele on 09/06/2016.
 */
public class TextNumericEncripter {


    private static TextNumericEncripter singletonInstance = null;

    private String messaggioStringa = "";
    private BigInteger messaggioNumerico;


    public TextNumericEncripter(){}



    public static TextNumericEncripter getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new TextNumericEncripter();
        }
        return singletonInstance;
    }



    public String trasformazioneNumerica(String messaggio){
        String buffer = messaggio;
        String lettera = "";
        messaggioStringa = "";
        for(int i=0;i<buffer.length();i++)
        {

            if((int)buffer.charAt(i) < 100){
                lettera = "0"+(int)buffer.charAt(i);
            }
            else {
                lettera = String.valueOf((int)buffer.charAt(i));
            }

            messaggioStringa = messaggioStringa +lettera;
        }


        return messaggioStringa;
    }

    public String getMessaggioStringa() {
        return messaggioStringa;
    }

    public void setMessaggioStringa(String messaggioStringa) {
        this.messaggioStringa = messaggioStringa;
    }

    public BigInteger getMessaggioNumerico() {
        return messaggioNumerico;
    }

    public void setMessaggioNumerico(BigInteger messaggioNumerico) {
        this.messaggioNumerico = messaggioNumerico;
    }
}
