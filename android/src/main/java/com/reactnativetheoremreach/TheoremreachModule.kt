package com.reactnativetheoremreach

import androidx.annotation.Nullable
import com.facebook.react.bridge.*
import com.facebook.react.modules.core.RCTNativeAppEventEmitter

import theoremreach.com.theoremreach.TheoremReach
import theoremreach.com.theoremreach.TheoremReachRewardListener
import theoremreach.com.theoremreach.TheoremReachSurveyAvailableListener
import theoremreach.com.theoremreach.TheoremReachSurveyListener


class TheoremreachModule(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext), LifecycleEventListener, TheoremReachRewardListener, TheoremReachSurveyListener, TheoremReachSurveyAvailableListener {
    private var isAppInitialized = false
    override fun getName(): String {
        return "RNTheoremReach"
    }

    @ReactMethod
    fun initWithApiKeyAndUserId(apiKey: String?, userId: String?) {
        TheoremReach.initWithApiKeyAndUserIdAndActivityContext(apiKey, userId, currentActivity)

        // The below code is required because onResume is called before this method by default
        // and it should be prevented for the correct working of the SDK
        TheoremReach.getInstance().onResume(currentActivity)
        isAppInitialized = true
        TheoremReach.getInstance().setTheoremReachRewardListener(this)
        TheoremReach.getInstance().setTheoremReachSurveyListener(this)
        TheoremReach.getInstance().setTheoremReachSurveyAvailableListener(this)
    }

    @ReactMethod
    fun showRewardCenter() {
        TheoremReach.getInstance().showRewardCenter()
    }

    @ReactMethod
    fun isSurveyAvailable(cb: Callback) {
        cb.invoke(TheoremReach.getInstance().isSurveyAvailable())
    }

    /* Callbacks */
    private fun sendEvent(reactContext: ReactContext,
                          eventName: String,
                          @Nullable params: Any?) {
        reactContext
                .getJSModule(RCTNativeAppEventEmitter::class.java)
                .emit(eventName, params)
    }

    override fun theoremreachSurveyAvailable(surveyAvailable: Boolean) {
        sendEvent(reactContext, "theoremreachSurveyAvailable", surveyAvailable)
    }

    override fun onReward(quantity: Int) {
        sendEvent(reactContext, "onReward", quantity)
    }

    override fun onRewardCenterOpened() {
        sendEvent(reactContext, "onRewardCenterOpened", null)
    }

    override fun onRewardCenterClosed() {
        sendEvent(reactContext, "onRewardCenterClosed", null)
    }

    /* Lifecycle methods */
    override fun onHostResume() {
        if (isAppInitialized) {
            TheoremReach.getInstance().onResume(currentActivity)
        }
    }

    override fun onHostPause() {
        TheoremReach.getInstance().onPause()
    }

    override fun onHostDestroy() {
        // Actvity `onDestroy`
    }

    init {
        reactContext.addLifecycleEventListener(this)
    }
}