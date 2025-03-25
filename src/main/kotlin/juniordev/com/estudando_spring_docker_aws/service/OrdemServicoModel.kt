package juniordev.com.estudando_spring_docker_aws.service

import juniordev.com.estudando_spring_docker_aws.entity.OrdemServico
import juniordev.com.estudando_spring_docker_aws.repository.OrdemServicoRepository
import org.springframework.stereotype.Service

@Service
class OrdemServicoModel(private val repository: OrdemServicoRepository):OrdemServicoService {

    override fun create(ordemServico: OrdemServico): OrdemServico {
        return repository.save(ordemServico)
    }

    override fun findByOS(numeroOS: String, dataAbertura: String): List<OrdemServico?> {
      return repository.findByNumeroOSAndDataAbertura(numeroOS, dataAbertura )
    }

    override fun findAllByOS(contrato: String, dataAbertura: String): List<OrdemServico> {
        return repository.findAllByPersonContratoAndDataAbertura(contrato, dataAbertura)
    }
}