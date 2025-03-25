package juniordev.com.estudando_spring_docker_aws.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority

@Entity
@Table( name = "permissions")
class Permission:GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "description", length = 255)
    var description: String? = null


    override fun getAuthority(): String {
        return  description!!
    }


}