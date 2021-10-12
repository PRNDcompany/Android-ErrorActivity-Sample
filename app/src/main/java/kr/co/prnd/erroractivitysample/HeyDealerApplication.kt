package kr.co.prnd.erroractivitysample

import androidx.multidex.MultiDexApplication

class HeyDealerApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        setCrashHandler()
    }

    private fun setCrashHandler() {
        val crashlyticsExceptionHandler = Thread.getDefaultUncaughtExceptionHandler() ?: return
        Thread.setDefaultUncaughtExceptionHandler(
            HeyDealerExceptionHandler(
                this,
                crashlyticsExceptionHandler
            )
        )
    }

}
