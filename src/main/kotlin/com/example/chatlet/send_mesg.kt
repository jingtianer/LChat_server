package com.example.chatlet

import com.google.gson.Gson
import org.apache.commons.io.IOUtils
import java.util.*
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@WebServlet(name = "send_mesg", value = ["/send_mesg"])
class send_mesg : HttpServlet(){
    val gson = Gson()
    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        var body = IOUtils.toString(req!!.inputStream, "utf-8")
        val data = gson.fromJson<Datas.mesg>(body, Datas.mesg::class.java)
        val uuid = UUID.randomUUID().toString().replace("-", "")
        Datas.add_mesgs(data)
        resp!!.outputStream.write(gson.toJson(mapOf("ok" to "ok")).encodeToByteArray())
        resp!!.outputStream.flush()
        println(data.toString())
    }
}