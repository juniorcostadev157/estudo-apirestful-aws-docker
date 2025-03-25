package juniordev.com.estudando_spring_docker_aws.controller

import juniordev.com.estudando_spring_docker_aws.dto.AccountCredentialsDTO
import juniordev.com.estudando_spring_docker_aws.dto.TokenView
import juniordev.com.estudando_spring_docker_aws.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController (private val authService: AuthService){


    @PostMapping("/signin")
    fun signin(@RequestBody login: AccountCredentialsDTO): ResponseEntity<TokenView>{
        val user  = authService.signin(login.username!!, login.password!!)
        return ResponseEntity.status(HttpStatus.OK).body(user)
    }
    @PutMapping("/refresh/{username}")
    fun refreshToken(@PathVariable username: String, @RequestHeader("Authorization") refreshToken:String )
    :ResponseEntity<TokenView>{
       val user  = authService.refreshToken(username, refreshToken)
       return ResponseEntity.status(HttpStatus.OK).body(user)
    }
}