package hu.unideb.inf.benocspeter.model;


import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Dimensions.class, Resolution.class})
@XmlType(
        propOrder = {
                "name",
                "value",
                "measure",
                "alternativeValue",
                "alternativeMeasure"
        }
)
public class TelevisionProperty {

    @XmlAttribute(required = true)
    private String name;

    @XmlElement(required = true)
    private Object value;

    @XmlElement(required = false)
    private String measure;

    @XmlElement(required = false)
    private Object alternativeValue;

    @XmlElement(required = false)
    private String alternativeMeasure;


    public TelevisionProperty(String name, Object value, String measure, Object value2, String measure2) {
        this.name = name;
        this.value = value;
        this.measure = measure;
        this.alternativeValue = value2;
        this.alternativeMeasure = measure2;
    }
    public TelevisionProperty(String name, Object value,String measure) {
        this.name = name;
        this.value = value;
        this.measure = measure;
    }
    public TelevisionProperty(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public TelevisionProperty( ) {
    }


    @Override
    public String toString() {
        return "TelevisionProperty{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", measure='" + measure + '\'' +
                ", alternativeValue=" + alternativeValue +
                ", alternativeMeasure='" + alternativeMeasure + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Object getAlternativeValue() {
        return alternativeValue;
    }

    public void setAlternativeValue(Object alternativeValue) {
        this.alternativeValue = alternativeValue;
    }

    public String getAlternativeMeasure() {
        return alternativeMeasure;
    }

    public void setAlternativeMeasure(String alternativeMeasure) {
        this.alternativeMeasure = alternativeMeasure;
    }
}
