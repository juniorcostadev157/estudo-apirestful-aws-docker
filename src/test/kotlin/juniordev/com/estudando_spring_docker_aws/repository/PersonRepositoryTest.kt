package juniordev.com.estudando_spring_docker_aws.repository
import jakarta.persistence.EntityManager
import juniordev.com.estudando_spring_docker_aws.dto.PersonDTO
import juniordev.com.estudando_spring_docker_aws.entity.Person
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
class PersonRepositoryTest {
    @Autowired
    lateinit var entityManager: EntityManager

    @Autowired
    lateinit var personRepository: PersonRepository

    @Test
    @DisplayName("Should find Person by contrato successfully")
    fun findByContratoSucess(){
        val contrato = "123456"
        val personDTO = PersonDTO(
            firstName = "John",
            lastName = "Doe",
            email = "john@example.com",
            password = "password",
            zipcode = "12345",
            street = "Main Street",
            number = "10",
            telefone = "123456789",
            contrato = contrato,
            idade = "30"
        )
        createUser(personDTO)
       val result =  personRepository.findByContrato(contrato)

        assertThat(result).isNotNull
        assertThat(result?.contrato).isEqualTo(contrato)
        assertThat(result?.firstName).isEqualTo("John")
    }
    @Test
    @DisplayName("Quando falhar a busca ")
    fun findByContratoFail(){
        val contrato = "123456"

        val result =  personRepository.findByContrato(contrato)

        assertThat(result).isNull()
    }


    private fun createUser(personDTO: PersonDTO):Person{
        val newPerson = personDTO.toEntity()
        entityManager.persist(newPerson)
        entityManager.flush()
        return newPerson

    }
}