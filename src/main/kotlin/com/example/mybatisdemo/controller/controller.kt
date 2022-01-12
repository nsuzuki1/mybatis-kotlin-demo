package com.example.mybatisdemo.controller

import com.example.mybatisdemo.domain.MultiDelete
import com.example.mybatisdemo.domain.MultiInsert
import com.example.mybatisdemo.domain.MultiUpdate
import com.example.mybatisdemo.domain.Todo
import com.example.mybatisdemo.mapper.TodoMapper
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api")
class WebApiController(private val mapper: TodoMapper) {
    @RequestMapping("hello")
    private fun hello(): String {
        return "SpringBoot!"
    }

    @RequestMapping("insert")
    private fun insert(): List<Todo> {
        val todo = Todo()
        todo.id = 1
        todo.details = "aaa"
        todo.title = "bbb"
        todo.finished = true
        val todo2 = Todo()
        todo2.id = 2
        todo2.details = "ccc"
        todo2.title = "ddd"
        todo2.finished = true
        mapper.insert(todo)
        mapper.insert(todo2)
        val list = mutableListOf<Todo>()
        val result = mapper.select(1)
        val result2 = mapper.select(2)
        list.add(result)
        list.add(result2)
        return list
    }

    //登録
    @RequestMapping(value = ["/test"], method = [RequestMethod.POST])
    private fun testRequestBody(@RequestBody body: Todo) {
        val record = Todo()
        record.id = body.id
        record.details = body.details
        record.title = body.title
        record.finished = body.finished
        mapper.insert(record)
    }

    //参照
    @RequestMapping("/test/{param}")
    private fun testPathVariable(@PathVariable("param") param: Int): Todo {
        val id = param
        val result = mapper.select(id)
        return result
    }

    //更新
    @RequestMapping(value = ["/resource"], method = [RequestMethod.PUT])
    private fun update(@RequestBody body: Todo) {
        val record = Todo()
        record.id = body.id
        record.details = body.details
        record.title = body.title
        record.finished = body.finished
        mapper.update(record)
    }

    //削除
    @RequestMapping(value = ["/delete/{param}"], method = [RequestMethod.DELETE])
    private fun delete(@PathVariable("param") param: Int) {
        mapper.delete(param)

    }

    //登録されたデータをすべて取得
    @RequestMapping("all")
    private fun all(): List<Todo> {
        return mapper.selectAll()
    }

    //複数登録
    @RequestMapping(value = ["/multiInsert"], method = [RequestMethod.POST])
    fun multiInsert(@RequestBody body: MultiInsert) {
        body.insertData.forEach { it ->
            if (it.id != 100) {
                mapper.insert(it)
            }else {
            }
        }
    }


    //    複数更新
    @RequestMapping(value = ["/multiUpdate"], method = [RequestMethod.PUT])
    private fun multiUpdate(@RequestBody body: MultiUpdate) {
        body.updateData.forEach { it ->
            mapper.update(it)
        }
    }

    //    複数削除
    @RequestMapping(value = ["/multiDelete"], method = [RequestMethod.DELETE])
    private fun multiDelete(@RequestBody body: MultiDelete) {
        body.deleteData.forEach { it ->
            mapper.multiDelete(it)
        }

    }

    @RequestMapping(value = ["/multiDeleteTitle"], method = [RequestMethod.DELETE])
    private fun multiDeleteTitle(@RequestBody body: MultiDelete) {
        body.deleteData.forEach { it ->
            mapper.multiDeleteTitle(it)
        }
    }
}
