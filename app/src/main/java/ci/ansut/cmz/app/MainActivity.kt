package ci.ansut.cmz.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ci.ansut.cmz.core.navigation.AppNavigation
import ci.ansut.cmz.ui.theme.CmzTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("on create")
        enableEdgeToEdge()
        setContent {
            CmzTheme {
                AppNavigation()
            }
        }
    }

}