package dataTypes;

import entities.Class;

import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Map;
import java.util.Date;

public class DtActivity {

        private final String name;
        private final String description;
        private final  Integer duration;
        private final  Float price;
        private final  Date registryDate;
        private final Map<String, Class> classes;

        public DtActivity(String name, String description, Integer duration, Float price, Date registryDate, Map<String, Class> classes)
        {
            this.name           = name;
            this.description    = description;
            this.duration       = duration;
            this.price          = price;
            this.registryDate   = registryDate;
            this.classes        = classes;
        }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getDuration() {
        return duration;
    }

    public Float getPrice() {
        return price;
    }

    public Date getRegistryDate() {
        return registryDate;
    }

    public Map<String, Class> getClasses() {
        return classes;
    }
}
