package com.android.goally.data.network.rest

import android.content.Context
import com.android.goally.data.network.rest.Headers.TOKEN
import com.android.goally.data.repo.GeneralRepo
import com.android.goally.util.AppUtil
import com.android.goally.util.NoInternetException
import com.android.goally.util.PreferenceUtil
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkConnectionInterceptor(
    context: Context
) : Interceptor {

    private val applicationContext = context.applicationContext

    @Throws(NoInternetException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        if (!AppUtil.isInternetAvailable(applicationContext)) throw NoInternetException(com.android.goally.constants.AppConstant.INTERNET_ERROR)


        val token = PreferenceUtil(applicationContext).getString("token", null)

        request = if (token == null) {
            request.newBuilder().addHeader(
                "Accept", "application/json"
            )
                .addHeader("Content-Type", "application/json").build()
        } else {
            request.newBuilder().addHeader(
                "Accept", "application/json"
            )
                .addHeader("Content-Type", "application/json")
                .addHeader(TOKEN, token).build()
        }
        return chain.proceed(request)
    }


}