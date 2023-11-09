package com.example.rolldice

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.rolldice.Navigation.NavGraph
import com.example.rolldice.ui.theme.RollDiceTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {

    private var mSensorManager: SensorManager? = null
    private var mAccelerometer: Sensor? = null
    private var mShakeDetector: ShakeDetector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RollDiceTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController, mShakeDetector = mShakeDetector)
            }
        }
        initSensor()
    }

    override fun onResume() {
        super.onResume()
        mSensorManager!!.registerListener(
            mShakeDetector, mAccelerometer,
            SensorManager.SENSOR_DELAY_UI
        )
    }

    override fun onPause() {
        mSensorManager!!.unregisterListener(mShakeDetector)
        super.onPause()
    }

    private fun initSensor(){
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        mShakeDetector = ShakeDetector()
    }
}

@Composable
fun DiceWithButtonAndImage(
    navController: NavController, resultShow: Int, mShakeDetector: ShakeDetector?, modifier: Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(
            Alignment.Center
        )
) {
    var result by remember {
        mutableStateOf(resultShow)
    }

    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    mShakeDetector?.setOnShakeListener(object : ShakeDetector.OnShakeListener {
        override fun onShake(count: Int) {
            result = (1..6).random()
        }
    })

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .height(300.dp)
                .width(300.dp)
        ) {
            var offsetX by remember {
                mutableStateOf(0f)
            }
            var offsetY by remember {
                mutableStateOf(0f)
            }


            Image(
                painter = painterResource(id = imageResource),
                contentDescription = result.toString(),
                modifier = Modifier
                    .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, _, _ ->
                            offsetX += pan.x
                            offsetY += pan.y
                        }
                    }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { result = (1..6).random() }) {
            Text(text = stringResource(R.string.roll))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                when (result) {
                    1 -> navController.navigate(
                        Screens.Card.route
                    )

                    2 -> navController.navigate(
                        Screens.DiceResult.route
                            .replace(
                                oldValue = "{result}",
                                newValue = result.toString()
                            )
                    )

                    3 -> navController.navigate(
                        Screens.Increment.route
                            .replace(
                                oldValue = "{result}",
                                newValue = result.toString()
                            )
                    )

                    4 -> navController.navigate(
                        Screens.Pages.route.replace(
                            oldValue = "{result}",
                            newValue = result.toString()
                        )
                    )

                    5 -> navController.navigate(
                        Screens.DiceResultWithTextField.route
                            .replace(
                                oldValue = "{result}",
                                newValue = result.toString()
                            )
                    )

                    else -> navController.navigate(
                        Screens.DiceResultReturn.route
                            .replace(
                                oldValue = "{result}",
                                newValue = result.toString()
                            )
                    )
                }
            }
        ) {
            Text(text = stringResource(R.string.result_screen), fontSize = 24.sp)
        }
    }
}

@Preview
@Composable
fun DiceRollerApp(mShakeDetector:ShakeDetector? = null) {
    DiceWithButtonAndImage(
        rememberNavController(),
        resultShow = 1,
        mShakeDetector = mShakeDetector,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
    )
}