package com.xspp.flutter_plugin_google_example;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;




import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;


public class MainActivity extends FlutterActivity {
  private static final String BATTERY_CHANNEL = "com.meetyou.flutter/event";
  private static final  String CHANNAL = "flutter_plugin_google";
  String guid = "吹牛逼";
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getGuid();
    //FlutterEventChannel.create(getFlutterView());


      new MethodChannel(getFlutterView(),CHANNAL).setMethodCallHandler(new MethodChannel.MethodCallHandler(){
          @Override
          public void onMethodCall(MethodCall call, MethodChannel.Result result) {

              if(call.method.equals("getGuId")){

                  result.success(guid);
              }
          }
      });













    new EventChannel(getFlutterView(), BATTERY_CHANNEL).setStreamHandler(
            new EventChannel.StreamHandler() {
                private BroadcastReceiver chargingStateChangeReceiver;
              @Override
              public void onListen(Object args, final EventChannel.EventSink events) {
                events.success("哈哈老爸老爸你要去哪里呀");
                  chargingStateChangeReceiver = createChargingStateChangeReceiver(events);
                  registerReceiver(
                          chargingStateChangeReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
               // Log.w(TAG, "adding listener");
              }

              @Override
              public void onCancel(Object args) {
                  unregisterReceiver(chargingStateChangeReceiver);
                  chargingStateChangeReceiver = null;
               // Log.w(TAG, "cancelling listener");
              }
            }
    );
  }
    public void getGuid(){
        new Thread(new Runnable() {
            String advertisingId;
            public void run() {
                try {
                    AdvertisingIdClient.AdInfo adInfo = AdvertisingIdClient
                            .getAdvertisingIdInfo(MainActivity.this);
                    advertisingId = adInfo.getId();
                    guid = advertisingId;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private BroadcastReceiver createChargingStateChangeReceiver(final EventChannel.EventSink events) {
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                try {
                    Bundle extras = intent.getExtras();
                    if (extras != null) {
                        String referrer = extras.getString("referrer");
                        events.success(referrer);
                    }else{}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
  }





