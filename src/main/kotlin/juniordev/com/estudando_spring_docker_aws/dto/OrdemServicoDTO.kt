package juniordev.com.estudando_spring_docker_aws.dto

import jakarta.validation.constraints.NotEmpty
import juniordev.com.estudando_spring_docker_aws.entity.OrdemServico
import juniordev.com.estudando_spring_docker_aws.entity.Person

data class OrdemServicoDTO(
    @field: NotEmpty(message = "Invalid Input")val numeroOS:String,
    @field: NotEmpty(message = "Invalid Input")val dataAbertura:String,
    val node:String,
    @field: NotEmpty(message = "Invalid Input")val tipo:String,
    @field: NotEmpty(message = "Invalid Input")val contrato:String
){
    fun toEntity():OrdemServico = OrdemServico(

        numeroOS = this.numeroOS,
        dataAbertura = this.dataAbertura,
        node = this.node,
        tipo = this.tipo,
        person = Person(contrato = this.contrato),


    )
}
