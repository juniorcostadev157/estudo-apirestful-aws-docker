package juniordev.com.estudando_spring_docker_aws.service


import juniordev.com.estudando_spring_docker_aws.dto.TokenView
import juniordev.com.estudando_spring_docker_aws.repository.UserRepository
import juniordev.com.estudando_spring_docker_aws.security.jwt.JwtTokenProvider
import org.jboss.logging.Logger
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val repository: UserRepository,
    private val tokenProvider: JwtTokenProvider,
    private val authenticationManager: AuthenticationManager
) {



    private val logger = Logger.getLogger(AuthService::class.java)

    fun signin(userName:String, password:String): TokenView {

       try {
           authenticationManager.authenticate(UsernamePasswordAuthenticationToken(userName, password))
           val user = repository.findByUserName(userName)?:throw UsernameNotFoundException("Username $userName not found!")
           return  tokenProvider.createAcess(userName, user.role)
       }catch (e:AuthenticationException){
           throw BadCredentialsException("Invalid username or password supplied")
       }


    }

    fun refreshToken(userName: String, refreshToken: String):TokenView{
        try {

            val user = repository.findByUserName(userName)?:throw  UsernameNotFoundException("Username $userName not found")
            return tokenProvider.refreshToken(refreshToken)
            }catch (e: AuthenticationException){
                throw UsernameNotFoundException( "Invalidusername or password supplied")
            }

    }
}