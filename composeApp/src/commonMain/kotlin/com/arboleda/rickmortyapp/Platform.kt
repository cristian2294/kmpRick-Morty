package com.arboleda.rickmortyapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform