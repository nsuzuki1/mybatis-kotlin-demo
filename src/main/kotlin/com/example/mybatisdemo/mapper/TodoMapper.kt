package com.example.mybatisdemo.mapper

import com.example.mybatisdemo.domain.Todo
import org.apache.ibatis.annotations.*


@Mapper
interface TodoMapper {

    @Insert("""
        INSERT INTO todo
            (id, title, details, finished)
        VALUES
            (#{id},#{title}, #{details}, #{finished})
    """)
    fun insert(todo: Todo)

    @Select("""
        SELECT
            id, title, details, finished
        FROM
            todo
        WHERE
            id = #{id}
    """)
    fun select(id: Int): Todo

    @Update("""
        UPDATE todo
        SET
            title = #{title}, details = #{details}, finished = #{finished}
        WHERE
            id = #{id}
    """)
    fun update(todo: Todo)

    @Delete("""
        DELETE FROM todo
        WHERE 
            id = #{id}
    """)
    fun delete(id:Int):Todo

    //全件取得
    @Select("""
        SELECT
            *
        FROM
            todo
    """)
    fun selectAll():List<Todo>

    @Delete("""
        DELETE FROM todo
        WHERE 
            id = #{id}
    """)
    fun multiDelete(todo:Todo)

    @Delete("""
        DELETE FROM todo
        WHERE 
            title = #{title}
    """)
    fun multiDeleteTitle(todo:Todo)
    }
