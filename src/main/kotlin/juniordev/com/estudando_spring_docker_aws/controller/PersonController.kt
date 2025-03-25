package juniordev.com.estudando_spring_docker_aws.controller

import jakarta.validation.Valid
import juniordev.com.estudando_spring_docker_aws.dto.MessageResponse
import juniordev.com.estudando_spring_docker_aws.dto.PersonDTO
import juniordev.com.estudando_spring_docker_aws.dto.PersonUpdateDTO
import juniordev.com.estudando_spring_docker_aws.dto.PersonView
import juniordev.com.estudando_spring_docker_aws.entity.Person
import juniordev.com.estudando_spring_docker_aws.service.PersonServiceModel
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pessoas")
class PersonController(private val personService: PersonServiceModel) {
    //ESSA NOTAÇÃO É INTERESSANTE POR CONTA DO SWAGGER , ASSIM O MESMO CONSEGUIRAR DOCUMENTAR AS INFORMACOES
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    fun findAllPerson(): ResponseEntity<List<PersonView>> {
        val person:List<Person> = personService.findAllPerson()
        val personView = person.map { PersonView(it) }
        return ResponseEntity.status(HttpStatus.OK).body(personView)
    }

    @GetMapping("/{contrato}")
    fun findByContrato(@PathVariable contrato:String):ResponseEntity<PersonView>{
        val person:Person = personService.findByContrato(contrato)
        return ResponseEntity.status(HttpStatus.OK).body(PersonView(person))
    }

    @PostMapping("/create")
    fun createuser(@RequestBody @Valid personDTO: PersonDTO): ResponseEntity<EntityModel<MessageResponse>>{
        val person = personService.create(personDTO.toEntity())

        val responseBody = MessageResponse(
            message =   "User created sucessfully"
        )
        val resource = EntityModel.of(
            responseBody,
            linkTo(methodOn(PersonController::class.java).findByContrato(person.contrato)).withSelfRel().withType("GET"),
            linkTo(methodOn(PersonController::class.java).findAllPerson()).withRel("all").withType("GET"),
            linkTo(methodOn(PersonController::class.java).deleteuser(person.contrato)).withRel("delete").withType("DELETE"),
            linkTo(methodOn(PersonController::class.java).updateuser(person.contrato, PersonUpdateDTO("", "", "", "", "", ""))).withRel("update").withType("PATCH")
        )


        return ResponseEntity.status(HttpStatus.CREATED).body(resource)
    }

    @DeleteMapping("/{id}")
       fun deleteuser(@PathVariable id:String):ResponseEntity<Void>{
            this.personService.delete(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @PatchMapping
    fun updateuser(@RequestParam(value = "contrato")id:String, @RequestBody @Valid personUpdateDTO: PersonUpdateDTO)
    :ResponseEntity<PersonView>{
        val person = personService.findByContrato(id)
        val personUpdate = personUpdateDTO.toEntity(person)
        val personSave = personService.updateContrato(personUpdate)
        return ResponseEntity.status(HttpStatus.OK).body(PersonView(personSave))
    }



}