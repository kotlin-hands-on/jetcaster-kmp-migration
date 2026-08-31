import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.example.jetcaster.core.data.network.OnlineChecker
import com.example.jetcaster.shared.di.initJetcasterDi
import com.example.jetcaster.ui.JetcasterApp
import com.example.jetcaster.ui.rememberJetcasterAppState
import com.example.jetcaster.ui.theme.JetcasterTheme
import kotlinx.browser.document
import org.koin.compose.koinInject

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initJetcasterDi()

    ComposeViewport(document.body!!) {
        val adaptiveInfo = currentWindowAdaptiveInfo(supportLargeAndXLargeWidth = false)
        val appState = rememberJetcasterAppState(onlineChecker = koinInject<OnlineChecker>())
        JetcasterTheme {
            JetcasterApp(
                adaptiveInfo = adaptiveInfo,
                appState = appState,
            )
        }
    }
}
