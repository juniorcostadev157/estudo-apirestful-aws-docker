package juniordev.com.estudando_spring_docker_aws.repository

import juniordev.com.estudando_spring_docker_aws.entity.OrdemServico
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrdemServicoRepository:JpaRepository<OrdemServico, Long> {

    fun findByNumeroOSAndDataAbertura(numeroOS: String, dataAbertura:String): List<OrdemServico?>



    fun findAllByPersonContratoAndDataAbertura(contrato: String, dataAbertura: String): List<OrdemServico>

}