package juniordev.com.estudando_spring_docker_aws.controller

import jakarta.validation.Valid
import juniordev.com.estudando_spring_docker_aws.dto.OrdemServicoDTO
import juniordev.com.estudando_spring_docker_aws.service.OrdemServicoModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("ordemservico")
class OrdemServicoController(private val service: OrdemServicoModel) {
    @PostMapping
    fun saveOrdemServico(@RequestBody @Valid ordemServicoDTO: OrdemServicoDTO): ResponseEntity<String>{
        service.create(ordemServicoDTO.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created")
    }
}