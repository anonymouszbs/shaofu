package com.xspp.flutter_plugin_google_example;
import android.content.BroadcastReceiver;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;



import io.flutter.Log;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.plugin.common.EventChannel;
import io.flutter.view.FlutterView;

public class FlutterEventChannel extends FlutterActivity implements EventChannel.StreamHandler {

    private static final String TAG = "FlutterEventChannel";
    private static final String EVENT_CHANNEL_NAME = "com.meetyou.flutter/event";
    BroadcastReceiver chargingStateChangeReceiver;
    private FlutterEventChannel(FlutterView flutterView) {
        EventChannel eventChannel = new EventChannel(flutterView, EVENT_CHANNEL_NAME);
        eventChannel.setStreamHandler(this);
    }

    public static FlutterEventChannel create(FlutterView flutterView) {
        return new FlutterEventChannel(flutterView);
    }

    private EventChannel.EventSink eventSink;

    /**
     * 暴露出去供界面传数据到Flutter
     */
    public void sendEvent(Object data) {
        if (eventSink != null) {
            eventSink.success(data);
        } else {
            Log.e(TAG, "===== FlutterEventChannel.eventSink 为空 需要检查一下 =====");
        }
    }

    @Override
    public void onListen(Object arguments, EventChannel.EventSink events) {

        events.success("你好");
        events.success("hahah");

       //chargingStateChangeReceiver = createChargingStateChangeReceiver(events);
       //registerReceiver(
              // chargingStateChangeReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }


    @Override
    public void onCancel(Object o) {
       // unregisterReceiver(chargingStateChangeReceiver);
        //chargingStateChangeReceiver = null;
    }

    private BroadcastReceiver createChargingStateChangeReceiver(final EventChannel.EventSink events) {
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

                if (status == BatteryManager.BATTERY_STATUS_UNKNOWN) {
                    events.error("UNAVAILABLE", "Charging status unavailable", null);
                } else {
                    boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                            status == BatteryManager.BATTERY_STATUS_FULL;
                    events.success(isCharging ? "charging" : "discharging");
                }
            }
        };
    }
}
                //events.success("世界你好");
//                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
//
//                if (status == BatteryManager.BATTERY_STATUS_UNKNOWN) {
//                    events.error("UNAVAILABLE", "Charging status unavailable", null);
//                } else {
//                    boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
//                            status == BatteryManager.BATTERY_STATUS_FULL;
//                    // 把电池状态发给Flutter
//                    events.success(isCharging ? "charging" : "discharging");
//                }
//            }
//        };
//    }
//}

