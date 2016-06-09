package src;

import java.math.BigInteger;

/**
 * Created by Gioele on 09/06/2016.
 */
public class TextEncripter {




    private String messaggioF = "";
    private BigInteger messaggioFinale;


    public TextEncripter(String messaggio){

        String buffer = messaggio;
        String lettera = "";
        for(int i=0;i<buffer.length();i++)
        {

            if((int)buffer.charAt(i) < 100){
                lettera = "0"+(int)buffer.charAt(i);
            }
            else {
                lettera = String.valueOf((int)buffer.charAt(i));
            }

            messaggioF= messaggioF+lettera;
        }


        messaggioFinale = new BigInteger(messaggioF);
    }


    public String getMessaggioF() {
        return messaggioF;
    }

    public void setMessaggioF(String messaggioF) {
        this.messaggioF = messaggioF;
    }

    public BigInteger getMessaggioFinale() {
        return messaggioFinale;
    }

    public void setMessaggioFinale(BigInteger messaggioFinale) {
        this.messaggioFinale = messaggioFinale;
    }
}
