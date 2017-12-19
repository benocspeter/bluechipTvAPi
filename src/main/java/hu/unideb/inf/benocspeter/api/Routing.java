package hu.unideb.inf.benocspeter.api;

import org.restlet.Context;
import org.restlet.routing.Router;

public class Routing {

    private Router router;
    private String basePath;
    private int port;

    public Routing(Context context, String basePath, int port){
        this.basePath=basePath;
        this.router  = new Router(context);
        this.port=port;
        router.setDefaultMatchingQuery(true);
    }

    public void attach(String s, Class c){
        this.router.attach(this.basePath + ":" + String.valueOf(this.port) + s, c);

    }
    public Router getRouter(){
        return this.router;
    }
}
