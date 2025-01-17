package com.oxford.sean.util


class UnauthorizedRequestException : Exception()
class NoInternetException : Exception()
class UnknownException(message: String) : Exception(message)