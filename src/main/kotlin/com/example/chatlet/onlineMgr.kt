package com.example.chatlet

import com.google.gson.Gson
import org.apache.commons.io.IOUtils
import java.util.*
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "online", value = ["/online"])
class onlineMgr : HttpServlet() {
    val gson = Gson()
    var timers = hashMapOf<String, Timer?>()

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val body = IOUtils.toString(req!!.inputStream, "utf-8")
        val data = gson.fromJson<online>(body, online::class.java)
        Datas.add_online_user(data.id, Datas.users[data.id]!!)
        if (timers.containsKey(data.id)) {
            timers[data.id]!!.cancel()
        }
        timers[data.id] = Timer()
        timers[data.id]!!.schedule(object :TimerTask() {
            override fun run() {
                Datas.online_user.remove(data.id)
            }
        },30*1000)

        resp!!.outputStream.write(gson.toJson(mapOf("users" to Datas.online_user)).encodeToByteArray())
        resp!!.outputStream.flush()
        println(gson.toJson(mapOf("users" to Datas.online_user)).toString())


    }
    class online(val id:String) {}
}