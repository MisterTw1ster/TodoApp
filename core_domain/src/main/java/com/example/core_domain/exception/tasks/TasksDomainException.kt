package com.example.core_domain.exception.tasks

abstract class DomainException : IllegalStateException()

class NoInternetConnectionException : DomainException()
class ServiceUnavailableException : DomainException()