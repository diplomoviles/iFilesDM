package com.amaurypm.ifilesdm.model

/**
 * Creado por Amaury Perea Matsumura el 25/08/23
 */

data class Student(
    var id: Int = System.currentTimeMillis().toInt(), //Para generar un id único cada vez
    var name: String? = null
)
