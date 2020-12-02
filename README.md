# Library-Intent-Utils

### How to install
Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```
dependencies {
     implementation 'com.github.mhmdJalal:Library-Intent-Utils:release_version'
}
```
You can change the release version to 0.1.0

### Example Usage
```
 val params = arrayListOf<Pair<String, Any?>>()
 params.add(Pair("key", "value"))

 startActivityForResult<Activity>(params) {
     if (it.resultCode == RESULT_OK) {
         // result success
     } else {
         // result failed
     }
 }
```
