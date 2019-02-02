# Tapligh Android Native Library Demo

Native ads are banner ads which have the same look and feel as the
original items of an app have. You can integrate tapligh native ads
by passing some simple steps. First of that create account from
[Tapligh panel](http://dashboard.tapligh.com/register) . Next register
your application through [this link](http://dashboard.tapligh.com/publisher/apps/new).

## Installation

1. Add Tapligh repository into your main build.gradle file of your
project like below:

```
repositories {

        maven { url  "https://dl.bintray.com/tapligh/Tapligh-SDK" }

        }
```

2. Next add tapligh native ad dependency into your app gradle file lie

```

dependencies {

    implementation 'com.tapligh.sdk:native-sdk:1.+'

}

```

## Usage

Please follow [this link](https://tapligh.com/implement-native-ads/) to
understand how to integrate tapligh native ad in your app.
