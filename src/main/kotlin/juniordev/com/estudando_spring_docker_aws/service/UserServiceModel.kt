package juniordev.com.estudando_spring_docker_aws.service

import juniordev.com.estudando_spring_docker_aws.entity.User
import juniordev.com.estudando_spring_docker_aws.exception.BusinessException
import juniordev.com.estudando_spring_docker_aws.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserServiceModel(private  val userRepository: UserRepository):UserService, UserDetailsService{


     override fun loadUserByUsername(username: String?): UserDetails {
       val user = userRepository.findByUserName(username)
         return user ?: throw BusinessException ("Nenhum Usuario encontrado")
    }

    override fun findByUserName(userName: String): User {
        TODO("Not yet implemented")
    }

    override fun create(user: User): User {
        TODO("Not yet implemented")
    }

    override fun findByAllUser(): List<User> {
        TODO("Not yet implemented")
    }


}