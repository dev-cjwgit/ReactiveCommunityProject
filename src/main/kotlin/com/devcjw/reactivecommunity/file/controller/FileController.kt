package com.devcjw.reactivecommunity.file.controller

import com.devcjw.reactivecommunity.common.model.RestResponseVO
import com.devcjw.reactivecommunity.file.model.domain.FileRepListVO
import com.devcjw.reactivecommunity.file.service.FileService
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RequestMapping("/file")
@RestController
class FileController(
        private val fileService: FileService
) {
    @GetMapping("/{file_uid}")
    fun download(@PathVariable("file_uid") fileUid: String): Mono<ResponseEntity<Resource>> {
        return fileService.download(fileUid)
                .map { resource ->
                    ResponseEntity
                            .ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${resource.filename}\"")
                            .body(resource)
                }
    }

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun upload(
            @RequestPart("files") fileParts: Flux<FilePart>
    ): Flux<RestResponseVO<FileRepListVO>> {
        return fileService.upload(fileParts)
    }
}