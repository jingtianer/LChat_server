package com.example.chatlet

import com.google.gson.Gson
import org.apache.commons.io.IOUtils
import java.util.*
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "has_unread", value = ["/has_unread"])
class has_unread: HttpServlet() {
    val gson = Gson()
    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        var body = IOUtils.toString(req!!.inputStream, "utf-8")
        val data = gson.fromJson<new_mesg.ID>(body, new_mesg.ID::class.java)
        if (Datas.unread_id.containsKey(data.id)) {
            val unread = Datas.unread_id[data.id]!!.toList()
            val res = hashMapOf<String, Datas.User>()
            for(ids in unread) {
                if (Datas.users.containsKey(ids)) {
                    res.put(ids, Datas.users[ids]!!)
                }
            }
            resp!!.outputStream.write(gson.toJson(mapOf("unread" to res)).encodeToByteArray())
            resp!!.outputStream.flush()
            Datas.unread_id[data.id]!!.clear()
            println("give back unread: ${res.toString()}")
        } else {
            resp!!.outputStream.write(gson.toJson(mapOf("unread" to mapOf<String, Datas.User>())).encodeToByteArray())
            resp!!.outputStream.flush()
        }
    }
}