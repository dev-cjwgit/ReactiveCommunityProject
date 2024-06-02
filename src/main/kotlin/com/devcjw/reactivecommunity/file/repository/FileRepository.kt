package com.devcjw.reactivecommunity.file.repository

import com.devcjw.reactivecommunity.file.model.entity.FileEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface FileRepository : ReactiveCrudRepository<FileEntity, String> {
    fun findByUid(uid: String): Mono<FileEntity>
    fun findByMd5(md5: String): Mono<FileEntity>

}