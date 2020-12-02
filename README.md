# Library-Intent-Utils

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
