package hu.unideb.inf.benocspeter.model;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Dimensions
{
    @XmlElement(required = true)
    private BigDecimal width;

    @XmlElement(required = true)
    private BigDecimal height;

    @XmlElement(required = true)
    private BigDecimal thickness;

    public Dimensions() {}

    public Dimensions(String s) throws ParseException {

        Pattern pattern = Pattern.compile("\\s*([\\d,.]+)\\s*x\\s*([\\d,.]+)\\s*x\\s*([\\d,.]+) mm");
        Matcher matcher = pattern.matcher(s);
        if (! matcher.matches()) throw new ParseException(s, 0);
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(java.util.Locale.ENGLISH);
        df.setParseBigDecimal(true);
        width = (BigDecimal) df.parse(matcher.group(1));
        height = (BigDecimal) df.parse(matcher.group(2));
        thickness = (BigDecimal) df.parse(matcher.group(3));
    }

    public Dimensions(BigDecimal width, BigDecimal height, BigDecimal thickness, BigDecimal weight) {
        this.width = width;
        this.height = height;
        this.thickness = thickness;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getThickness() {
        return thickness;
    }

    public void setThickness(BigDecimal thickness) {
        this.thickness = thickness;
    }



    public String toString() {
        return String.format("%.2f x %.2f x %.2f mm  ", width, height, thickness );
    }

}