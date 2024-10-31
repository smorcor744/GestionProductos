package com.es.dao

import utils.Consola
import com.es.model.Proveedor
import jakarta.persistence.EntityManager

import jakarta.persistence.TypedQuery
import utils.HibernateUtils

class ProveedorDAO(private val consola: Consola ){



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

    fun readProveedor(id: String): Proveedor? {
        return executeTransaction { em ->
            em.find(Proveedor::class.java, id)
        }
    }

    fun insertProveedor(proveedor: Proveedor) {
        executeTransaction { em ->
            em.persist(proveedor)
        }
    }

    fun updateProducto(proveedor: Proveedor) {
        executeTransaction { em ->
            val productoBD = em.find(Proveedor::class.java, proveedor.id)
            if (productoBD != null) {
                em.merge(proveedor)
            } else {
                consola.write("Proveedor no encontrado")
            }
        }
    }

    fun deleteProveedor(id: String) {
        executeTransaction { em ->
            val proveedorBD = em.find(Proveedor::class.java, id)
            if (proveedorBD != null) {
                em.remove(proveedorBD)
            } else {
                consola.write("Proveedor no encontrado")
            }
        }
    }

    fun obtenerProveedoresConStock(): List<Proveedor>? {
        return executeTransaction { em ->
            val query: TypedQuery<Proveedor> = em.createQuery(
                "SELECT p FROM Proveedor p WHERE p.stock > 0", Proveedor::class.java
            )
            query.resultList
        }
    }

    fun obtenerProveedoresSinStock(): List<Proveedor>? {
        return executeTransaction { em ->
            val query: TypedQuery<Proveedor> = em.createQuery(
                "SELECT p FROM Proveedor p WHERE p.stock = 0", Proveedor::class.java
            )
            query.resultList
        }
    }

    fun obtenerTodosLosProveedores(): List<Proveedor>? {
        return executeTransaction { em ->
            val query: TypedQuery<Proveedor> = em.createQuery(
                "SELECT p FROM Proveedor", Proveedor::class.java
            )
            query.resultList
        }
    }
}
