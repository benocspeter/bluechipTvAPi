package hu.unideb.inf.benocspeter.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        propOrder = {
                "productName",
                "productId",
                "grossPrice",
                "netPrice",
                "stockInformation",
                "shortDescription",
                "longDescription",
                "specificationTable",
                "url"
        }
)
public class Television {


    @XmlAttribute(required = true)
    private String productId;

    @XmlElement(required = true)
    private String productName;

    @XmlElement(required = false)
    private String shortDescription;

    @XmlElement(required = false)
    private String longDescription;

    @XmlElement(required = false, name = "property")
    @XmlElementWrapper( name ="properties")
    private List<TelevisionProperty> specificationTable;

    @XmlElement(required = true)
    private Price grossPrice;

    @XmlElement(required = true)
    private Price netPrice;

    @XmlElement(required = true)
    private String stockInformation;

    @XmlAttribute(required = true)
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }




    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getStockInformation() {
        return stockInformation;
    }

    public void setStockInformation(String stockInformation) {
        this.stockInformation = stockInformation;
    }
    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public List<TelevisionProperty> getSpecificationTable() {
        return specificationTable;
    }

    public void setSpecificationTable(List<TelevisionProperty >specificationTable) {
        this.specificationTable = specificationTable;
    }



    public Price getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(Price grossPrice) {
        this.grossPrice = grossPrice;
    }

    public Price getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(Price netPrice) {
        this.netPrice = netPrice;
    }



}
