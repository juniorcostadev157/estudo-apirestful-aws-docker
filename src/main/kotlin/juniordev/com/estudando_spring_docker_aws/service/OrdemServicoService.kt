package juniordev.com.estudando_spring_docker_aws.service

import juniordev.com.estudando_spring_docker_aws.entity.OrdemServico



interface OrdemServicoService {

    fun create(ordemServico: OrdemServico):OrdemServico

    fun findByOS(numeroOS: String, dataAbertura:String):List<OrdemServico?>

    fun findAllByOS(contrato: String, dataAbertura: String): List<OrdemServico>
}