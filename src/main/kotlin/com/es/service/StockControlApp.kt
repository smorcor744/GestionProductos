package com.es.service


import utils.Consola
import com.es.dao.ProductoDAO
import com.es.dao.ProveedorDAO
import com.es.dao.UsuarioDAO
import com.es.model.Producto
import com.es.model.Proveedor

class StockControlApp(
    private val productoDAO: ProductoDAO,
    private val consola: Consola,
    private val usuarioDAO: UsuarioDAO,
    private val proveedorDAO: ProveedorDAO
) {

    fun altaProducto(nombre: String, categoria: String, descripcion: String?, precioSinIva: Float, stock: Int?, proveedorId: String) {
        val proveedor = proveedorDAO.readProveedor(proveedorId)
        if (proveedor != null) {
            val producto = Producto(nombre = nombre, categoria = categoria, descripcion = descripcion ?: "", precio_sin_iva = precioSinIva, stock = stock, proveedor = proveedor)
            productoDAO.insertProducto(producto)
            consola.write("Producto '$nombre' añadido exitosamente.")
        } else {
            consola.write("Proveedor no encontrado. No se puede agregar el producto.")
        }
    }

    fun bajaProducto(id: String) {
        productoDAO.deleteProducto(id)
        consola.write("Producto con ID '$id' eliminado exitosamente.")
    }

    fun modificarNombreProducto(id: String, nuevoNombre: String) {
        val producto = productoDAO.readProducto(id)
        if (producto != null) {
            val productoModificado = productoDAO.readProducto(id)
            productoDAO.updateProducto(productoModificado)
            consola.write("Nombre del producto con ID '$id' modificado exitosamente a '$nuevoNombre'.")
        } else {
            consola.write("Producto no encontrado.")
        }
    }

    fun modificarStockProducto(id: String, nuevoStock: Int?) {
        val producto = productoDAO.readProducto(id)
        if (producto != null) {
            val productoModificado = producto
            productoDAO.updateProducto(productoModificado)
            consola.write("Stock del producto con ID '$id' modificado exitosamente a '$nuevoStock'.")
        } else {
            consola.write("Producto no encontrado.")
        }
    }

    fun obtenerProducto(id: String): Producto? {
        val producto = productoDAO.readProducto(id)
        if (producto != null) {
            consola.write("Producto encontrado: $producto")
        } else {
            consola.write("Producto no encontrado.")
        }
        return producto
    }

    fun obtenerProductosConStock(): List<Producto>? {
        val productos = productoDAO.obtenerProductosConStock()
        consola.write("Productos con stock:")
        productos?.forEach { println(it) }
        return productos
    }

    fun obtenerProductosSinStock(): List<Producto>? {
        val productos = productoDAO.obtenerProductosSinStock()
        consola.write("Productos sin stock:")
        productos?.forEach { println(it) }
        return productos
    }

    fun obtenerProveedorDeProducto(id: String): Proveedor? {
        val proveedor = productoDAO.obtenerProveedorDeProducto(id)
        if (proveedor != null) {
            consola.write("Proveedor del producto con ID '$id': ${proveedor.nombre}")
        } else {
            consola.write("Producto no encontrado o no tiene proveedor.")
        }
        return proveedor
    }

    fun obtenerTodosLosProveedores(): List<Proveedor>? {
        val proveedores = proveedorDAO.obtenerTodosLosProveedores()
        consola.write("Todos los proveedores:")
        proveedores?.forEach { println(it) }
        return proveedores
    }
}
