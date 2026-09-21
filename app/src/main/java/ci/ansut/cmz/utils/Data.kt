package ci.ansut.cmz.utils

object Data {
    val discussions = listOf(
        Discussion(
            participants = Pair("hote", "Sana"),
            messages = listOf(
                Message(
                    sender = "hote",
                    content = "Salut Sana 👋, comment vas-tu ?"
                ),
                Message(
                    sender = "Sana",
                    content = "Salut ! Ça va très bien, et toi ?"
                ),
                Message(
                    sender = "hote",
                    content = "Très bien aussi. Tu as pu avancer sur le projet ?"
                ),
                Message(
                    sender = "Sana",
                    content = "Oui, j'ai terminé la partie sur laquelle je travaillais."
                ),
                Message(
                    sender = "hote",
                    content = "Super ! Tu peux me l'envoyer quand tu as un moment ?"
                ),
                Message(
                    sender = "Sana",
                    content = "Bien sûr, je te l'envoie tout à l'heure 😊"
                )
            )
        ),

        Discussion(
            participants = Pair("hote", "Diarra"),
            messages = listOf(
                Message(
                    sender = "hote",
                    content = "Salut Diarra 👋"
                ),
                Message(
                    sender = "Diarra",
                    content = "Salut ! Quoi de neuf ?"
                ),
                Message(
                    sender = "hote",
                    content = "Je travaille sur l'application mobile."
                ),
                Message(
                    sender = "Diarra",
                    content = "Ah super ! Tu avances bien ?"
                ),
                Message(
                    sender = "hote",
                    content = "Oui, je suis actuellement sur la partie messagerie."
                ),
                Message(
                    sender = "Diarra",
                    content = "Excellent. Hâte de voir le résultat 🔥"
                ),
                Message(
                    sender = "hote",
                    content = "Je te montrerai une démo dès que c'est prêt 👍"
                )
            )
        )
    )
}