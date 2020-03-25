#import "FlutterSpecilPlugin.h"
#if __has_include(<flutter_specil/flutter_specil-Swift.h>)
#import <flutter_specil/flutter_specil-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutter_specil-Swift.h"
#endif

@implementation FlutterSpecilPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterSpecilPlugin registerWithRegistrar:registrar];
}
@end
