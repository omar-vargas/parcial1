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
package co.edu.uniandes.csw.heroes.persistence;

import co.edu.uniandes.csw.heroes.entities.HeroeEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ISIS2603
 */
@Stateless
public class HeroePersistence {

    private static final Logger LOGGER = Logger.getLogger(HeroePersistence.class.getName());

    @PersistenceContext(unitName = "HeroePU")
    protected EntityManager em;

    /**
     * Busca si hay algun heroe con el nombre que se env�a de argumento
     *
     * @param name: Nombre del heroe que se est� buscando
     * @return null si no existe ningun heroe con el nombre del argumento. Si
     * existe alguno devuelve la primera.
     */
    public HeroeEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando heroe por nombre ", name);

        // Se crea un query para buscar heroes con el nombre que recibe el m�todo como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From HeroeEntity e where e.name = :name", HeroeEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<HeroeEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }

  
    public HeroeEntity find(Long id) {
        return em.find(HeroeEntity.class, id);
    }
    
    //TODO método crear de heroe

    public HeroeEntity create(HeroeEntity entity){
        em.persist(entity);
        return entity;
    }
}
