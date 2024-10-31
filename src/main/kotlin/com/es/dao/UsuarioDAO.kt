package com.es.dao

import utils.Consola
import com.es.model.Usuario
import jakarta.persistence.EntityManager
import utils.HibernateUtils

class UsuarioDAO(private val consola: Consola ) {



    private fun <T> executeTransaction(action: (EntityManager) -> T): T? {
        val em = HibernateUtils.createEntityManager()
        return try {
            em.transaction.begin()
            val result = action(em)
            em.transaction.commit()
            result
        } catch (e: Exception) {
            em.transaction.rollback()
            consola.write("Error: ${e.message}")
            null
        } finally {
            HibernateUtils.closeEntityManager(em)
        }
    }

    fun readUsuario(id: String): Usuario? {
        return executeTransaction { em ->
            em.find(Usuario::class.java, id)
        }
    }

    fun insertUsuario(usuario: Usuario) {
        executeTransaction { em ->
            em.persist(usuario)
        }
    }

    fun updateUsuario(usuario: Usuario) {
        executeTransaction { em ->
            val productoBD = em.find(Usuario::class.java, usuario.nombreUsuario)
            if (productoBD != null) {
                em.merge(usuario)
            } else {
                consola.write("Producto no encontrado")
            }
        }
    }

    fun deleteUsuario(id: String) {
        executeTransaction { em ->
            val usuarioBD = em.find(Usuario::class.java, id)
            if (usuarioBD != null) {
                em.remove(usuarioBD)
            } else {
                consola.write("Usuario no encontrado")
            }
        }
    }


}
