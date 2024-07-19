/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucplh.T_Product;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author ADMIN
 */
public class T_ProductDTO implements Serializable{
    
    private int id;
    private String name;
    private String deseription;
    private float price;

    public T_ProductDTO() {
    }

    public T_ProductDTO(int id, String name, String deseription, float price) {
        this.id = id;
        this.name = name;
        this.deseription = deseription;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeseription() {
        return deseription;
    }

    public void setDeseription(String deseription) {
        this.deseription = deseription;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }  
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        T_ProductDTO that = (T_ProductDTO) o;
        return id == that.id &&
                Float.compare(that.price, price) == 0 &&
                Objects.equals(name, that.name) &&
                Objects.equals(deseription, that.deseription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, deseription, price);
    }
    
}
