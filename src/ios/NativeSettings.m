#import "NativeSettings.h"

@implementation NativeSettings

- (void)open:(CDVInvokedUrlCommand*)command
{
	__block CDVPluginResult* pluginResult = nil;

    NSURL* URL = [NSURL URLWithString:UIApplicationOpenSettingsURLString];

    UIApplication *application = [UIApplication sharedApplication];

    [application openURL:URL options:@{} completionHandler:^(BOOL success) {
        if (success) {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"Opened"];
        } else {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Cannot open"];
        }

        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

@end
