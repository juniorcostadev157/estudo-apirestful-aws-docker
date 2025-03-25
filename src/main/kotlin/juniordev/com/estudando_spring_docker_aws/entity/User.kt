package juniordev.com.estudando_spring_docker_aws.entity

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
class User:UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "user_name", unique = true)
    var userName: String? = null

    @Column(name = "full_name")
    var fullName: String? = null

    @Column(name = "password")
    private var password: String? = null


    @Column(name = "account_non_expired")
    var accountNonExpired: Boolean? = null

    @Column(name = "account_non_locked")
    var accountNonLocked: Boolean? = null

    @Column(name = "credentials_non_expired")
    var credentialsNonExpired: Boolean? = null

    @Column(name = "enabled")
    var enabled: Boolean? = null

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_permission",
        joinColumns = [JoinColumn(name = "id_user")],
        inverseJoinColumns = [JoinColumn(name = "id_permission")]

    )
    var permissions: List<Permission>? = null
    val role: List<String?>
        get(){
            /*val roles: MutableList<String?> = ArrayList()
            for (permission in permissions!!){
                roles.add(permission.description)
            }
            return  roles*/
            //duas maneiras de interar sobre a lista
            return permissions?.map { it.description } ?: emptyList()
        }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return permissions!!
    }

    override fun getPassword(): String {
        return password!!
    }

    override fun getUsername(): String {
        return  userName!!
    }

    override fun isAccountNonExpired(): Boolean = accountNonExpired!!
    override fun isAccountNonLocked(): Boolean = accountNonLocked!!
    override fun isCredentialsNonExpired(): Boolean = credentialsNonExpired!!
    override fun isEnabled(): Boolean = enabled!!

}