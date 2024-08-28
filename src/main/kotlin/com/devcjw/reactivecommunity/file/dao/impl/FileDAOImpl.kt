package com.devcjw.reactivecommunity.file.dao.impl

import com.devcjw.reactivecommunity.file.dao.FileDAO
import com.devcjw.reactivecommunity.file.model.entity.InFileInsertEntity
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class FileDAOImpl(
        val databaseClient: DatabaseClient
) : FileDAO {
    override fun insert(inFileInsertEntity: InFileInsertEntity): Mono<Void> {
        return databaseClient.sql(
                """
                INSERT INTO rc_file (`uid`, `path`, `name`, `size`, `md5`)
                VALUES (:uid, :path, :name, :size, :md5)
            """.trimIndent()
        )
                .bind("uid", inFileInsertEntity.uid)
                .bind("path", inFileInsertEntity.path)
                .bind("name", inFileInsertEntity.name)
                .bind("size", inFileInsertEntity.size)
                .bind("md5", inFileInsertEntity.md5)
                .then()
    }
}