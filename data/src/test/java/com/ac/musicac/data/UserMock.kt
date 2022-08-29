package com.ac.musicac.data

import com.ac.musicac.domain.User

object UserMock {

    fun fakeUser() : User  =  User(
        id = "1",
        displayName = "Adrián",
        followers = 0,
        imageUrl = ""
    )
}