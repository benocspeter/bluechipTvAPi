package hu.unideb.inf.benocspeter.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        propOrder = {
                "itemsTotal",
                "from",
                "to",
                "items"
        }
)
public class SearchResults {



    @XmlAttribute(required = true ,name="amountOfItems")
    private int itemsTotal;

    @XmlAttribute(required = true)
    private int from;

    @XmlAttribute(required = true)
    private int to;

    @XmlElement(name = "item")
    private ArrayList<SearchResultItem> items;

    public SearchResults() {
        this.items   = new ArrayList<SearchResultItem>();
    }

    public SearchResults(int itemsTotal, int from, int to, ArrayList<SearchResultItem> items) {
        this.itemsTotal = itemsTotal;
        this.from = from;
        this.to = to;
        this.items = items;
    }

    public int getItemsTotal() {
        return itemsTotal;
    }

    public void setItemsTotal(int itemsTotal) {
        this.itemsTotal = itemsTotal;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public List<SearchResultItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<SearchResultItem> items) {
        this.items = items;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
