package hu.unideb.inf.benocspeter.parser;

import hu.unideb.inf.benocspeter.model.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class TelevisionParser {
    private static Logger logger = LoggerFactory.getLogger(TelevisionParser.class);

    private String url;
    public Television parse( ) throws IOException {
        Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
        Television television = new Television();
        ArrayList<String> connectivities = new ArrayList<String>();

        //Product ID
        try{
            String prodId = doc.select("div[id=prod_info] > p").first().text();
            prodId = prodId.split(":")[1].trim();
            television.setProductId(prodId);
            logger.info("Product ID: {}", prodId);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        //Product name
        try{
            String productName = doc.select("div[id=prod_info] > p").first().nextElementSibling().nextElementSibling().text().trim();
            television.setProductName(productName);
            logger.info("Product Name: {}", productName);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        //Short description
        try{
            String shortDescription = doc.select("div[id=prod_info] > span").first().text();
            television.setShortDescription(shortDescription);
            logger.info("Short Description: {}", shortDescription);
        }
        catch (Exception e){
            throw new IOException("Malformed document");
        }

        //Stock information
        try{
            String stockInfo = doc.select("div[id=prod_info] > div > p[id=keszletinfo] > span[id=keszletinfo_jelolo]").first().text();
            television.setStockInformation(stockInfo);
            logger.info("Stock Information: {}", stockInfo);
        }
        catch (Exception e){
            throw new IOException("Malformed document");
        }

        //Gross and net price
        Price grossPrice = null;
        Price netPrice = null;
        String currency ="HUF";
        DecimalFormat df = MonetaryUtil.createDecimalFormat(currency);
        df.setParseBigDecimal(true);

        try{
            String grossPriceString =  doc.select("div[id=prod_info] > div > form[id=addtocart_form] > div > div:nth-child(2)").text().
                    replace(" ","").replace("Ft"," Ft");

            String netPriceString =  doc.select("div[id=prod_info] > div > form[id=addtocart_form] > div > div:nth-child(3)").text().split(":")[1].
                    replace(" ","").replace("Ft"," Ft");

            grossPrice = new Price((BigDecimal) df.parse(grossPriceString),currency);
            netPrice = new Price((BigDecimal) df.parse(netPriceString),currency);
            logger.info("Gross price: {}",grossPrice);
            logger.info("Net price: {}",netPrice);
            television.setGrossPrice(grossPrice);
            television.setNetPrice(netPrice);

        }
        catch (Exception e){
            throw new IOException("Malformed document");
        }


        //Long description
        try{
            String longDescription = doc.select("div.teszt_szoveg").first().text();
            television.setLongDescription(longDescription);
            logger.info("Long description: {}", longDescription);
        }
        catch (Exception e){
            throw new IOException("Malformed document");
        }

        //---SPECIFICATION TABLE ---

        ArrayList<TelevisionProperty> properties = new ArrayList<>();

        Elements elements = doc.select("div[id=termekTBox_co1] > div[id=properties_box] > table > tbody > tr");
        for(int i =0; i< elements.size(); i++){
            try{
                Element e = elements.get(i);
                String key = e.select("td:nth-child(1)").text().trim();
                String value = e.select("td:nth-child(2)").text().trim();
                String[] parts = null;
                switch (key){
                    case "Gyártó":
                        properties.add(new TelevisionProperty(key,value));
                        break;
                    case "Képátló":
                        parts = value.split("/");
                        String valueInCm = parts[0].replaceAll("\\D+","");
                        String valueInCol = parts[1].replaceAll("\\D","");
                        properties.add(new TelevisionProperty(key,valueInCm,"cm",valueInCol,"\""));
                        break;
                    case "Felbontás":
                        parts = value.split(" x");
                        int width = Integer.parseInt(parts[0].replaceAll("\\D+",""));
                        int height = Integer.parseInt(parts[1].replaceAll("\\D+",""));
                        Resolution resolution = new Resolution(width,height);
                        properties.add(new TelevisionProperty(key,resolution,"pixel"));

                        break;
                    case "Kijelzőtípus":
                        properties.add(new TelevisionProperty(key,value));
                        break;
                    case "Megjelenítés":
                        properties.add(new TelevisionProperty(key,value));
                        break;
                    case "Hangszóró teljesítmény":
                        String valueInWatt = value.replaceAll("\\D+","");
                        properties.add(new TelevisionProperty(key,valueInWatt,"W"));
                        break;
                    case "Csatlakozó":
                        parts = e.select("td:nth-child(2)").html().split("<br>");
                        for(String sss : parts){
                            properties.add(new TelevisionProperty(sss,"Van"));
                        }

                        break;
                    case "Térhangzás":
                        properties.add(new TelevisionProperty(key,value));
                        break;
                    case "Szín":
                        properties.add(new TelevisionProperty(key,value));
                        break;
                    case "Méret":
                        Dimensions dimensions = new Dimensions(value);
                        properties.add(new TelevisionProperty(key,dimensions,"mm"));
                        break;
                    case "Súly":
                        DecimalFormat nf = (DecimalFormat) NumberFormat.getNumberInstance(Locale.forLanguageTag("HU"));
                        Double weight = (Double) nf.parse(value.replaceAll("[^\\.,0-9]",""));
                        properties.add(new TelevisionProperty(key,weight,"kg"));
                        break;
                    case "Garancia":
                        properties.add(new TelevisionProperty(key,value));
                        break;

                }
                logger.info(key + ": {}", value);
            }
            catch (Exception e){
                throw new IOException("Malformed document");
            }
        }
        television.setSpecificationTable(properties);


        return television;

    }



    public TelevisionParser(String url) throws IOException{
        this.url=url;
    }



}