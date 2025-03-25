package juniordev.com.estudando_spring_docker_aws.repository

import jakarta.persistence.EntityManager
import juniordev.com.estudando_spring_docker_aws.dto.OrdemServicoDTO
import juniordev.com.estudando_spring_docker_aws.entity.OrdemServico
import juniordev.com.estudando_spring_docker_aws.entity.Person
import org.junit.jupiter.api.DisplayName
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired

@DataJpaTest
@ActiveProfiles("test")
class OrdemServicoRepositoryTest {

    @Autowired
    lateinit var entityManager: EntityManager

    @Autowired
    lateinit var ordemServicoRepository: OrdemServicoRepository

    @Test
    @DisplayName("Deve encontrar OS pelo numero e dataAbertura com sucesso")
    fun findByNumeroOSAndDataAbertura_Sucess() {
        val numeroOS = "123456"
        val dataAbertura = "2024-03-07"
        val dto = OrdemServicoDTO(
            numeroOS = numeroOS,
            dataAbertura = dataAbertura,
            node =  "Node1",
            tipo = "Instalação",
            contrato = "CTR123"
        )
        createOrdemServico(dto)
        val result = ordemServicoRepository.findByNumeroOSAndDataAbertura(numeroOS, dataAbertura)
        assertThat(result).isNotEmpty
        assertThat(result.first()?.numeroOS).isEqualTo(numeroOS)
        assertThat(result.first()?.dataAbertura).isEqualTo(dataAbertura)
    }


    @Test
    @DisplayName("Não deve encontrar OS com numero e dataAbertura inexistentes")
    fun findByNumeroOSAndDataAbertura_NotFound(){
        val result = ordemServicoRepository.findByNumeroOSAndDataAbertura("123456", "2024-03-07")
        assertThat(result).isEmpty()
    }

    @Test
    @DisplayName("Deve listar OS por contrato e dataAbertura com sucesso")
     fun findAllByPersonContratoAndDataAbertura_Success(){
        val contrato = "1234567"
        val dataAbertura = "2024-03-07"

        val dt01 = OrdemServicoDTO("001", dataAbertura, "Node01", "Instalacao", contrato)
        val dt02 = OrdemServicoDTO("002", dataAbertura, "Node02", "Instalacao", contrato)
        createOrdemServico(dt01)
        createOrdemServico(dt02)

        val result = ordemServicoRepository.findAllByPersonContratoAndDataAbertura(contrato, dataAbertura)

        assertThat(result).hasSize(2)
        assertThat(result.map { it.numeroOS }).containsExactlyInAnyOrder("001", "002")
    }

    @Test
    @DisplayName("Deve retornar lista vazia ao buscar contrato e dataAbertura sem resultado")
    fun findAllByPersonContratoAndDataAbertura_Empty(){
        val result = ordemServicoRepository.findAllByPersonContratoAndDataAbertura("CTR123", "2024-03-07")
        assertThat(result).isEmpty()
    }

    private fun createOrdemServico(dto: OrdemServicoDTO):OrdemServico{
        val person = createPerson(dto.contrato)
        val ordemServico = dto.toEntity().copy(person = person)

        entityManager.persist(ordemServico)
        entityManager.flush()
        return ordemServico
    }



    private fun createPerson(contrato:String):Person{
        var person = entityManager.find(Person::class.java, contrato)
        if (person == null) {
            person = Person(
                contrato = contrato,
                firstName = "Teste",
                lastName = "User",
                email = "teste@example.com",
                password = "123456",
                telefone = "123456789",
                idade = "30"
            )
            entityManager.persist(person)
            entityManager.flush()
        }
        return person
    }

}