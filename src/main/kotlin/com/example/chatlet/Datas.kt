package com.example.chatlet


class Datas {
    class mesg(val time:Long,val  content:String,val  to_id:String, val  from_id:String){
        override fun toString(): String {
            return "mesg(time=$time, content='$content', to_id='$to_id', from_id='$from_id')"
        }
    }
    companion object {
        var online_user = hashMapOf<String, User>()
        var users = hashMapOf<String, User>()

        var mesgs = hashMapOf<String, ArrayList<mesg>>()

        var unread_id = hashMapOf<String, MutableSet<String>>()
        fun add_mesgs(mesg: mesg) {
            if (mesgs.containsKey(mesg.to_id)) {
                mesgs[mesg.to_id]!!.add(mesg)
            } else {
                mesgs[mesg.to_id] = arrayListOf(mesg)
            }
            if (unread_id.containsKey(mesg.to_id)) {
                unread_id[mesg.to_id]!!.add(mesg.from_id)
            } else {
                unread_id[mesg.to_id] = mutableSetOf(mesg.from_id)
            }
            println(mesgs.size)
        }

        fun add_online_user(id:String, user:User) {
            if (!online_user.containsKey(id)) {
                online_user[id] = user
                println(online_user.toString())
            }
        }
        fun add_user(id:String, user:User) {
            if(!users.containsKey(id)) {
                users[id] = user
                println(users.toString())
            }
        }
    }
    class User(var password:String, var name:String) {
    }
}