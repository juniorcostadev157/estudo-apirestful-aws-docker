package juniordev.com.estudando_spring_docker_aws.entity

import jakarta.persistence.*
import juniordev.com.estudando_spring_docker_aws.enumeration.Status_OS

@Entity
@Table(name = "ordem_servico")
data class OrdemServico(
    @Id
    @Column(name = "numeroos", nullable = false, unique = true)
    val numeroOS: String = "",

    @Column(nullable = false)
    val tipo: String = "",

    @Column(nullable = false)
    val node: String = "",

    @Embedded
    val endereco: Adress = Adress(),

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    val status: Status_OS = Status_OS.PENDENTE,

    @ManyToOne
    @JoinColumn(name = "contrato", referencedColumnName = "contrato", nullable = false)
    val person: Person? = null,

    @Column(name = "codigo_baixa")
    val codigoBaixa: String = "",

    @Column(name = "data_abertura")
    val dataAbertura: String = "",

    @Column(name = "data_baixa")
    val dataBaixa: String = ""
)

