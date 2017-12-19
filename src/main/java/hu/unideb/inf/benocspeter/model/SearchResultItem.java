package hu.unideb.inf.benocspeter.model;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        propOrder = {
                "productName",
                "grossPrice",
                "netPrice",
                "displaySize",
                "resolution",
                "displayType",
                "stockInformation",
                "productID",
                "detailsAddress"
        }
)
public class SearchResultItem {

    @XmlElement(required = true)
    private String productName;

    @XmlElement(required = true)
    private Price grossPrice;

    @XmlElement(required = true)
    private Price netPrice;

    @XmlElement(required = false)
    private String displaySize;

    @XmlElement(required = false)
    private String resolution;

    @XmlElement(required = false)
    private String displayType;

    @XmlElement(required = true)
    private String stockInformation;

    @XmlElement(required = true)
    private String productID;

    @XmlAttribute(required = true, name="uri")
    private String detailsAddress;





    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(String displaySize) {
        this.displaySize = displaySize;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public String getStockInformation() {
        return stockInformation;
    }

    public void setStockInformation(String stockInformation) {
        this.stockInformation = stockInformation;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getDetailsAddress() {
        return this.detailsAddress;
    }

    public void setDetailsAddress(String detailsAddress) {
        this.detailsAddress = detailsAddress;
    }
}
