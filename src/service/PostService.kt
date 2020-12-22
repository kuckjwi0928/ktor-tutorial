package com.kuckjwi.ktor.service

import com.kuckjwi.ktor.domain.dto.PostDto
import com.kuckjwi.ktor.domain.entity.PostEntity
import org.jetbrains.exposed.sql.transactions.transaction

class PostService {
    fun addPost(post: PostDto): PostDto = transaction {
        PostEntity.new {
            this.title = post.title
            this.content = post.content
        }.toDto()
    }
}
