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
public class HeroeDetailDTO extends HeroeDTO{
    
    public HeroeDetailDTO(){
        
    }
    
    public HeroeDetailDTO(HeroeEntity entity){
        super(entity);
    }
    
}
