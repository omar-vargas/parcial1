/*
MIT License

Copyright (c) 2017 ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.heroes.ejb;

import co.edu.uniandes.csw.heroes.entities.HeroeEntity;
import co.edu.uniandes.csw.heroes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.heroes.persistence.HeroePersistence;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ISIS2603
 */
@Stateless
public class HeroeLogic {
 
    @Inject
    private HeroePersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
   
    public HeroeEntity getHeroe(Long id) {
        return persistence.find(id);
    }
    
    //TODO modificar el método createHeroe
  
    public HeroeEntity createHeroe(HeroeEntity entity) throws BusinessLogicException{
        
        return persistence.create(entity);
    }
}
