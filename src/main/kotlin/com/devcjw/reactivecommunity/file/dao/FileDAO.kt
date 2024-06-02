package com.devcjw.reactivecommunity.file.dao

import com.devcjw.reactivecommunity.file.model.entity.FileInsertDTO
import reactor.core.publisher.Mono

interface FileDAO {
    fun insert(fileInsertDTO: FileInsertDTO): Mono<Void>
}