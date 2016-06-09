package src;

import java.math.BigInteger;

/**
 * Created by Gioele on 09/06/2016.
 */
public class TextDecripter {


    private String messaggioDecriptatoStringa= "";

    public TextDecripter(BigInteger messaggio){

        String messaggioStringa=messaggio.toString();

        if(messaggioStringa.length()%3 != 0 ){
            messaggioStringa= "0"+messaggioStringa;
        }

        String[] parts = messaggioStringa.split("(?<=\\G...)");

        for(int i=0; i<parts.length ; i++){
            char c = (char)Integer.parseInt(parts[i]);
            messaggioDecriptatoStringa = messaggioDecriptatoStringa+c;
        }

    }

    public String getMessaggioDecriptatoStringa() {
        return messaggioDecriptatoStringa;
    }

    public void setMessaggioDecriptatoStringa(String messaggioDecriptatoStringa) {
        this.messaggioDecriptatoStringa = messaggioDecriptatoStringa;
    }
}
