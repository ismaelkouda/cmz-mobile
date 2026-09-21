package ci.ansut.cmz.utils

data class Discussion(
    val participants: Pair<String, String>,
    val messages: List<Message>
)