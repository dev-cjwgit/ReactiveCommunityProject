package com.devcjw.reactivecommunity.file.service.impl

import com.devcjw.reactivecommunity.common.model.RestResponseVO
import com.devcjw.reactivecommunity.file.dao.FileDAO
import com.devcjw.reactivecommunity.file.model.domain.FileRepListVO
import com.devcjw.reactivecommunity.file.model.entity.FileEntity
import com.devcjw.reactivecommunity.file.model.entity.FileInsertDTO
import com.devcjw.reactivecommunity.file.repository.FileRepository
import com.devcjw.reactivecommunity.file.service.FileService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.core.io.Resource
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.io.File
import java.security.MessageDigest
import java.time.LocalDateTime.now
import java.util.*

@Service
class FileServiceImpl(
        private val fileRepository: FileRepository,
        private val fileDAO: FileDAO,
) : FileService {
    private val logger = KotlinLogging.logger {}

    override fun upload(fileParts: Flux<FilePart>): Flux<RestResponseVO<FileRepListVO>> {
        /**
         * 1. md5 계산
         * 2. DB에서 md5 조회
         * 3. DB에서 md5 값 없으면 uid 및 filename 생성
         * 4. file md5 반환
         */
        return fileParts
                .flatMap { filePart ->
                    val md5 = MessageDigest.getInstance("MD5")
                    filePart.content()
                            .flatMap { dataBuffer ->
                                val bytes = ByteArray(dataBuffer.readableByteCount())
                                dataBuffer.read(bytes)
                                Mono.just(bytes)
                            }.reduce { acc, bytes ->
                                md5.update(bytes)
                                acc + bytes
                            }
                            .flatMap { allBytes ->
                                val md5Hex = md5.digest().joinToString("") { "%02x".format(it) }
                                val fileSize = allBytes.size
                                logger.info { "md5 : $md5Hex, size : $fileSize" }
                                val uid = UUID.randomUUID().toString()
                                val filename = UUID.randomUUID().toString()
                                fileRepository.findByMd5(md5Hex)
                                        .switchIfEmpty(Mono
                                                .defer {
                                                    logger.info { "no find db" }
                                                    val fileEntity = FileEntity(
                                                            uid = uid,
                                                            path = "/tmp",
                                                            size = fileSize,
                                                            name = filename,
                                                            md5 = md5Hex,
                                                            createdAt = now(),
                                                            updatedAt = now()
                                                    )
                                                    filePart.transferTo(File("/tmp/$filename"))
                                                            .thenReturn(fileEntity)
                                                }
                                                .flatMap {
                                                    logger.info { "insert file $it" }
                                                    fileDAO.insert(FileInsertDTO(it.uid, it.path, it.name, it.size, it.md5))
                                                            .then(Mono.just(it))
                                                }
                                        )
                            }

                }
                .map {
                    FileRepListVO(it.md5)
                }
                .map { RestResponseVO(result = true, data = it) }

        /*return fileParts.flatMap { filePart ->
            val md5 = MessageDigest.getInstance("MD5")
            filePart.content().flatMap { dataBuffer ->
                val bytes = ByteArray(dataBuffer.readableByteCount())
                dataBuffer.read(bytes)
                md5.update(bytes)
                filePart.transferTo(File("/tmp/${filePart.filename()}")).thenReturn(bytes)
            }.collectList().flatMap { bytesList ->
                val allBytes = bytesList.flat().toByteArray()
                val fileEntity = FileEntity(
                    uid = UUID.randomUUID().toString(),
                    path = "/tmp",
                    name = filePart.filename(),
                    md5 = md5.digest().joinToString("") { "%02x".format(it) },
                    createdAt = now(),
                    updatedAt = now()
                )
                fileRepository.save(fileEntity)
            }.map { RestResponseVO<Void>(true) }
        }*/
    }

    override fun download(filename: String): Mono<Resource> {
        TODO("Not yet implemented")
    }
}