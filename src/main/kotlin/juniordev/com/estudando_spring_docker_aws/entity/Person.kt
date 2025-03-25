package juniordev.com.estudando_spring_docker_aws.entity

import jakarta.persistence.*

@Entity
@Table (name = "person")
data class Person(

    @Id
    @Column(unique = true, nullable = false)val contrato:String = "",

    @Column(name = "first_name", nullable = false, length = 80)
    var firstName:String = "" ,

    @Column(name = "last_name", nullable = false, length = 80)
    var lastName:String = "" ,

    @Column(nullable = false, length = 80)var email:String = "",

    @Column(nullable = false, length = 80) var password:String="",

    @Embedded var adress:Adress = Adress(),

    @Column(nullable = false, length = 12)
    var telefone:String = "",

    @Column(nullable = false)
    var idade: String = ""
)
