package com.es.dao

import utils.Consola
import com.es.model.Producto
import com.es.model.Proveedor
import jakarta.persistence.EntityManager
import jakarta.persistence.TypedQuery
import utils.HibernateUtils

class ProductoDAO(private val consola: Consola) {



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

    fun readProducto(id: String): Producto? {
        return executeTransaction { em ->
            em.find(Producto::class.java, id)
        }
    }

    fun insertProducto(producto: Producto) {
        executeTransaction { em ->
            em.persist(producto)
        }
    }

    fun getProductoByName(nombre: String): Producto? {
        return executeTransaction { em ->
            val query: TypedQuery<Producto> = em.createQuery(
                "SELECT p FROM Producto p WHERE p.nombre = :nombre", Producto::class.java
            )
            query.setParameter("nombre", nombre)

            val productoBD = query.resultList.firstOrNull()

            if (productoBD != null) {
                productoBD
            } else {
                Consola().write("Producto no encontrado")
                null
            }
        }
    }


    fun updateProducto(producto: Producto?) {
        executeTransaction { em ->
            val productoBD = em.find(Producto::class.java, producto?.id)
            if (productoBD != null) {
                em.merge(producto)
            } else {
                consola.write("Producto no encontrado")
            }
        }
    }

    fun deleteProducto(id: String) {
        executeTransaction { em ->
            val productoBD = em.find(Producto::class.java, id)
            if (productoBD != null) {
                em.remove(productoBD)
            } else {
                consola.write("Producto no encontrado")
            }
        }
    }

    fun obtenerProductosConStock(): List<Producto>? {
        return executeTransaction { em ->
            val query: TypedQuery<Producto> = em.createQuery(
                "SELECT p FROM Producto p WHERE p.stock > 0", Producto::class.java
            )
            query.resultList
        }
    }

    fun obtenerProductosSinStock(): List<Producto>? {
        return executeTransaction { em ->
            val query: TypedQuery<Producto> = em.createQuery(
                "SELECT p FROM Producto p WHERE p.stock = 0", Producto::class.java
            )
            query.resultList
        }
    }

    fun obtenerProveedorDeProducto(id: String): Proveedor? {
        return executeTransaction { em ->
            val producto = em.find(Producto::class.java, id)
            producto?.proveedor
        }
    }
}
