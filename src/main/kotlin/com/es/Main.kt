package com.es

import com.es.dao.ProductoDAO
import com.es.dao.ProveedorDAO
import com.es.dao.UsuarioDAO
import com.es.service.StockControlApp
import utils.Consola


fun main() {
    val consola = Consola()


    val productoDAO = ProductoDAO(consola)
    val proveedorDAO = ProveedorDAO(consola)
    val usuarioDAO = UsuarioDAO(consola)

    val stockControlApp = StockControlApp(productoDAO, consola, usuarioDAO, proveedorDAO)

    while (true) {
        consola.write("=== Menú de Control de Stock ===")
        consola.write("1. Alta Producto")
        consola.write("2. Baja Producto")
        consola.write("3. Modificación Nombre Producto")
        consola.write("4. Modificación Stock Producto")
        consola.write("5. Obtener Producto")
        consola.write("6. Obtener Productos con Stock")
        consola.write("7. Obtener Productos sin Stock")
        consola.write("8. Obtener Proveedor de Producto")
        consola.write("9. Obtener Todos los Proveedores")
        consola.write("0. Salir")
        consola.write("Seleccione una opción: ")

        val opcion = consola.readNumber()

        when (opcion) {
            1 -> {
                consola.write("Ingrese el nombre del producto:")
                val nombre = consola.read()
                consola.write("Ingrese la categoría del producto:")
                val categoria = consola.read()
                consola.write("Ingrese la descripción del producto (opcional):")
                val descripcion = consola.read()
                consola.write("Ingrese el precio sin IVA:")
                val precioSinIva = consola.readNumber()
                consola.write("Ingrese el stock:")
                val stock = consola.readNumber()
                consola.write("Ingrese el ID del proveedor:")
                val proveedorId = consola.read()

                stockControlApp.altaProducto(nombre, categoria, descripcion,
                    precioSinIva?.toFloat() ?: 0.0f, stock, proveedorId)
            }
            2 -> {
                consola.write("Ingrese el ID del producto a eliminar:")
                val id = consola.read()
                stockControlApp.bajaProducto(id)
            }
            3 -> {
                consola.write("Ingrese el ID del producto a modificar:")
                val id = consola.read()
                consola.write("Ingrese el nuevo nombre del producto:")
                val nuevoNombre = consola.read()
                stockControlApp.modificarNombreProducto(id, nuevoNombre)
            }
            4 -> {
                consola.write("Ingrese el ID del producto a modificar:")
                val id = consola.read()
                consola.write("Ingrese el nuevo stock del producto:")
                val nuevoStock = consola.readNumber()
                stockControlApp.modificarStockProducto(id, nuevoStock)
            }
            5 -> {
                consola.write("Ingrese el ID del producto a obtener:")
                val id = consola.read()
                stockControlApp.obtenerProducto(id)
            }
            6 -> {
                stockControlApp.obtenerProductosConStock()
            }
            7 -> {
                stockControlApp.obtenerProductosSinStock()
            }
            8 -> {
                consola.write("Ingrese el ID del producto:")
                val id = consola.read()
                stockControlApp.obtenerProveedorDeProducto(id)
            }
            9 -> {
                stockControlApp.obtenerTodosLosProveedores()
            }
            0 -> {
                consola.write("Saliendo de la aplicación...")
                break
            }
            else -> {
                consola.write("Opción no válida. Intente de nuevo.")
            }
        }
    }
}
