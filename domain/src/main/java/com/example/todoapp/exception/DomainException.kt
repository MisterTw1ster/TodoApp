package com.example.todoapp.exception

abstract class DomainException : IllegalStateException()

class NoInternetConnectionException : DomainException()
class ServiceUnavailableException : DomainException()