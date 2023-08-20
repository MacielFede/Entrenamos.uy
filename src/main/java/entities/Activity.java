package entities;

import dataTypes.DtClass;
import dataTypes.DtActivity;


import org.hibernate.mapping.Array;
import org.hibernate.mapping.List;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

import java.util.Map;


@Entity
public class Activity {
    @Id
    private String name;

    private String description;
    private Integer duration;
    private Float price;
    @Temporal(TemporalType.TIMESTAMP)
    private Date registryDate;
    @OneToMany(cascade = CascadeType.ALL)
    private Map<String, Class> classes;


    public Activity(){}

    public Activity(String name, String description, Integer duration, Float price,Map<String, Class> classes)
    {
        this.name           = name;
        this.description    = description;
        this.duration       = duration;
        this.price          = price;
        this.classes        = classes;
    }

    // GETTERS
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

    public Date getregistryDate() {
        return registryDate;
    }

    public Map<String, Class> getClasses() {
        return classes;
    }

    // SETTERS

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setregistryDate(Date registryDate) {
        this.registryDate = registryDate;
    }

    // METHODS
    public boolean existsClass(String name) {
        //programar logica
        return false;
    }
    public Map<String, Class> getAssociatedClasses(){
        return classes;
    }

    public Class getClass(String className){
        //programar logica
        return new Class();
    }

    public DtActivity getData(){return new DtActivity(this.name, this.description, this.duration, this.price, this.registryDate);}
}
