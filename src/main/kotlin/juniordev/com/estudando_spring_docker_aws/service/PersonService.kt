package juniordev.com.estudando_spring_docker_aws.service

import juniordev.com.estudando_spring_docker_aws.entity.Person

interface PersonService {

    fun create(person:Person):Person

    fun delete(id:String)

    fun update(person: Person):Person

    fun findByContrato(contrato:String):Person

    fun findAllPerson():List<Person>
}