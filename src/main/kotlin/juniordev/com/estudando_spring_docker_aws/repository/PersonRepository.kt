package juniordev.com.estudando_spring_docker_aws.repository

import juniordev.com.estudando_spring_docker_aws.entity.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository:JpaRepository<Person,  Long?>{

    fun findByContrato(contrato:String): Person?
}