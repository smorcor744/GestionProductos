package utils

class Consola {

    fun write(string: String, jump: Boolean = true){
        if (jump){
            println(string)
        }else print(string)
    }

    fun read(): String {
        return readLine().toString()
    }
    fun  readNumber(): Int? {
        try {
            val num = readln().toInt()
            return num
        } catch (e: Exception){
            write(e.message.toString())
            return null
        }
    }
}