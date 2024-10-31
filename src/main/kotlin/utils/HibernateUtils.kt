package utils

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence

object HibernateUtils {

    private const val PERSISTENCE_UNIT_NAME = "unidadMySQL"

    private val emf: EntityManagerFactory = buildEntityManagerFactory()

    private fun buildEntityManagerFactory(): EntityManagerFactory {
        return try {
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
        } catch (e: Exception) {
            println("Error al crear EntityManagerFactory: ${e.message}")
            throw RuntimeException("No se pudo inicializar EntityManagerFactory", e)
        }
    }

    fun createEntityManager(): EntityManager {
        return emf.createEntityManager()
    }

    fun closeEntityManager(em: EntityManager?) {
        try {
            em?.let {
                if (it.isOpen) {
                    it.close()
                }
            }
        } catch (e: Exception) {
            println("Error al cerrar EntityManager: ${e.message}")
        }
    }

    fun close() {
        try {
            if (emf.isOpen) {
                emf.close()
            }
        } catch (e: Exception) {
            println("Error al cerrar EntityManagerFactory: ${e.message}")
        }
    }
}
