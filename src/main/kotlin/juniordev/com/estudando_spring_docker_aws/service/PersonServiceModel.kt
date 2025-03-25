package juniordev.com.estudando_spring_docker_aws.service

import juniordev.com.estudando_spring_docker_aws.entity.Person
import juniordev.com.estudando_spring_docker_aws.exception.BusinessException
import juniordev.com.estudando_spring_docker_aws.repository.PersonRepository

import org.springframework.stereotype.Service

@Service
class PersonServiceModel(private val repository: PersonRepository):PersonService{


    override fun findAllPerson():List<Person>{

        return repository.findAll()
    }

    override fun findByContrato(contrato:String):Person{
        return repository.findByContrato(contrato) ?: throw BusinessException("Contrato nao encontrado")
    }



   // deixando aqui so para nivel didatico , o jpa ja faz o update pelo save
    override fun update(person: Person):Person{
        val entity = findByContrato(person.contrato)
       if (entity == null){
           throw BusinessException("Contrato não encontrado")
       }

               entity.firstName = person.firstName
               entity.lastName = person.lastName
               entity.adress.zipcode = person.adress.zipcode
               entity.adress.number = person.adress.number
               entity.adress.street = person.adress.street
               entity.telefone = person.telefone
               println("Usuario do id: ${person.contrato} atualizado com sucesso")
               return repository.save(entity)

    }

    fun updateContrato(person: Person): Person{
        return repository.save(person)

    }

    override fun create(person: Person): Person {
        if (person.firstName.isNullOrBlank())throw BusinessException("o first name nao pode ser nulo")
        val verifcarContrato = repository.findByContrato(person.contrato)
        if (verifcarContrato != null){
            throw BusinessException("Contrato ja cadastrado no sistema")
        }
        println("usuario criado com sucesso")
        return repository.save(person)
    }

    override fun delete(id:String){
        val person :Person = findByContrato(id)
        repository.delete(person)
    }
}