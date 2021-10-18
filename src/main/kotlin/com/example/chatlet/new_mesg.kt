package com.example.chatlet

import com.google.gson.Gson
import org.apache.commons.io.IOUtils
import java.util.*
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "new_mesg", value = ["/new_mesg"])
class new_mesg: HttpServlet() {
    class ID(val id:String){}
    val gson = Gson()
    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        var body = IOUtils.toString(req!!.inputStream, "utf-8")
        val data = gson.fromJson<ID>(body, ID::class.java)
        val uuid = UUID.randomUUID().toString().replace("-", "")
        if (Datas.mesgs.containsKey(data.id)) {
            val res = Datas.mesgs[data.id]
            resp!!.outputStream.write(gson.toJson(mapOf("data" to res)).encodeToByteArray())
            resp!!.outputStream.flush()
            Datas.mesgs[data.id]!!.clear()
            println("give back: ${res.toString()}")
        } else {
            resp!!.outputStream.write(gson.toJson(mapOf("data" to arrayListOf<Datas.mesg>())).encodeToByteArray())
            resp!!.outputStream.flush()
        }
    }
}