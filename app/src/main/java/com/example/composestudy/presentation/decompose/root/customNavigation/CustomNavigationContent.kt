package com.example.composestudy.presentation.decompose.root.customNavigation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import kotlin.math.PI
import kotlin.math.min


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomNavigationContent(customNavigationComponent: CustomNavigationComponent, modifier: Modifier = Modifier) {
    val children by customNavigationComponent.children.subscribeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Custom decompose navigation") },
                navigationIcon = {
                    IconButton(customNavigationComponent::onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ChildItems(children = children)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                Button(onClick = customNavigationComponent::onBackwardClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
                Button(onClick = customNavigationComponent::onForwardClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null
                    )
                }
            }
            Button(onClick = customNavigationComponent::remove) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun ChildItems(
    children: CustomNavigationComponent.Children<*, DogComponent>,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        children.items.forEachIndexed { index, child ->
            key(child.configuration) {
                val childModifier =
//                    Modifier.getChildModifier(
//                        activeIndex = children.index,
//                        childIndex = index,
//                        childCount = children.items.size,
//                        mode = children.mode,
//                        constraints = constraints,
//                    )

                when (children.mode) {
                    CustomNavigationComponent.Mode.CAROUSEL -> {
                        DogContent(
                            component = child.instance,
                            modifier = Modifier
                        )
                    }
                    CustomNavigationComponent.Mode.PAGER -> TODO()
                }
            }
        }
    }
}

@Composable
fun DogContent(component: DogComponent, modifier: Modifier) {
    val model by component.model.subscribeAsState()
    val color = when (model.imageUrl) {
        Images.DOG_1 -> Color.Cyan
        Images.DOG_2 -> Color.Yellow
        Images.DOG_3 -> Color.Green
    }

    Box(modifier = modifier) {
        Text(text = model.counter, modifier = Modifier
            .background(color)
            .size(100.dp))
    }
}

@Composable
fun Modifier.getChildModifier(
    activeIndex: Int,
    childIndex: Int,
    childCount: Int,
    mode: CustomNavigationComponent.Mode,
    constraints: Constraints,
): Modifier {
    val constraintSize = min(constraints.maxWidth, constraints.maxHeight)
    val size = constraintSize / if (childIndex == activeIndex) 5f else 6f
    val targetOffset = when (mode) {
        CustomNavigationComponent.Mode.CAROUSEL -> {
            val angle = ((childIndex - activeIndex) * 360f / childCount).toRadians()
            val xOffset = (childIndex - activeIndex) / childCount * size
            IntOffset(xOffset.toInt(), 0)
        }

        CustomNavigationComponent.Mode.PAGER -> TODO()
    }

    val targetSize = DpSize(size.dp, size.dp)
    
    val offset by animateIntOffsetAsState(targetValue = targetOffset)
    val width by animateDpAsState(targetSize.width)
    val height by animateDpAsState(targetSize.height)

    return this
        .size(width, height)
        .graphicsLayer {
            translationX = offset.x.toFloat()
            translationY = offset.y.toFloat()

            if (childIndex != activeIndex) {
                renderEffect = BlurEffect(3f, 3f)
            }

            clip = true
            shape = CircleShape
        }
}

private fun Float.toRadians(): Float {
    return (this * PI / 180).toFloat()
}
