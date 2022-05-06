package com.ac.musicac.data

sealed class GranType(val type: String)  {
    object Implicit : GranType("implicit")
    object AuthorizationCode : GranType("authorization_code")
    object ClientCredentials : GranType("client_credentials")
    object RefreshToken : GranType("refreh_token")
}
