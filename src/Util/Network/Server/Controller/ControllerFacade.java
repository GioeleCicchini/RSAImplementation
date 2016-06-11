package Util.Network.Server.Controller;

import Util.Network.TransferObject.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Gioele on 11/06/2016.
 */
public class ControllerFacade implements Controller {

    private String funzione;
    private List<Serializable> oggettiPersistenti;
    private List<Observer> osservatoriRichieste = new ArrayList<Observer>();


    public List<Observer> getOsservatoriRichieste() {
        return osservatoriRichieste;
    }

    public void setOsservatoriRichieste(List<Observer> osservatoriRichieste) {
        this.osservatoriRichieste = osservatoriRichieste;
    }

    public void ControllerFacade() {

    }


    public String getFunzione() {
        return this.funzione;
    }


    public void ArrivaRichiesta(DTO dto) {
        this.funzione = dto.getFunzione();
        this.oggettiPersistenti = dto.getOggettiTrasferimento();

        this.Notify();
    }

    @Override
    public void Attach(Observer o) {
        osservatoriRichieste.add(o);
    }

    @Override
    public void Detach(Observer o) {
        osservatoriRichieste.remove(o);
    }

    @Override
    public void Notify() {
        for (Observer osservatore : osservatoriRichieste) {
            osservatore.update(this);
        }
    }

    private void prelevato() {
        this.funzione = null;
        this.oggettiPersistenti.clear();

    }

    public List<Serializable> getOggettiPersistenti() {
        return oggettiPersistenti;
    }

    public void setOggettiPersistenti(List<Serializable> oggettiPersistenti) {
        this.oggettiPersistenti = oggettiPersistenti;
    }

}
