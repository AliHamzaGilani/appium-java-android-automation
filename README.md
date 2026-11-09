# Appium Java Android Automation

![Android Appium Tests](https://github.com/AliHamzaGilani/appium-java-android-automation/actions/workflows/android.yml/badge.svg)

Android test framework for the public [Sauce Labs sample app](https://github.com/saucelabs/sample-app-mobile), built with **Appium 2 (UiAutomator2)**, **Java 17**, **TestNG** and **AssertJ**.

## Highlights

- **Screen Object Model** with a shared `BaseScreen` for waits and actions
- **Thread-safe `DriverFactory`** using `UiAutomator2Options`
- Accessibility-id locators first; XPath only where the app has no id
- **Data-driven** login tests with a TestNG `@DataProvider`
- **Smoke and regression suites** selected by TestNG groups
- Screenshots captured on failure
- **GitHub Actions** boots an Android emulator, starts Appium and runs the suite: smoke on push and PR, full regression nightly

## Project structure

```
src/test/java/com/alihamza/appium/
  core/      Config, DriverFactory
  screens/   BaseScreen, LoginScreen, ProductsScreen
  tests/     BaseTest, LoginTest, CartTest
scripts/download-app.sh       fetches the sample APK
src/test/resources/suites/    smoke.xml, regression.xml
```

## Run it locally

Requires Java 17+, Maven, Node.js, Android SDK and a running emulator or device.

```bash
npm install -g appium
appium driver install uiautomator2
appium                                   # in a separate terminal

bash scripts/download-app.sh             # saves apps/sauce-labs-sample.apk
mvn test                                 # full regression
mvn test -Dsuite=smoke
mvn test -Dudid=emulator-5554 -DappiumUrl=http://127.0.0.1:4723
```

## Test coverage

| Area | Scenarios |
| --- | --- |
| Login | Valid login, locked-out user, wrong password, empty username |
| Cart | Add item updates badge, remove item clears badge |
