package com.example.rolldice

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiceResultWithTextField(
    navController: NavController,
    resultShow: Int,
    modifier: Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(
            Alignment.Center
        )
) {
    var result by remember {
        mutableStateOf(resultShow.toString())
    }
    var imageResource = when (result) {
        "1" -> R.drawable.dice_1
        "2" -> R.drawable.dice_2
        "3" -> R.drawable.dice_3
        "4" -> R.drawable.dice_4
        "5" -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = imageResource), contentDescription = result)

        Button(onClick = { navController.navigate(Screens.Roll.route) }) {
            Text(text = stringResource(R.string.back), fontSize = 24.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = result,
            onValueChange = {

                result = it
                if (it.isNotEmpty() && it.toInt() in 1..6) {
                    imageResource = when (result) {
                        "1" -> R.drawable.dice_1
                        "2" -> R.drawable.dice_2
                        "3" -> R.drawable.dice_3
                        "4" -> R.drawable.dice_4
                        "5" -> R.drawable.dice_5
                        else -> R.drawable.dice_6
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
        )
    }

}