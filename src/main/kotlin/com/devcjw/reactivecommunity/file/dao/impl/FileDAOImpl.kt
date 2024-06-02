package com.devcjw.reactivecommunity.file.dao.impl

import com.devcjw.reactivecommunity.file.dao.FileDAO
import com.devcjw.reactivecommunity.file.model.entity.FileInsertDTO
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class FileDAOImpl(
    val databaseClient: DatabaseClient
) : FileDAO {
    override fun insert(fileInsertDTO: FileInsertDTO): Mono<Void> {
        return databaseClient.sql(
            """
                INSERT INTO RC_FILE (`UID`, `PATH`, `NAME`, `SIZE`, `MD5`)
                VALUES (:uid,:path,:name,:size,:md5)
            """.trimIndent()
        )
            .bind("uid", fileInsertDTO.uid)
            .bind("path", fileInsertDTO.path)
            .bind("name", fileInsertDTO.name)
            .bind("size", fileInsertDTO.size)
            .bind("md5", fileInsertDTO.md5)
            .then()
    }
}