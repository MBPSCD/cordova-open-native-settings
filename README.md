# NativeSettings plugin for Cordova (6/7)

The plugin allows you to open OS settings on iOS 8/9/10 and Android, via cordova-based app. For example, it will allow you to open the keyboard settings, Wifi, bluetooth etc (full list below).

### Example for iOS and Android - open app settings

```js
cordova.settings.open().then(() => {
    console.log('Can Opened App Setting');
}, () => {
    console.log('Cannot Opened App Setting');
});
```
