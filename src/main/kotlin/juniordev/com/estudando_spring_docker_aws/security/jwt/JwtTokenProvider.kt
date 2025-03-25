package juniordev.com.estudando_spring_docker_aws.security.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import juniordev.com.estudando_spring_docker_aws.dto.TokenView
import juniordev.com.estudando_spring_docker_aws.exception.InvalidJwtAuthenticationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.Base64
import java.util.Date

@Service
class JwtTokenProvider {
    @Value("\${jwt.token.secret-key}")
    private var secretKey = "secret"

    @Value("\${jwt.token.expire-length}")
    private var validityMilliseconds: Long = 3_600_000

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    private lateinit var algorithm: Algorithm

    @PostConstruct
    protected fun init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
        algorithm = Algorithm.HMAC256(secretKey.toByteArray())
    }

    fun createAcess(userName:String, roles:List<String?>): TokenView{
        val now  = Date()
        val validity = Date(now.time + validityMilliseconds)
        val accesToken = getAcessToken(userName, roles, now, validity)
        val refreshToken = getRefreshToken(userName, roles, now)
        return TokenView(
            username = userName,
            authenticated = true,
            accessToken = accesToken,
            refreshToken = refreshToken,
            created = now,
            expiration = validity

        )
    }

    fun refreshToken(refreshToken: String): TokenView{
        var token: String = ""
        if (refreshToken.contains("Bearer ")) token = refreshToken.substring("Bearer ".length)
        val verify: JWTVerifier = JWT.require(algorithm).build()
        var decodedJWT: DecodedJWT  = verify.verify(token)
        val username: String = decodedJWT.subject
        val roles: List<String> = decodedJWT.getClaim("roles").asList(String::class.java)
        return createAcess(username, roles)
    }

    private fun getRefreshToken(userName: String, roles: List<String?>, now: Date): String {
        val validityRefreshToken = Date(now.time + validityMilliseconds * 3)
        return  JWT.create().withClaim("roles", roles)
            .withExpiresAt(validityRefreshToken)
            .withSubject(userName)
            .sign(algorithm)
            .trim()
    }

    private fun getAcessToken(userName: String, roles: List<String?>, now: Date, validity: Date): String {
        val issuerURL: String = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()
        return  JWT.create().withClaim("roles", roles)
            .withIssuedAt(now)
            .withExpiresAt(validity)
            .withSubject(userName)
            .withIssuer(issuerURL)
            .sign(algorithm)
            .trim()
    }

    fun getAuthentication(token:String):Authentication{
        val decodedJWT: DecodedJWT = decodedToken(token)
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(decodedJWT.subject)
        return  UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun decodedToken(token: String): DecodedJWT{
        val algorithm = Algorithm.HMAC256(secretKey.toByteArray())
        val verifier: JWTVerifier = JWT.require(algorithm).build()
        return verifier.verify(token)
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (!bearerToken.isNullOrBlank() && bearerToken.startsWith("Bearer")) {
            bearerToken.substring("Bearer".length).trim() // 🔥 Agora removemos o espaço extra!
        } else null
    }


    fun validateToken(token: String): Boolean {
        return try {
            val decodedJWT = decodedToken(token)
            if (decodedJWT.expiresAt.before(Date())) {
                println("❌ Token expirado!")
                return false
            }
            true
        } catch (e: Exception) {
            println("❌ Token inválido: ${e.message}")
            false
        }
    }



}