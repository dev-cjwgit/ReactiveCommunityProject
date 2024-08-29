package com.devcjw.reactivecommunity.file.controller

import com.devcjw.reactivecommunity.common.model.RestResponseVO
import com.devcjw.reactivecommunity.file.model.domain.RepFileListVO
import com.devcjw.reactivecommunity.file.service.FileRestService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

@RequestMapping("/file")
@RestController
@Tag(name = "파일 컨트롤러", description = "파일의 업로드/다운로드를 담당하는 컨트롤러")
class FileRestController(
    private val fileRestService: FileRestService
) {
    @GetMapping("/preview/{file_uid}")
    fun imagePreview(@PathVariable("file_uid") fileUid: String) {
        // TODO: 구현 필요
    }

    @GetMapping("/{file_uid}")
    @Operation(summary = "파일 다운로드", description = "특정 파일을 다운로드하는 API")
    fun download(@PathVariable("file_uid") fileUid: String): ResponseEntity<Flux<DataBuffer>> {
        val dataBuffer = fileRestService.download(fileUid)
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename\"$fileUid\"")
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(dataBuffer)
    }

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @Operation(summary = "파일 업로드", description = "특정 파일을 업로드하는 API")
    fun upload(
        @RequestPart("files") fileParts: Flux<FilePart>
    ): Flux<RestResponseVO<RepFileListVO>> {
        return fileRestService.upload(fileParts)
    }
}