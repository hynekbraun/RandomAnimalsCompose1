package com.hynekbraun.randomanimalscompose1

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application()

/*

-- DONE
Change navigation, nav controller should not be passed as a parameter

-- ALMOST DONE
Connection - show a dialog with a try again button
*** I ended up doing a banner that triggers only when data is needed

-- NOT DONE
Redo the swipe refresh so it does not have a fixed time but
disappears when it should depending on the state

Handle Error states with connection issues

Cancel the data fetch when user goes to detail screen while fetching data

Add appbar with dark mode toggle and potentially search window
 */
