package Util.Network.Server.Controller;

/**
 * Created by Gioele on 11/06/2016.
 */
public interface Controller {

    void Attach(Observer o);

    void Detach(Observer o);

    void Notify();


}
