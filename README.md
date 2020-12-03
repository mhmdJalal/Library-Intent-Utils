# Library-Intent-Utils
[![Jitpack.io](https://jitpack.io/v/mhmdJalal/Library-Intent-Utils.svg)](https://jitpack.io/#mhmdJalal/Library-Intent-Utils)

This library is inspired by Jetbrains Anko Commons

## Change Log v0.2.0
- Improvements
- Added a startActivity that can be run on an activity or fragment

## Setup
#### > Maven
**Step 1.** Add the JitPack repository to your build file
```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
**Step 2.** Add the dependency
```
<dependency>
    <groupId>com.github.mhmdJalal</groupId>
    <artifactId>Library-Intent-Utils</artifactId>
    <version>Tag</version>
</dependency>
```

#### > Gradle
**Step 1.** Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
**Step 2.** Add the dependency
```
dependencies {
    implementation 'com.github.mhmdJalal:Library-Intent-Utils:release_version'
}
```
You can change the release_version to the latest version ```0.2.0```

## Examples of Use

#### 1. startActivityForResult *by* passing data
```
 val params = arrayListOf<Pair<String, Any?>>()
 params.add(Pair("key", "value"))

 startActivityForResult<Activity>(params) { result ->
    val data = result.data
    if (result.resultCode == RESULT_OK) {
        // result success
    } else {
        // result failed
    }
 }
```

#### 2. startActivityForResult *without* passing data
```
 startActivityForResult<Activity> { result ->
     val data = result.data
     if (result.resultCode == RESULT_OK) {
       	// result success
     } else {
         // result failed
     }
 }
```

#### 3. startActivity in an activity *without* passing data
```
 startActivity<Activity>()
```

#### 4. startActivity in an activity *by* passing data (type 1)
```
 startActivity<Activity>("key" to "value")
```

#### 5. startActivity in an activity *by* passing data (type 2)
```
 val params = arrayListOf<Pair<String, Any?>>()
 params.add(Pair("key", "value"))
 
 startActivity<Activity>(params)
``````

#### 6. startActivityFromFragment in an fragment *without* passing data
```
 startActivityFromFragment<Activity>()
```

#### 7. startActivityFromFragment in an fragment *by* passing data (type 1)
```
 startActivityFromFragment<Activity>("key" to "value")
```

#### 8. startActivityFromFragment in an fragment *by* passing data (type 2)
```
 val params = arrayListOf<Pair<String, Any?>>()
 params.add(Pair("key", "value"))
 
 startActivityFromFragment<Activity>(params)
```

## LICENCE
Apache License 2.0
```
Copyright 2020 Muhamad Jalaludin

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```