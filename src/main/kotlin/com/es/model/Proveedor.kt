package com.es.model

import jakarta.persistence.*

@Entity
@Table(name = "proveedores")
class Proveedor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false, unique = true)
    val nombre: String,

    @Column
    val direccion: String? = null,

    @OneToMany(mappedBy = "proveedor", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val productos: List<Producto> = emptyList()
)
