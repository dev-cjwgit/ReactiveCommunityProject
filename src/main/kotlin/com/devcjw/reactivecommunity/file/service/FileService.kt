package com.devcjw.reactivecommunity.file.service

import com.devcjw.reactivecommunity.common.model.RestResponseVO
import com.devcjw.reactivecommunity.file.model.domain.FileRepListVO
import org.springframework.core.io.Resource
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.http.codec.multipart.FilePart
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface FileService {
    fun upload(fileParts: Flux<FilePart>): Flux<RestResponseVO<FileRepListVO>>

    fun download(fileUid: String): Flux<DataBuffer>
}