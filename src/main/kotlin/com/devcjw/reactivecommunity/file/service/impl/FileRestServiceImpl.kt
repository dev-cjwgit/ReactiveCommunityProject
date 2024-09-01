package com.devcjw.reactivecommunity.file.service.impl

import com.devcjw.reactivecommunity.common.exception.config.RcException
import com.devcjw.reactivecommunity.common.exception.model.RcErrorMessage
import com.devcjw.reactivecommunity.common.model.RestResponseVO
import com.devcjw.reactivecommunity.file.dao.FileDAO
import com.devcjw.reactivecommunity.file.model.domain.RepFileListVO
import com.devcjw.reactivecommunity.file.model.entity.FileEntity
import com.devcjw.reactivecommunity.file.model.entity.InFileInsertEntity
import com.devcjw.reactivecommunity.file.repository.FileRepository
import com.devcjw.reactivecommunity.file.service.FileRestService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.core.io.buffer.DataBufferUtils
import org.springframework.core.io.buffer.DefaultDataBufferFactory
import org.springframework.dao.DuplicateKeyException
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.io.File
import java.nio.file.NoSuchFileException
import java.nio.file.Paths
import java.security.MessageDigest
import java.time.LocalDateTime.now
import java.util.*

@Service
class FileRestServiceImpl(
    private val fileRepository: FileRepository,
    private val fileDAO: FileDAO,
) : FileRestService {
    private val logger = KotlinLogging.logger {}

    override fun upload(fileParts: Flux<FilePart>): Flux<RestResponseVO<RepFileListVO>> {
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
                    }
                    .reduce { acc, bytes ->
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
                            .switchIfEmpty(Mono.defer {
                                logger.info { "no find db" }
                                val fileEntity = FileEntity(
                                    uid = uid,
                                    path = "/tmp",
                                    size = fileSize,
                                    name = filename,
                                    md5 = md5Hex
                                )
                                filePart.transferTo(File("/tmp/$filename"))
                                    .thenReturn(fileEntity)
                                    .flatMap {
                                        logger.info { "insert file $it" }
                                        fileDAO.insert(InFileInsertEntity(it.uid, it.path, it.name, it.size, it.md5))
                                            .then(Mono.just(it))
                                    }
                            })
                            .map { fileEntity ->
                                RepFileListVO(fileEntity.uid, filePart.filename())
                            }
                    }
                    .map { fileRepListVO ->
                        RestResponseVO(result = true, data = fileRepListVO)
                    }
                    .onErrorResume { ex ->
                        when (ex) {
                            is NoSuchFileException -> {
                                Mono.just(
                                    RestResponseVO(
                                        result = false,
                                        data = null,
                                        message = RcErrorMessage.NOT_FOUND_PATH_SAVE_FILE_EXCEPTION.message
                                    )
                                )
                            }
                            is DuplicateKeyException -> {
                                Mono.just(
                                    RestResponseVO(
                                        result = false,
                                        data = null,
                                        message = RcErrorMessage.EXIST_FILE_MD5_EXCEPTION.message
                                    )
                                )
                            }
                            else -> {
                                logger.error(ex) { "Exception" }
                                Mono.just(
                                    RestResponseVO(
                                        result = false,
                                        data = null,
                                        message = RcErrorMessage.UNKNOWN_EXCEPTION.message
                                    )
                                )
                            }
                        }
                    }
            }
    }

    override fun download(fileUid: String): Flux<DataBuffer> {
        return fileRepository
            .findByUid(fileUid)
            .switchIfEmpty(Mono.error(RcException(RcErrorMessage.NOT_FOUND_FILE_DB_EXCEPTION)))
            .flatMapMany { fileEntity ->
                val path = Paths.get("/tmp/${fileEntity.name}")
                DataBufferUtils.read(path, DefaultDataBufferFactory(), 4096)
            }
            .onErrorResume { throwable ->
                if (throwable is NoSuchFileException) {
                    Mono.error(RcException(RcErrorMessage.NOT_FOUND_FILE_NAS_EXCEPTION))
                } else {
                    Mono.error(throwable)
                }
            }
    }
}