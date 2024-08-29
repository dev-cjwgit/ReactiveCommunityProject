package com.devcjw.reactivecommunity.file.service

import com.devcjw.reactivecommunity.common.model.RestResponseVO
import com.devcjw.reactivecommunity.file.model.domain.RepFileListVO
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.http.codec.multipart.FilePart
import reactor.core.publisher.Flux

interface FileRestService {
    fun upload(fileParts: Flux<FilePart>): Flux<RestResponseVO<RepFileListVO>>

    fun download(fileUid: String): Flux<DataBuffer>
}