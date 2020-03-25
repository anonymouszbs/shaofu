package com.xspp.flutter_specil

import io.flutter.embedding.android.FlutterFragment
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast

import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.StringCodec
import io.flutter.view.FlutterView
import java.util.*

class FlutterBaseFragment : FlutterFragment() {
    private val METHOD_CHANNEL = "tip.flutter.io/method"
    private val EVENT_CHANNEL = "tip.flutter.io/event"
    private val MESSAGE_CHANNEL = "tip.flutter.io/message"
    private val handler = Handler()
    var mesageChan: BasicMessageChannel<String>? = null

    companion object {
        fun newInstance(router: String) = FlutterBaseFragment().apply {
            arguments = Bundle().apply {
                putString("router", router)
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MethodChannel((getView() as FlutterView), METHOD_CHANNEL).setMethodCallHandler { call, result ->
            if (call.method == "getBatteryLevel") {
                val ba = getBatteryLevel()
                if (ba != -1) {
                    result.success(ba)
                } else {
                    result.error("UNAVAILABLE", "Battery level not available.", null)
                }
            } else {
                result.notImplemented()
            }
        }
        EventChannel((getView() as FlutterView), EVENT_CHANNEL).setStreamHandler(object : EventChannel.StreamHandler {
            override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
                Timer().schedule(object : TimerTask() {
                    override fun run() {
                        handler.post {
                            events?.success("当前时间毫秒${System.currentTimeMillis()}")
                        }
                    }
                }, 1000, 1000)
            }

            override fun onCancel(p0: Any?) {
            }

        })
        mesageChan = BasicMessageChannel<String>(
                (getView() as FlutterView),
                MESSAGE_CHANNEL,
                StringCodec.INSTANCE
        )
        mesageChan?.setMessageHandler { a, b ->
            b.reply("aaaa${System.currentTimeMillis()}")
            Toast.makeText(context,a,Toast.LENGTH_LONG).show()
            // Log.e("收到消息", "a=$a=b=${b.reply("aaaa${System.currentTimeMillis()}")}")
        }
    }
    private fun getBatteryLevel(): Int {
        return 123
    }


}
