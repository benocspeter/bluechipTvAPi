package hu.unideb.inf.benocspeter.api;

import hu.unideb.inf.benocspeter.model.Television;
import hu.unideb.inf.benocspeter.parser.TelevisionParser;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.io.IOException;

public class TelevisionDetailApi extends ServerResource {

    private static String baseURI = "http://www.bluechip.hu";

    @Get
    public Television represent() {
        String termek = getAttribute("termek");

        try{
            TelevisionParser parser = new TelevisionParser(baseURI + "/termek/"+termek);
            return parser.parse();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);
    }

}
