#!/usr/bin/env bash
# Downloads the public Sauce Labs sample Android app used by the tests.
set -euo pipefail

APP_VERSION="2.7.1"
APP_URL="https://github.com/saucelabs/sample-app-mobile/releases/download/${APP_VERSION}/Android.SauceLabs.Mobile.Sample.app.${APP_VERSION}.apk"

mkdir -p apps
curl -fL "$APP_URL" -o apps/sauce-labs-sample.apk
echo "Saved apps/sauce-labs-sample.apk"
