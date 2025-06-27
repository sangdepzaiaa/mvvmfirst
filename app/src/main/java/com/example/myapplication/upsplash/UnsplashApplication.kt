package com.example.myapplication.upsplash

import android.app.Application

class UnsplashApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        UnsplashServiceLocator.initwith(this)
    }

}
//3️⃣ Tại sao lại cần hàm initWith()?
//Để gom toàn bộ phần khởi tạo lại một chỗ.
//
//Dễ bảo trì.
//
//Đảm bảo khởi tạo đúng thời điểm (ngay khi app start, trong Application.onCreate()).