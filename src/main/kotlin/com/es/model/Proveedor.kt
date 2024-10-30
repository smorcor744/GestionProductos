package com.es.model

import jakarta.persistence.*

@Entity
@Table(name = "proveedores")
class Proveedor (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long?,

    @Column(nullable = false, unique = true)
    val nombre:String,

    @Column
    val direccion:String,

    @OneToMany(mappedBy = "proveedor" )
//    @JoinColumn
    val productos:List<Producto>?


)