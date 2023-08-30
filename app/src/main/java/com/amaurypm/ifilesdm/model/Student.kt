package com.amaurypm.ifilesdm.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Creado por Amaury Perea Matsumura el 25/08/23
 */
@Root(name = "student")
data class Student(
    @field:Element(name = "id")
    var id: Int = System.currentTimeMillis().toInt(), //Para generar un id único cada vez
    @field:Element(name = "name")
    var name: String? = null
)