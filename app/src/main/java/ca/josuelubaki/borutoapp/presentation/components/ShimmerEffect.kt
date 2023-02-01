package ca.josuelubaki.borutoapp.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ca.josuelubaki.borutoapp.ui.theme.ABOUT_PLACEHOLDER_HEIGHT
import ca.josuelubaki.borutoapp.ui.theme.EXTRA_SMALL_PADDING
import ca.josuelubaki.borutoapp.ui.theme.HERO_ITEM_HEIGHT
import ca.josuelubaki.borutoapp.ui.theme.LARGE_PADDING
import ca.josuelubaki.borutoapp.ui.theme.RATING_PLACEHOLDER_HEIGHT
import ca.josuelubaki.borutoapp.ui.theme.SHIMMER_NAME_PLACEHOLDER_HEIGHT
import ca.josuelubaki.borutoapp.ui.theme.SMALL_PADDING
import ca.josuelubaki.borutoapp.ui.theme.ShimmerDarkGray
import ca.josuelubaki.borutoapp.ui.theme.ShimmerLightGray
import ca.josuelubaki.borutoapp.ui.theme.ShimmerMediumGray

@Composable
fun ShimmerEffect() {

}

@Composable
fun AnimatedShimmerItem() {
    val transition = rememberInfiniteTransition()
    val alphaAnim = transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                delayMillis = 100,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    ShimmerItem(alpha = alphaAnim.value)

}

@Composable
fun ShimmerItem(alpha: Float) {
    Surface (
        modifier= Modifier
            .fillMaxWidth()
            .height(HERO_ITEM_HEIGHT),
        color = if(isSystemInDarkTheme()) Color.Black else ShimmerLightGray,
        shape = RoundedCornerShape(size = LARGE_PADDING)
    ){
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING),
            verticalArrangement = Arrangement.Bottom
        ){
            Surface(
                modifier = Modifier
                    .alpha(alpha = alpha)
                    .fillMaxWidth(0.5f)
                    .height(SHIMMER_NAME_PLACEHOLDER_HEIGHT),
                color = if(isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
                shape = RoundedCornerShape(size = SMALL_PADDING)
            ){

            }
            Spacer(modifier = Modifier.padding(all= SMALL_PADDING))
            repeat(3){
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(alpha = alpha)
                        .height(ABOUT_PLACEHOLDER_HEIGHT),
                    color = if(isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
                    shape = RoundedCornerShape(size = SMALL_PADDING)
                ){

                }
                Spacer(modifier = Modifier.padding(all= EXTRA_SMALL_PADDING))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ){
                repeat(5){
                    Surface(
                        modifier = Modifier
                            .alpha(alpha = alpha)
                            .size(RATING_PLACEHOLDER_HEIGHT),
                        color = if(isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
                        shape = RoundedCornerShape(size = SMALL_PADDING)
                    ){

                    }
                    Spacer(modifier = Modifier.padding(all= SMALL_PADDING))
                }
            }
        }
    }
}

@Preview
@Composable
fun ShimmerItemPreview() {
    AnimatedShimmerItem()
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ShimmerItemDarkPreview() {
    AnimatedShimmerItem()
}