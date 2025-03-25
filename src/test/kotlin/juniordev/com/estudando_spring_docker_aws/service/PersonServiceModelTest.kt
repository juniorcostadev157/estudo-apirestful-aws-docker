package juniordev.com.estudando_spring_docker_aws.service

import juniordev.com.estudando_spring_docker_aws.entity.Person
import juniordev.com.estudando_spring_docker_aws.exception.BusinessException
import juniordev.com.estudando_spring_docker_aws.repository.PersonRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
 class PersonServiceModelTest {

  @InjectMocks
  lateinit var service: PersonServiceModel

  @Mock
  lateinit var repository: PersonRepository

@BeforeEach
 fun setUp() {}

 fun autorization(){

 }

@Test
@DisplayName("Teste, caso a busca seja um sucesso ")
 fun findByContrato_case1() {
  // given
  val contrato  = "123456"
  val fakePerson = Person(
   contrato = contrato,
   firstName = "Junior",
   lastName = "Costa",
   email = "junior@gmail.com",
   password = "senha",
   telefone = "123456789",
   idade = "30"
  )
 Mockito.`when`(repository.findByContrato(contrato)).thenReturn(fakePerson)
 //when
 val result = service.findByContrato(contrato)

 //then
 assertThat(result).isNotNull
 assertThat(result.contrato).isEqualTo(contrato)
 assertThat(result.firstName).isEqualTo("Junior")

 }


 @Test
 @DisplayName("Deve lançar exceção ao não encontrar o contrato")
 fun findByContrato_case2() {
 //given
  val contrato = "99999"
  Mockito.`when`(repository.findByContrato(contrato)).thenReturn(null)
  //when & then
  val throwExcepation = assertThrows<BusinessException> { service.findByContrato(contrato) }
  assertThat(throwExcepation.message).isEqualTo("Contrato nao encontrado")
 }




@Test
@DisplayName("Caso a criação seja um sucesso")
 fun create_case1() {
 val contrato  = "123456"
 val fakePerson = Person(
  contrato = contrato,
  firstName = "Junior",
  lastName = "Costa",
  email = "junior@gmail.com",
  password = "senha",
  telefone = "123456789",
  idade = "30"
 )
  Mockito.`when`(repository.findByContrato(contrato)).thenReturn(null)
  Mockito.`when`(repository.save(fakePerson)).thenReturn(fakePerson)

  val result = service.create(fakePerson)
  assertThat(result).isNotNull
  assertThat(result.contrato).isEqualTo("123456")
 }

 @Test
 @DisplayName("Se o contrato ja existir,  lançar uma excessao")
 fun create_case2() {

  val contrato  = "123456"
  val fakePerson = Person(
   contrato = contrato,
   firstName = "Junior",
   lastName = "Costa",
   email = "junior@gmail.com",
   password = "senha",
   telefone = "123456789",
   idade = "30"
  )
  Mockito.`when`(repository.findByContrato(contrato)).thenReturn(fakePerson)

  assertThrows<BusinessException> {service.create(fakePerson) }
 }


}