package hu.unideb.inf.benocspeter.api;


import hu.unideb.inf.benocspeter.model.SearchResults;
import hu.unideb.inf.benocspeter.search.TelevisionSearchService;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.io.IOException;

public class TelevisionSearchApi extends ServerResource {

    @Get("json|xml")
    public SearchResults represent() {
        String queryString = getAttribute("query");
        TelevisionSearchService searchService = new TelevisionSearchService();
        try{
            return searchService.doSearch(queryString);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);
    }

}