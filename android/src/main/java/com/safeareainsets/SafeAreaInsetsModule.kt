package com.safeareainsets

import android.graphics.Insets
import android.os.Build
import android.view.View
import android.view.WindowInsets
import androidx.core.view.WindowInsetsCompat
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.WritableMap
import com.facebook.react.bridge.WritableNativeMap
import com.facebook.react.module.annotations.ReactModule


@ReactModule(name = SafeAreaInsetsModule.NAME)
class SafeAreaInsetsModule(reactContext: ReactApplicationContext) :
  NativeSafeAreaInsetsSpec(reactContext) {

  override fun getName(): String {
    return NAME
  }

  private fun _getSafeAreaInsets(): Map<String, Int> {
    val constants: MutableMap<String, Int> = HashMap()

      val activity = currentActivity
      val view = activity!!.window.decorView
      val insets = view.rootWindowInsets

      val isFullscreen =
        (view.systemUiVisibility and View.SYSTEM_UI_FLAG_IMMERSIVE) == View.SYSTEM_UI_FLAG_IMMERSIVE

      if (insets != null && isFullscreen) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
          val insets30: Insets = insets.getInsets(WindowInsets.Type.systemGestures())

          constants["top"] = insets30.top
          constants["bottom"] = insets30.bottom
          constants["left"] = insets30.left
          constants["right"] = insets30.right
        } else {
          val insets2 = WindowInsetsCompat.toWindowInsetsCompat(insets).getInsets(
            WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
          )

          constants["top"] = insets2.top
          constants["bottom"] =
            insets2.bottom
          constants["left"] = insets2.left
          constants["right"] = insets2.right
        }
      } else {
        constants["top"] = 0
        constants["bottom"] = 0
        constants["left"] = 0
        constants["right"] = 0
      }

    return constants
  }

  override fun getSafeAreaInsets(): WritableMap {
    val constants = this._getSafeAreaInsets()
    val map: WritableMap = WritableNativeMap()

    map.putInt("top", constants["top"]!!)
    map.putInt("bottom", constants["bottom"]!!)
    map.putInt("left", constants["left"]!!)
    map.putInt("right", constants["right"]!!)

    return map
  }


  companion object {
    const val NAME = "SafeAreaInsets"
  }
}
