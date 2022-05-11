package com.hynekbraun.randomanimalscompose1

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application()

//Redo the swipe refresh so it does not have a fixed time but
//disappears when it should depending on the state

//Handle Error states with connection issues
//Connection - show a dialog with a try again button

//Cancel the data fetch when user goes to detail screen while fetching data
//For now, show a dialog witk OK and Retry buttons. Put some picture in the background
//if the user decides he does not want to fetch again

//Add appbar with dark mode toggle and potentially search window