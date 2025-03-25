package juniordev.com.estudando_spring_docker_aws.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable


@Embeddable
data class Adress(
  @Column(nullable = false)  var street:String = "",
  @Column(nullable = false)  var zipcode:String = "",
  @Column(nullable = false)  var number:String = ""
)
