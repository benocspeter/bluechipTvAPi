package hu.unideb.inf.benocspeter.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resolution {

    @XmlElement
    private int width;

    @XmlElement
    private int height;

    public Resolution(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public Resolution( ) {

    }
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
