package com.example.rolldice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

@Composable
fun Pages(
    navController: NavController,
    resultShow: Int = 1,
    modifier: Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(
            Alignment.Center
        )
) {
    val result by remember {
        mutableStateOf(resultShow)
    }

    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { navController.navigate(Screens.Roll.route) }) {
            Text(text = stringResource(id = R.string.back))
        }
        Button(onClick = { navController.navigate(Screens.Card.route) }) {
            Text(text = stringResource(R.string.screen_1))
        }
        Button(onClick = {
            navController.navigate(
                Screens.DiceResult.route.replace(
                    oldValue = "{result}",
                    newValue = result.toString()
                )
            )
        }) {
            Text(text = stringResource(R.string.screen_2))
        }
        Button(onClick = {
            navController.navigate(
                Screens.Increment.route.replace(
                    oldValue = "{result}",
                    newValue = result.toString()
                )
            )
        }) {
            Text(text = stringResource(R.string.screen_3))
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = stringResource(R.string.screen_5))
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = stringResource(R.string.screen_6))
        }
    }
}