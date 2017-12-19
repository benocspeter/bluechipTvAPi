package hu.unideb.inf.benocspeter.parser;

import hu.unideb.inf.benocspeter.api.Main;
import hu.unideb.inf.benocspeter.model.Price;
import hu.unideb.inf.benocspeter.model.SearchResultItem;
import hu.unideb.inf.benocspeter.model.SearchResults;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class SearchResultsParser {

    private static Logger logger = LoggerFactory.getLogger(SearchResultsParser.class);



    private int maxItems = 150;

    public ArrayList<SearchResultItem> parse(Document doc) throws IOException {
        ArrayList<SearchResultItem> list = new ArrayList<>();
        Elements productsElement = doc.select(" div[id=eredeti_termekek] > div[id^=termek_]");

        for (int i = 0; i< productsElement.size(); i++) {
            if(maxItems<0 || maxItems <= list.size()) break;

            Element element = productsElement.get(i);

            SearchResultItem item = new SearchResultItem();

            ///Details URI
            try{
                String relativeURI =  element.select("div.listname > b > a ").first().attr("href").split("/")[2];
                item.setDetailsAddress(Main.LOCAL_PATH+":"+Main.LOCAL_PORT+"/details?termek="+ relativeURI);
            }
            catch(Exception e){
                throw new IOException("Malformed Document");
            }


            //Product name
            try {
                String productName = element.select("div.listname > b > a").first().text().trim();
                item.setProductName(productName);
                logger.debug("Product name: {}",productName);
            }
            catch(Exception e){
                throw new IOException("Malformed document");
            }

            //Short description: display size, display type, resolution
            try {
                Elements shortDescription = element.select("div.listname > ul > li");
                int a=0,b=1,c=2;
                if(shortDescription.size()<3){
                    b=0; c=0;
                }
                shortDescription.get(a).children().remove();
                String displaySize = shortDescription.get(a).text().replace("-", "").replace("  ", "").trim();
                item.setDisplaySize(displaySize);
                logger.debug("Display size: {}",displaySize);

                shortDescription.get(b).children().remove();
                String resolution = shortDescription.get(b).text().replace("-", "").replace("  ", "").trim();
                item.setResolution(resolution);
                logger.debug("Resolution: {}",resolution);

                shortDescription.get(c).children().remove();
                String displayType = shortDescription.get(c).text().replace("-", "").replace("  ", "").trim();
                item.setDisplayType(displayType);
                logger.debug("Display type: {}",displayType);
            }
            catch(Exception e){
                throw new IOException("Malformed document");
            }

            //Stock information
            try {
                String stockInformation;
                Elements check = element.select("div.listname > table[id=keszletinfotable]").next().select("tbody > tr > td:nth-child(2)");
                if(check.size() != 0) {
                    stockInformation = check.text().trim();

                }
                else{
                    Element ef = element.select("div.listname").first().clone();
                    Elements efChildren = ef.children();
                    for (Element ec: efChildren) {
                        if(!ec.tag().getName().equals("span"))   ec.remove();
                    }
                    stockInformation = ef.text();
                }

                item.setStockInformation(stockInformation);
                logger.debug("Stock information: {}" , stockInformation);
            }
            catch(Exception e){
                throw new IOException("Malformed document");
            }

            try {
                String productId = element.select("div.listname > div.sku").text().split(":")[1].trim();
                item.setProductID(productId);
                logger.debug("Product ID: {}" , productId);
            }
            catch(Exception e){
                throw new IOException("Malformed document");
            }


            //Gross and net price
            Price grossPrice = null;
            Price netPrice = null;
            String currency ="HUF";
            DecimalFormat df = MonetaryUtil.createDecimalFormat(currency);
            df.setParseBigDecimal(true);

            try{
                String grossPriceString =  element.select("div.listprice > div.price").text().replace(" ","").replace("Ft"," Ft");
                String netPriceString =  element.select("div.listprice > div.netto").text().replace(" ","").replace("Ft"," Ft");

                grossPrice = new Price((BigDecimal) df.parse(grossPriceString),currency);
                netPrice = new Price((BigDecimal) df.parse(netPriceString),currency);

                item.setGrossPrice(grossPrice);
                item.setNetPrice(netPrice);
                logger.debug("Gross price: {}" , grossPrice);
                logger.debug("Net price: {}" , netPrice);
            }
            catch (Exception e){
                throw new IOException("Malformed document");
            }



            list.add(item);

        }

        return list;
    }

    public int getTotalNumberOfItems(Document doc){
        return doc.select("div[id=eredeti_termekek] > div[id^=termek]").size();
    }

    public SearchResults ProvideSearchResults(Document doc) throws IOException {
        ArrayList<SearchResultItem> items = parse(doc);
        SearchResults results = new SearchResults();
        results.setItemsTotal(getTotalNumberOfItems(doc));
        results.setItems(items);
        results.setFrom(1);
        results.setTo(items.size());
        return results;
    }



    public SearchResultsParser(int maxItems) {
        this.setMaxItems(maxItems);
    }

    public SearchResultsParser(){
    }


    public int getMaxItems() {
        return maxItems;
    }

    public void setMaxItems(int maxItems) {
        this.maxItems = maxItems;
    }
}