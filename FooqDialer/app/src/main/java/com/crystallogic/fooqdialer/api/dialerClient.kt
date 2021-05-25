package com.crystallogic.deliveryboyapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://application.dawa.com/api/"

     class dialerClient {
//        private val client = OkHttpClient.Builder().build()
//
//        private val retrofit = Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build()
//
//        fun<T> buildService(service: Class<T>): T{
//            return retrofit.create(service)
//        }

         private lateinit var apiService: dialerApi

         fun getApiService(): dialerApi {

             // Initialize ApiService if not initialized yet
             if (!::apiService.isInitialized) {
                 val retrofit = Retrofit.Builder()
                         .baseUrl(BASE_URL)
                         .addConverterFactory(GsonConverterFactory.create())
                         .build()

                 apiService = retrofit.create(dialerApi::class.java)
             }

             return apiService
         }

     }
