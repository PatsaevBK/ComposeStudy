package com.example.composestudy.presentation.decompose

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.composestudy.presentation.decompose.root.DogComponent
import com.example.composestudy.presentation.decompose.root.RootComponent
import kotlin.math.PI
import kotlin.math.min


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomNavigationContent(rootComponent: RootComponent, modifier: Modifier = Modifier) {
    val children by rootComponent.children.subscribeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Custom decompose navigation") }
            )
        },
        modifier = modifier,
    ) {
        Box(Modifier
            .fillMaxSize()
            .padding(it)) {
            ChildItems(children, Modifier)
        }
    }
}

@Composable
private fun ChildItems(
    children: RootComponent.Children<*, DogComponent>,
    modifier: Modifier,
) {
    BoxWithConstraints(modifier = modifier) {
        children.items.forEachIndexed { index, child ->
            key(child.configuration) {
                val childModifier =
                    Modifier.getChildModifier(
                        activeIndex = children.index,
                        childIndex = index,
                        childCount = children.items.size,
                        mode = children.mode,
                        constraints = constraints,
                    )

                when (children.mode) {
                    RootComponent.Mode.CAROUSEL -> {
                        
                    }
                    RootComponent.Mode.PAGER -> TODO()
                }
            }
        }
    }
}

@Composable
fun Modifier.getChildModifier(
    activeIndex: Int,
    childIndex: Int,
    childCount: Int,
    mode: RootComponent.Mode,
    constraints: Constraints,
): Modifier {
    val constraintSize = min(constraints.maxWidth, constraints.maxHeight)
    val size = constraintSize / if (childIndex == activeIndex) 5f else 6f
    val targetOffset = when (mode) {
        RootComponent.Mode.CAROUSEL -> {
            val angle = ((childIndex - activeIndex) * 360f / childCount).toRadians()
            val offset = IntOffset(0, 0)
            IntOffset(1, 1)
        }

        RootComponent.Mode.PAGER -> TODO()
    }
    
    val offset by animateIntOffsetAsState(targetValue = targetOffset)
    return this.graphicsLayer {
        translationX = offset.x.toFloat()
        translationY = offset.y.toFloat()
        

    }
}

private fun Float.toRadians(): Float {
    return (this * PI / 180).toFloat()
}
