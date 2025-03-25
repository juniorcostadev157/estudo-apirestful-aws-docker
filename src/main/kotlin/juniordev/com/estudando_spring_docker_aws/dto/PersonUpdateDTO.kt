package juniordev.com.estudando_spring_docker_aws.dto

import jakarta.validation.constraints.NotEmpty
import juniordev.com.estudando_spring_docker_aws.entity.Person


data class PersonUpdateDTO(

    @field:NotEmpty(message = "Invalid Input")val firstName:String,
    @field:NotEmpty(message = "Invalid Input")val lastName:String,
    @field:NotEmpty(message = "Invalid Input")val telefone:String,
    @field:NotEmpty(message = "Invalid Input")val zipcode:String,
    @field:NotEmpty(message = "Invalid Input")val number:String,
    @field:NotEmpty(message = "Invalid Input")val street:String,
    ){
    fun toEntity(person:Person):Person{
        person.firstName =this.firstName
        person.lastName = this.lastName
        person.telefone = this.telefone
        person.adress.zipcode = this.zipcode
        person.adress.number = this.number
        person.adress.street = this.street
        return person

    }
}
