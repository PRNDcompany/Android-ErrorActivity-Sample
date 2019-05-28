package kr.co.prnd.erroractivitysample

import androidx.multidex.MultiDexApplication
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

class HeyDealerApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        setCrashHandler()
    }

    private fun setCrashHandler() {

        val defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { _, _ ->
            // Crashlytics에서 기본 handler를 호출하기 때문에 이중으로 호출되는것을 막기위해 빈 handler로 설정
        }
        Fabric.with(this, Crashlytics())
        val fabricExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(
            HeyDealerExceptionHandler(
                this,
                defaultExceptionHandler,
                fabricExceptionHandler
            )
        )
    }

}