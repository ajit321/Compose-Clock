package com.example.composeclock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeclock.ui.theme.BgColor
import com.example.composeclock.ui.theme.ComposeClockTheme
import com.example.composeclock.ui.theme.Green
import com.example.composeclock.ui.theme.Red
import com.example.composeclock.viewmodel.ComposeClockViewModel
import java.lang.Math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    //val viewModel: ComposeClockViewModel = viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeClockTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = "Compose Clock", modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            )
                        }, backgroundColor = Color.Green
                    ) {
                        ComposeClock()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeClock() {
    val viewModel: ComposeClockViewModel = viewModel()
    val clockState: Triple<Float, Float, Float> by viewModel.clockState.collectAsState()
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Current Time",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))
            ComposeClockView(
                hourAngle = clockState.first,
                minuteAngle = clockState.second,
                secondAngle = clockState.third,
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .background(
                        color = MaterialTheme.colors.surface,
                        shape = CircleShape
                    ),

                )
        }
    }
}

/*@Composable
fun ComposeClockView(
    hourAngel: Float,
    minuteAngel: Float,
    secondAngel: Float,
    modifier: Modifier = Modifier,
    textColor: Color = Red,
    hourHandColor: Color = Green,
    minuteHandColor: Color = Green,
    secondHandColor: Color = Red
) {
    Canvas(
        modifier = modifier
            .clip(CircleShape)
            .background(BgColor)
    ) {
        //Draw Outer circle
        drawCircle(
            color = textColor,
            style = Stroke(20.dp.toPx())
        )
        //Draw Hour line
        val lineOuterR = (size.minDimension / 2.0f).minus(6.dp.toPx())
        val lineInnerR = (size.minDimension / 2.0f).minus(18.dp.toPx())
        val origin = size.minDimension / 2.0f

        for (i in 0..11) {
            val xPad = kotlin.math.cos(30.0 * i * Math.PI / 180F)
            val yPad = sin(30.0 * i * Math.PI / 180F)
            drawLine(
                color = textColor,
                start = Offset(
                    (origin + (lineInnerR * xPad)).toFloat(),
                    (origin + (lineInnerR * yPad)).toFloat()
                ),
                end = Offset(
                    (origin + (lineOuterR * xPad)).toFloat(),
                    (origin + (lineOuterR * yPad)).toFloat()
                ),
                strokeWidth = if (i % 3 == 0) {
                    4.dp.toPx()
                } else {
                    2.dp.toPx()
                },
                cap = StrokeCap.Round
            )
        }

        //Draw Inner circle
        val rInnerCircle = (size.minDimension / 2.0f).minus(22.dp.toPx())

        drawCircle(
            color = textColor,
            radius = rInnerCircle,
            style = Stroke(1.dp.toPx())
        )
        //Center Points
        drawCircle(
            color = hourHandColor,
            radius = 4.dp.toPx()
        )

        //Draw hours Hand
        val xRadHours = kotlin.math.cos(hourAngel * Math.PI / 180F)
        val yRadHours = sin(hourAngel * Math.PI / 180F)
        val rHours = rInnerCircle.times(3).div(4)
        drawLine(
            color = hourHandColor,
            start = Offset(origin,origin),
            end = Offset(
                (origin+(rHours*xRadHours)).toFloat(),
                (origin+(rHours*yRadHours)).toFloat()
            ),
            strokeWidth =3.dp.toPx() ,
            cap = StrokeCap.Round
        )

        //Draw minute Hand
        val xRadMinute = kotlin.math.cos(minuteAngel * Math.PI / 180F)
        val yRadMinute = sin(minuteAngel * Math.PI / 180F)
        val rMinute = rInnerCircle.minus(2.dp.toPx())

        drawLine(
            color = minuteHandColor,
            start = Offset(origin,origin),
            end = Offset(
                (origin+(rMinute*xRadMinute)).toFloat(),
                (origin+(rMinute*yRadMinute)).toFloat()
            ),
            strokeWidth =2.dp.toPx() ,
            cap = StrokeCap.Round
        )
        //Draw Second Hand
        *//*val xRadSecond = kotlin.math.cos(secondAngel * Math.PI / 180F)
        val yRadSecond = sin(secondAngel * Math.PI / 180F)
        val rSecond = lineOuterR.minus(2.dp.toPx())
        drawLine(
            color = secondHandColor,
            start = Offset(origin,origin),
            end = Offset(
                (origin+(rSecond*xRadSecond)).toFloat(),
                (origin+(rSecond*yRadSecond)).toFloat()
            ),
            strokeWidth =1.dp.toPx() ,
            cap = StrokeCap.Round
        )*//*

        val xRadSecond = cos(secondAngel * Math.PI / 180F)
        val yRadSecond = sin(secondAngel * Math.PI / 180F)
        val rSecond = lineOuterR.minus(2.dp.toPx())
        drawLine(
            color = secondHandColor,
            start = Offset(origin, origin),
            end = Offset(
                (origin + (rSecond * xRadSecond)).toFloat(),
                (origin + (rSecond * yRadSecond)).toFloat()
            ),
            strokeWidth = 1.dp.toPx(),
            cap = StrokeCap.Round
        )

    }
}*/

@Composable
fun ComposeClockView(
    hourAngle: Float,
    minuteAngle: Float,
    secondAngle: Float,
    modifier: Modifier = Modifier,
    textColor: Color = Red,
    hourHandColor: Color = Green,
    minuteHandColor: Color = Green,
    secondHandColor: Color = Red
) {
    Canvas(
        modifier = modifier
            .clip(CircleShape)
            .background(BgColor)
    ) {
        //Draw Outer Circle
        drawCircle(
            color = textColor,
            style = Stroke(2.dp.toPx())
        )

        //Draw Hour Line
        val lineOuterR = (size.minDimension / 2.0f).minus(6.dp.toPx())
        val lineInnerR = (size.minDimension / 2.0f).minus(18.dp.toPx())
        val origin = size.minDimension / 2.0f
        for (i in 0..11) {
            val xRad = cos(30.0 * i * Math.PI / 180F)
            val yRad = sin(30.0f * i * Math.PI / 180F)
            drawLine(
                color = textColor,
                start = Offset(
                    (origin + (lineInnerR * xRad)).toFloat(),
                    (origin + (lineInnerR * yRad)).toFloat()
                ),
                end = Offset(
                    (origin + (lineOuterR * xRad)).toFloat(),
                    (origin + (lineOuterR * yRad)).toFloat()
                ),
                strokeWidth = if (i % 3 == 0) {
                    4.dp.toPx()
                } else {
                    2.dp.toPx()
                },
                cap = StrokeCap.Round
            )
        }

        //Draw InnerCircle
        val rInnerCircle = (size.minDimension / 2.0f).minus(22.dp.toPx())
        drawCircle(
            color = textColor,
            radius = rInnerCircle,
            style = Stroke(1.dp.toPx())
        )

        //CenterPoint
        drawCircle(
            color = hourHandColor,
            radius = 4.dp.toPx()
        )

        //Draw HourHand
        val xRadHour = cos(hourAngle * Math.PI / 180F)
        val yRadHour = sin(hourAngle * Math.PI / 180F)
        val rHour = rInnerCircle.times(3).div(4)
        drawLine(
            color = hourHandColor,
            start = Offset(origin, origin),
            end = Offset(
                (origin + (rHour * xRadHour)).toFloat(),
                (origin + (rHour * yRadHour)).toFloat()
            ),
            strokeWidth = 3.dp.toPx(),
            cap = StrokeCap.Round
        )

        //Draw MinuteHand
        val xRadMinute = cos(minuteAngle * Math.PI / 180F)
        val yRadMinute = sin(minuteAngle * Math.PI / 180F)
        val rMinute = rInnerCircle.minus(2.dp.toPx())
        drawLine(
            color = minuteHandColor,
            start = Offset(origin, origin),
            end = Offset(
                (origin + (rMinute * xRadMinute)).toFloat(),
                (origin + (rMinute * yRadMinute)).toFloat()
            ),
            strokeWidth = 2.dp.toPx(),
            cap = StrokeCap.Round
        )

        //Draw SecondHand
        val xRadSecond = cos(secondAngle * Math.PI / 180F)
        val yRadSecond = sin(secondAngle * Math.PI / 180F)
        val rSecond = lineOuterR.minus(2.dp.toPx())
        drawLine(
            color = secondHandColor,
            start = Offset(origin, origin),
            end = Offset(
                (origin + (rSecond * xRadSecond)).toFloat(),
                (origin + (rSecond * yRadSecond)).toFloat()
            ),
            strokeWidth = 1.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

