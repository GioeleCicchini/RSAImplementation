package Util;

/**
 * Created by Gioele on 09/06/2016.
 */
public class NumericTextDecripter {


    private String messaggioDecriptatoStringa= "";

    public NumericTextDecripter(String messaggio){



        String[] parts = messaggio.split("(?<=\\G...)");

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
