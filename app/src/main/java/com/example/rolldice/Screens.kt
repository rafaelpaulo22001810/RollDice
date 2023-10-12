package com.example.rolldice

sealed class Screens(val route: String){
    object Roll : Screens("roll_screen")
    object DiceResult : Screens("result_screen/{result}")
}
