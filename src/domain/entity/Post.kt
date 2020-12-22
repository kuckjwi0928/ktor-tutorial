package com.kuckjwi.ktor.domain.entity

import com.kuckjwi.ktor.domain.dto.PostDto
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

object Post : IntIdTable("post") {
    val title = varchar("title", length = 200)
    val content = varchar("content", length = 200)
}

class PostEntity(id: EntityID<Int>) : IntEntity(id), DtoConvertible<PostDto> {
    companion object : IntEntityClass<PostEntity>(Post)

    var title by Post.title
    var content by Post.content

    override fun toDto(): PostDto = PostDto(title, content)
}
