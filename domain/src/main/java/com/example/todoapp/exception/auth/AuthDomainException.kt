package com.example.todoapp.exception.auth

import com.example.todoapp.exception.DomainException

abstract class AuthDomainException : DomainException()

class EmailNotFoundException : AuthDomainException()
class InvalidEmailException : AuthDomainException()
class InvalidPasswordException : AuthDomainException()
class WeakPasswordException : AuthDomainException()
class UnknownException : AuthDomainException()