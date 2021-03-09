/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.heroes.entities;

import javax.persistence.Entity;

/**
 *
 * @author Profesor
 */
@Entity
public class HeroeEntity extends BaseEntity {
    
    private String name;
    private String habilidad;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public HeroeEntity() {
    }
    
}
