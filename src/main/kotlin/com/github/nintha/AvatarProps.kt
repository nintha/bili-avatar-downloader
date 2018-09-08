package com.github.nintha

import java.util.*

class AvatarProps {
    companion object {
        private val props: Map<String, String>

        init {
            val prop = Properties()
            prop.load(Object::class.java.getResourceAsStream("/avatar.properties"))
            @Suppress("UNCHECKED_CAST")
            props = prop.toMap() as Map<String, String>
        }
        operator fun get(key: String) = props[key]
    }

}



