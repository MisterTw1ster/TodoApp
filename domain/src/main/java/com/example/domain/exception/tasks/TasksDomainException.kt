package com.example.domain.exception.tasks

abstract class DomainException : IllegalStateException()

class NoInternetConnectionException : DomainException()
class ServiceUnavailableException : DomainException()