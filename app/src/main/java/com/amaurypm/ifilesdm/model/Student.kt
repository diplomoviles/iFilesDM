package com.amaurypm.ifilesdm.model

data class Student(
    var id: Int = System.currentTimeMillis().toInt(), //va a generar un id único cada vez
    var name: String? = null
)
