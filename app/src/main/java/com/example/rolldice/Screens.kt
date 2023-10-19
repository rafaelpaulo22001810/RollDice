package com.example.rolldice

sealed class Screens(val route: String){
    object Roll : Screens("roll_screen/{result}")
    object DiceResult : Screens("result_screen/{result}")
    object Card : Screens("Card")
    object Pages : Screens("pages/{result}")
    object Increment : Screens("increment/{result}")
    object DiceResultWithTextField : Screens("result_screen_with_text_field/{result}")
    object DiceResultReturn : Screens("result_screen_return/{result}")
}
