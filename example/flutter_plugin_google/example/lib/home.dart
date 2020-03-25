import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_plugin_google/flutter_plugin_google.dart';

class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  static const EventChannel eventChannel =const EventChannel('com.meetyou.flutter/event', const StandardMethodCodec());
  @override
  void initState(){
    super.initState();
    eventChannel.receiveBroadcastStream().listen(_onEvent, onError: _onError);
    getGuId();
  }
  
  getGuId() async{
    print(await FlutterPluginGoogle.getGuId);
  }

  void _onEvent(Object event) {
    setState(() {
     print("Battery status: ${event}");
    });
  }

  void _onError(Object error) {
    setState(() {
      //_chargingStatus = 'Battery status: unknown.';
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        child:Text(
          '乖宝宝'
        )
      ),
    );
  }
}
