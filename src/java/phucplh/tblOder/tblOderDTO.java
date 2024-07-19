/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucplh.tblOder;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class tblOderDTO implements Serializable{
    private String idOder;
    private Date date;
    private float total;

    public tblOderDTO() {
    }

    public tblOderDTO(String idOder, Date date, float total) {
        this.idOder = idOder;
        this.date = date;
        this.total = total;
    }

    public String getIdOder() {
        return idOder;
    }

    public void setIdOder(String idOder) {
        this.idOder = idOder;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
}
