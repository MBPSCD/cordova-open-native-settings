'use strict';

var NativeSettings = function () {
};

NativeSettings.open = function (success, fail) {
    return getPromisedCordovaExec('open', success, fail);
};

function getPromisedCordovaExec(command, success, fail) {
    var result = null;

    if (!success) {
        result = new Promise(function (resolve, reject) {
            success = resolve;
            fail = reject;
        });
    }

    cordova.exec(success, fail, 'NativeSettings', command);

    return result;
}

module.exports = NativeSettings;
