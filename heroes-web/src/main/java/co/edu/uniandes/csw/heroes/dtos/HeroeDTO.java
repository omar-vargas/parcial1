/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.heroes.dtos;

import co.edu.uniandes.csw.heroes.entities.HeroeEntity;

/**
 *
 * @author Profesor
 */
public class HeroeDTO {
    
    private String name;
    
    private String habilidad;
    
    private Long id;
    
    public HeroeDTO(){
        
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    public HeroeDTO(HeroeEntity heroe) {
        this.id = heroe.getId();
        this.name = heroe.getName();
        this.habilidad = heroe.getHabilidad();
    }
    
    public HeroeEntity toEntity() {
        HeroeEntity entity = new HeroeEntity();
        entity.setId(this.id);
        entity.setName(this.name);    
        entity.setHabilidad(this.habilidad);
        return entity;
    }
}
