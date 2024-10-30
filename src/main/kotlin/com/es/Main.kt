package org.ventanas.com.es.hibernate


import com.es.model.Producto
import com.es.model.Proveedor
import com.es.model.Usuario
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence

fun main() {
    val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("unidadMySQL")
    val em: EntityManager = emf.createEntityManager()

    em.transaction.begin()

    // Crear y persistir instancias de Usuario
    val usuario1 = Usuario("pepe123", "1234")
    em.persist(usuario1)

    val usuario2 = Usuario("juan456", "password")
    em.persist(usuario2)

    val proveedor1 = Proveedor(1L, "Proveedor1", "Dirección 123", null)
    em.persist(proveedor1)

    val proveedor2 = Proveedor(2L, "Proveedor2", "Dirección 456", null)
    em.persist(proveedor2)

    val producto1 = Producto(
        id = "elelapro1",
        categoria = "electronica",
        nombre = "laptop",
        descripcion = "Laptop de alta gama",
        precio_sin_iva = 1000.0f,
        proveedor = proveedor1,
        stock = 10
    )
    em.persist(producto1)

    val producto2 = Producto(
        id = "elarespro2",
        categoria = "electronica",
        nombre = "resistencia",
        descripcion = "Resistencia para dispositivos electrónicos",
        precio_sin_iva = 5.0f,
        proveedor = proveedor2,
        stock = 100
    )
    em.persist(producto2)

    em.transaction.commit()
    em.close()
}
