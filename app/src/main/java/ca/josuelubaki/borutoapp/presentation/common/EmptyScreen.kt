package ca.josuelubaki.borutoapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import ca.josuelubaki.borutoapp.R
import ca.josuelubaki.borutoapp.ui.theme.NETWORK_ERROR_ICON_HEIGHT
import ca.josuelubaki.borutoapp.ui.theme.SMALL_PADDING

@Composable
fun EmptyScreen(error : LoadState.Error) {
    val message by remember { mutableStateOf(parseErrorMessage(error.toString())) }

    val icon by remember { mutableStateOf(R.drawable.ic_network_error) }

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnimation by animateFloatAsState(
        targetValue = if(startAnimation) ContentAlpha.disabled else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing
        )
    )

    LaunchedEffect(key1 = true){
        startAnimation = true
    }

    EmptyContent(alphaAnimation, icon, message)

}

@Composable
fun EmptyContent(alphaAnimation : Float, icon : Int, message : String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Icon(
            modifier = Modifier
                .alpha(alphaAnimation)
                .size(NETWORK_ERROR_ICON_HEIGHT),
            painter = painterResource(id = icon),
            contentDescription = stringResource(R.string.network_error_icon),
            tint = if(isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
        )
        Text(
            modifier = Modifier
                .alpha(alphaAnimation)
                .padding(top = SMALL_PADDING),
            text = message,
            color = if(isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign= TextAlign.Center,
        )
    }
}

fun parseErrorMessage(message : String) : String {
    return when {
        message.contains("SocketTimeoutException") -> "Server Unavailable"
        message.contains("ConnectException") -> "Internet Unavailable"
        else -> "Unknown Error"
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenPreview() {
    EmptyContent(
        alphaAnimation = ContentAlpha.disabled,
        icon = R.drawable.ic_network_error,
        message = "Server Unavailable"
    )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun EmptyScreenDarkPreview() {
    EmptyContent(
        alphaAnimation = ContentAlpha.disabled,
        icon = R.drawable.ic_network_error,
        message = "internet Unavailable"
    )
}