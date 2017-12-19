package hu.unideb.inf.benocspeter.api;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;

public class Main extends Application {

    static {
        System.setProperty("org.restlet.engine.loggerFacadeClass", "org.restlet.ext.slf4j.Slf4jLoggerFacade");
    }
    public static final String LOCAL_PATH = "http://localhost";
    public static final int LOCAL_PORT = 8111;

    public static void main(String[] args){
        Server server  = new Server(Protocol.HTTP,LOCAL_PORT,new Main());
        try{
            server.start();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public Restlet createInboundRoot(){
        Routing routing = new Routing(getContext(),LOCAL_PATH,LOCAL_PORT);
        routing.getRouter().setDefaultMatchingQuery(true);
        routing.attach("/tvsearch?query={query}", TelevisionSearchApi.class);
        routing.attach("/details?termek={termek}", TelevisionDetailApi.class);
        return routing.getRouter();
    }
}

