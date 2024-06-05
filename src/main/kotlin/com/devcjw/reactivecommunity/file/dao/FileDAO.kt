package com.devcjw.reactivecommunity.file.dao

import com.devcjw.reactivecommunity.file.model.entity.InFileInsertEntity
import reactor.core.publisher.Mono

interface FileDAO {
    fun insert(inFileInsertEntity: InFileInsertEntity): Mono<Void>
}