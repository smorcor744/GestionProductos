package com.es.model

import jakarta.persistence.*

@Entity
@Table(name = "usuario")
class Usuario(

    @Id
    @Column(nullable = false, unique = true)
    val nombreUsuario: String,

    @Column(nullable = false, length = 20)
    var password:String
)