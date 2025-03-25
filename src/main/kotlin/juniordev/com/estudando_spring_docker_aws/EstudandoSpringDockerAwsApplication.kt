package juniordev.com.estudando_spring_docker_aws

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder

@SpringBootApplication
class EstudandoSpringDockerAwsApplication

fun main(args: Array<String>) {
	runApplication<EstudandoSpringDockerAwsApplication>(*args)
	val passwordEncoder = Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8()
	val senha = "admin123" // Substitua pela senha desejada
	val senhaHash = passwordEncoder.encode(senha)
	println("Senha hasheada: $senhaHash")
}
