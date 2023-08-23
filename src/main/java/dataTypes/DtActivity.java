package dataTypes;


import java.util.Date;

public class DtActivity {

        private final String name;
        private final String description;
        private final  Integer duration;
        private final  Float price;
        private final  Date registryDate;
        private final int classesQuantity;

        public DtActivity(String name, String description, Integer duration, Float price, Date registryDate, int classesQuantity)
        {
            this.name           	= name;
            this.description    	= description;
            this.duration       	= duration;
            this.price          	= price;
            this.registryDate   	= registryDate;
            this.classesQuantity 	= classesQuantity;
        }

    public int getClassesQuantity() {
		return classesQuantity;
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

}
