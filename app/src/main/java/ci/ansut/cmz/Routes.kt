package ci.ansut.cmz

import kotlinx.serialization.Serializable

object Routes {

    @Serializable
    object A

    @Serializable
    data class B (val name: String)

}

