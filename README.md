# youtube_data_api
## 주의사항

##### Repository.kt 부분
```kotlin
     private fun setMap(searchText: String): HashMap<String, String> {
        val map = HashMap<String, String>()
        map["part"] = "snippet"
        map["q"] = searchText
        map["key"] = ApplicationContext.getContext().resources.getString(R.string.api_key)
        return map
    }
```

##### 에서 key 부분만 추가하고 사용하면 된다.
요청할 때 추가조건 사항은 맵에 넣어서 요청 날리면 됩니다
