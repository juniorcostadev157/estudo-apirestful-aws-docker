package juniordev.com.estudando_spring_docker_aws.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import juniordev.com.estudando_spring_docker_aws.entity.Adress
import juniordev.com.estudando_spring_docker_aws.entity.Person

data class PersonDTO(

    @field:NotEmpty(message = "Invalid Input") val firstName:String,
    @field:NotEmpty(message = "Invalid Input") val password:String,
    @field:NotEmpty(message = "Invalid Input") val lastName:String,
    @field:Email(message = "Invalid Email")
    @field:NotEmpty(message = "Invalid Input") val email:String,
    @field:NotEmpty(message = "Invalid Input") val zipcode:String,
    @field:NotEmpty(message = "Invalid Input") val street:String,
    @field:NotEmpty(message = "Invalid Input") val number:String,
    @field:NotEmpty(message = "Invalid Input") val telefone:String,
    @field:NotEmpty(message = "Invalid Input") val contrato:String,
    @field:NotEmpty(message = "Invalid Input") val idade:String


){
    fun toEntity():Person = Person(
        firstName = this.firstName,
        lastName = this.lastName,
        password = this.password,
        email = this.email,
        telefone = this.telefone,
        contrato = this.contrato,
        idade = this.idade,
        adress = Adress(
            number = this.number,
            street = this.street,
            zipcode = this.zipcode

        )

    )
}
