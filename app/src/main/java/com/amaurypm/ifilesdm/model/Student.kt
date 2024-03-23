package com.amaurypm.ifilesdm.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "student")
data class Student(
    @field:Element(name = "id")
    var id: Int = System.currentTimeMillis().toInt(), //va a generar un id único cada vez
    @field:Element(name = "name")
    var name: String? = null
)
