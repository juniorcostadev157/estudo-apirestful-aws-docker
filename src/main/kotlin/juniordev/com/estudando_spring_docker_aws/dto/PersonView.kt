package juniordev.com.estudando_spring_docker_aws.dto

import juniordev.com.estudando_spring_docker_aws.controller.PersonController
import juniordev.com.estudando_spring_docker_aws.entity.Person
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn


//@JsonPropertyOrder(aqui dentro define as ordem do json caso queira)
data class PersonView (
    //@fiel: JsonProperty(aqui dentro define como o campo deve ser exibido ex: first_name)
    val firstName:String,
    val lastName:String,
    val email:String,
    val zipcode:String,
    val number:String,
    val street:String,
    val telefone:String,
    val contrato:String
): RepresentationModel<PersonView>(){
    constructor(person: Person) : this(
        firstName = person.firstName,
        lastName = person.lastName,
        email = person.email,
        zipcode = person.adress.zipcode,
        number = person.adress.number,
        street = person.adress.street,
        telefone = person.telefone,
        contrato = person.contrato

    ){
        add(linkTo(methodOn(PersonController::class.java).findByContrato(person.contrato)).withSelfRel().withType("GET"))
        add(linkTo(methodOn(PersonController::class.java).findAllPerson()).withRel("all").withType("GET"))
        add(linkTo(methodOn(PersonController::class.java).deleteuser(person.contrato)).withRel("delete").withType("DELETE"))
        add(linkTo(methodOn(PersonController::class.java).updateuser(person.contrato, PersonUpdateDTO("","", "", "", "", ""))).withRel("update").withType("PATCH"))
        add(linkTo(methodOn(PersonController::class.java)
            .createuser(PersonDTO("", "", "", "","", "", "", "", "", "")))
            .withRel("create").withType("POST"))



    }

}