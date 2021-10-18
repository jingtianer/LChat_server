package com.example.chatlet


import com.google.gson.Gson
import org.apache.commons.io.IOUtils
import java.util.*
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@WebServlet(name = "regist", value = ["/regist"])
class RegistMgr : HttpServlet() {
    class regist(var password:String, var name:String) {}
    val gson = Gson()
    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        var body = IOUtils.toString(req!!.inputStream, "utf-8")
        val data = gson.fromJson<regist>(body, regist::class.java)
        val uuid = UUID.randomUUID().toString().replace("-", "")
        Datas.add_user(uuid, Datas.User(data.password, data.name))
        resp!!.outputStream.write(gson.toJson(mapOf("id" to uuid)).encodeToByteArray())
        resp!!.outputStream.flush()
    }
}