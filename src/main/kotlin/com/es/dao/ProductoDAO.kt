package com.es.dao

import com.es.model.Producto
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence

class ProductoDAO {

    companion object {
        private val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("gestorTienda")
        private val em: EntityManager = emf.createEntityManager()
    }

    fun readProducto(producto: Producto): Producto? {
        em.transaction.begin()
        try {
            val productoBD = em.find(Producto::class.java,producto.id)

            em.transaction.commit()
            return productoBD

        }catch (e:Exception){
            em.transaction.rollback()
            println(e)
        } finally {
            em.close()
        }

        return null

    }
    fun readProductoWithStock(): List<Producto>? {
        em.transaction.begin()
        try {
//            val productoBD = em.find(Producto::class.java,)
//
//            em.transaction.commit()
//            return productoBD

        }catch (e:Exception){
            em.transaction.rollback()
            println(e)
        } finally {
            em.close()
        }

        return null

    }

    fun insertProducto(producto: Producto){
        em.transaction.begin()
        try {

            em.persist(producto)
            em.transaction.commit()

        }catch (e:Exception){
            em.transaction.rollback()
            println(e)
        } finally {
            em.close()
        }


    }

    fun updateProducto(producto: Producto){
        em.transaction.begin()
        try {
            val productoBD = em.find(Producto::class.java,producto.id)
            if (productoBD != null) {
                em.merge(producto)
                em.transaction.commit()
            }else{
                println("Producto no encontrado")
            }

        }catch (e:Exception){
            em.transaction.rollback()
            println(e)
        } finally {
            em.close()
        }

    }

    fun deleteProducto(producto: Producto){

        em.transaction.begin()
        try {
            var productoBD = em.find(Producto::class.java,producto.id)

            em.remove(producto)
            em.transaction.commit()

        }catch (e:Exception){
            em.transaction.rollback()
            println(e)
        } finally {
            em.close()
        }

    }
}