package com.sideproject.productreviewer

import android.util.Log

class DAO {

    fun searchByCode(code : String){


        //todo DB에서 코드로 제품 조회 로직
        if(code == "8801121103661"){
            Log.d("DAO", "우유속에 코코아")
            var product = ProductInfo("우유속에 코코아", "초코우유", 5)
            //todo 등록된 제품 리뷰 확인 or 작성 로직
            return
        }

        //todo 미등록 제품 등록 로직
         Log.d("DAO", "등록안된 제품")

    }

}