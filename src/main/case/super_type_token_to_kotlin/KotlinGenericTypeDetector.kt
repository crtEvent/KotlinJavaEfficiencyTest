package super_type_token_to_kotlin

import kotlin.reflect.KClass

fun main() {
    val t1 = KotlinGenericTypeDetector<Int>()
    t1.action()

    val t2 = KotlinGenericTypeDetector<String>()
    t2.action()
}

class KotlinGenericTypeDetector<T> @PublishedApi internal constructor(private val thisType:KClass<*>) {
    companion object {
        inline operator fun <reified T> invoke(): KotlinGenericTypeDetector<T>
            = KotlinGenericTypeDetector(T::class)
    }
    fun action() {
        when(thisType) {
            Int::class -> println("Int")
            String::class -> println("String")
            else -> println("Unknown Type")
        }
    }
}
