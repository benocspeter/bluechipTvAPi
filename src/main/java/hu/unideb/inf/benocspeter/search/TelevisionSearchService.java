package hu.unideb.inf.benocspeter.search;

import hu.unideb.inf.benocspeter.model.SearchResults;
import hu.unideb.inf.benocspeter.parser.SearchResultsParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class TelevisionSearchService extends SearchResultsParser{

    private static String TELEVISION_URL = "http://www.bluechip.hu/kereses";
    private static String TELEVISION_CATEGORY_ID = "322";

    public TelevisionSearchService(){

    }

    public TelevisionSearchService(int maxItems) {
        super(maxItems);
    }

    public SearchResults doSearch(String query) throws IOException {

        Document doc = Jsoup.connect(TELEVISION_URL).userAgent("Mozilla").data("q",query).data("katids",TELEVISION_CATEGORY_ID).get();

        return ProvideSearchResults(doc);
    }



}