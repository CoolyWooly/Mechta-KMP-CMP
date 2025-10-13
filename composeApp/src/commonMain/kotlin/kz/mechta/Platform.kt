package kz.mechta

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform