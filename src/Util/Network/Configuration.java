package Util.Network;

import Util.Network.TransferObject.DTOMaker;

/**
 * Created by Gioele on 11/06/2016.
 */
public class Configuration {


    private static Configuration singletonInstance = null;
    private String portaServer;
    private String portaClient;
    private String indirizzoIp;

    public Configuration() {
    }

    public static Configuration getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new Configuration();
        }
        return singletonInstance;
    }


    public String getPortaServer() {
        return portaServer;
    }

    public void setPortaServer(String portaServer) {
        this.portaServer = portaServer;
    }

    public String getPortaClient() {
        return portaClient;
    }

    public void setPortaClient(String portaClient) {
        this.portaClient = portaClient;
    }


    public String getIndirizzoIp() {
        return indirizzoIp;
    }

    public void setIndirizzoIp(String indirizzoIp) {
        this.indirizzoIp = indirizzoIp;
    }
}
