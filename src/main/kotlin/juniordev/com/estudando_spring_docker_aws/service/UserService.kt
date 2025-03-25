package juniordev.com.estudando_spring_docker_aws.service

import juniordev.com.estudando_spring_docker_aws.entity.User

interface UserService {
    fun findByUserName(userName: String): User

    fun create(user: User):User

    fun findByAllUser(): List<User>
}