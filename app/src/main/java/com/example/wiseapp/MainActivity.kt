package com.example.wiseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun initData() {
        // remoteConfig 설정
        val remoteConfig = Firebase.remoteConfig

        // 비동기로 설정되게
        remoteConfig.setConfigSettingsAsync(
                remoteConfigSettings {
                    minimumFetchIntervalInSeconds = 0 // 앱을 들어올 때마다 패치 하도록.
                }
        )

        remoteConfig.fetchAndActivate().addOnCompleteListener {
            // 패치 작업이 완료 된 경우
            progressBar.visibility = View.GONE // 로딩창 숨김

            if (it.isSuccessful) {
                // json 을 파싱하여 배열로 가져옴.
                val quotes: List<Quote> = parseQuotesJson(remoteConfig.getString("quote"))
                val isNameRevealed: Boolean = remoteConfig.getBoolean("is_name_revealed")

                displayQuotesPager(quotes, isNameRevealed)

            }
        }
    }
}