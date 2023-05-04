package com.example.core_domain.exception.users

abstract class UsersDomainException : IllegalStateException()

class EmailNotFoundException : UsersDomainException()
class InvalidEmailException : UsersDomainException()
class InvalidPasswordException : UsersDomainException()
class WeakPasswordException : UsersDomainException()
class NoInternetConnectionException : UsersDomainException()
class ServiceUnavailableException : UsersDomainException()
class UnknownException: UsersDomainException()