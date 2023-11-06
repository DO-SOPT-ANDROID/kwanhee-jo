package org.sopt.dosopttemplate.model

sealed class HomeViewTypeModel (val type:Int) {
    object Header : HomeViewTypeModel(0)
    object Birthday : HomeViewTypeModel(1)
    object Profile: HomeViewTypeModel(2)
}