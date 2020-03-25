import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
static const EventChannel eventChannel = const EventChannel('samples.flutter.io/charging');
  @override
  void initState(){
    super.initState();
    eventChannel.receiveBroadcastStream().listen(_onEvent, onError: _onError);
  }
void _onEvent(Object event) {
  setState(() {
   print("Battery status: ${event == 'charging' ? '' : 'dis'}charging.");
  });
}

void _onError(Object error) {
  setState(() {
  // print("Battery status: ${event == 'charging' ? '' : 'dis'}charging.");
  });
}
  @override
  Widget build(BuildContext context) {
    return Scaffold(
    appBar: AppBar(
      centerTitle: true,
      title: Text('首页'),
    ),
    );
  }
}
