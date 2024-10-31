package com.es.model

import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "productos")
class Producto(

    @Id
    var id: String = "",

    @Column(nullable = false)
    val categoria: String,

    @Column(nullable = false, unique = true)
    val nombre: String,

    @Column
    val descripcion: String,

    @Column(nullable = false)
    val precio_sin_iva: Float,

    @Column(nullable = false)
    val precio_con_iva: Float = (precio_sin_iva+(precio_sin_iva * 0.21)).toFloat(),

    @Column(name = "fecha_alta")
    val fecha_alta: Date = Date.from(Instant.now()),

    @Column
    val stock: Int?,

    @ManyToOne(cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    @JoinColumn(name = "proveedor_id", nullable = false)
    val proveedor: Proveedor

){
    init {
        id = generarId()
    }
    private fun generarId(): String {
        val lista= mutableListOf<String>()
        val nom = nombre.split("")
        val cat = categoria.split("")
        val prove = proveedor.nombre.split("")
        var cont1 = 0
        var cont2 = 0
        var cont3 = 0

        nom.forEach {
            if (it !=" "&& cont1 != 3){
                cont1++
                lista.add(it)

            }
        }
        cat.forEach {
            if (it !=" "&& cont2 != 3){
                cont2++
                lista.add(it)

            }
        }
        prove.forEach {
            if (it !=" "&& cont3 != 3){
                cont3++
                lista.add(it)

            }
        }
        return lista.toString()
    }
}

