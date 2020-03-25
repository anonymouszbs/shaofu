package com.xspp.flutter_pluginspecial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;

import androidx.annotation.NonNull;

import io.flutter.Log;
import io.flutter.app.FlutterActivity;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** FlutterPluginspecialPlugin */
//public class FlutterPluginspecialPlugin implements FlutterPlugin, MethodCallHandler {
//  @Override
//  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
//    final MethodChannel channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "flutter_pluginspecial");
//    channel.setMethodCallHandler(new FlutterPluginspecialPlugin());
//  }
//
//
//  // This static function is optional and equivalent to onAttachedToEngine. It supports the old
//  // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
//  // plugin registration via this function while apps migrate to use the new Android APIs
//  // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
//  //
//  // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
//  // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
//  // depending on the user's project. onAttachedToEngine or registerWith must both be defined
//  // in the same class.
//  public static void registerWith(Registrar registrar) {
//    final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_pluginspecial");
//    channel.setMethodCallHandler(new FlutterPluginspecialPlugin());
//  }
//
//  @Override
//  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
//    if (call.method.equals("getPlatformVersion")) {
//      result.success("Android " + android.os.Build.VERSION.RELEASE);
//    } else {
//      result.notImplemented();
//    }
//  }
//
//  @Override
//  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
//  }
//}


