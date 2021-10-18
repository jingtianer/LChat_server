package com.example.chatlet

import com.google.gson.Gson
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "ip_check", value = ["/ip_check"])
class checkIP : HttpServlet(){
    val gson = Gson()
    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        resp!!.outputStream.write(gson.toJson(mapOf("check" to true)).encodeToByteArray())
        resp!!.outputStream.flush()
    }
}