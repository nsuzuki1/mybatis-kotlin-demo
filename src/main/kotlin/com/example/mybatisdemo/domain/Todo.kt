package com.example.mybatisdemo.domain

class Todo {
    var id: Int = 0
    var title: String = ""
    var details: String? = null
    var finished: Boolean = false
}

class MultiInsert{
    val insertData:List<Todo> = mutableListOf()
}

class MultiUpdate{
    val updateData:List<Todo> = mutableListOf()
}
class MultiDelete{
    //val deleteData:List<Int> = mutableListOf()
    val deleteData:List<Todo> = mutableListOf()
}





