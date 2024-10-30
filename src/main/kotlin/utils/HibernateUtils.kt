package utils

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence

object HibernateUtils {
    val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("unidadMySQL")
    val em: EntityManager = emf.createEntityManager()


    private fun createEntityFactory(persistence: String): EntityManagerFactory? {
        try {
            return Persistence.createEntityManagerFactory(persistence)
        } catch (e:Exception){
            println(e.message)
            return null
        }


    }
    fun createEntityManager(persistence: String): EntityManager? {
        try {
            return createEntityFactory(persistence)?.createEntityManager()
        } catch (e:Exception){
            println(e.message)
            return null
        }


    }


    fun closeEntityFactory(entityManagerFactory: EntityManagerFactory){
        try {
            if (entityManagerFactory.isOpen){
                entityManagerFactory.close()
            }
        } catch (e:Exception){
            println(e.message)
        }


    }



    fun closeEntityManager(entityManager: EntityManager){
        try {
            if (entityManager.isOpen){
                entityManager.close()
            }
        } catch (e:Exception){
            println(e.message)
        }

    }
}