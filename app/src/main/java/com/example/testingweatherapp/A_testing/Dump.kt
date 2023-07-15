package com.example.testingweatherapp.A_testing

class dump {
    /*val API_KEY = "2ebf9aea9a1e493ca2b20050230707"
    val location = "Cebu"
    val days = 1
    val aqi = "no"
    val alerts = "no"



    val call = forecastDataAPI.getForecastData(API_KEY, location, days, aqi, alerts)
    call.enqueue(object: Callback<ForecastData.forecastData!> {
        override fun onResponse(call: Call<ForecastData.forecastData>, response: Response<ForecastData.forecastData>){
            if(response.isSuccesful){
                Log.d("this", response.body())
            }else{
                Log.d("this", "Error or smthn")
            }
        }

        override fun onFailure(call: Call<ForecastData.forecastData>, t: Throwable){
            Log.d("this", "You failed")
        }
    })*/

    /*val quotesApi = RetrofitHelper.getInstance().create(ForecastDataAPI::class.java)
    Log.d("This", "Something was done here")
    // launching a new coroutine
    GlobalScope.launch {
        val result = quotesApi.getForecastData(
            apiKey = "2ebf9aea9a1e493ca2b20050230707",
            location = "Cebu",
            days = 1,
            aqi = "no",
            alerts = "no"
        )
        if(result.isSuccessful){
            Log.d("this", result.body().toString())
        }else{
            Log.d("this", "you failed home boy")
        }

    }



    Log.d("this", "Went through that. not happy")*/



    /*fun apiCall2(location: String, days: Int): ForecastData.forecastData?{

        var forecastData: ForecastData.forecastData?= null
        val forecastdataApi = RetrofitHelper.getInstance().create(ApiServices::class.java)
        Log.d("This", "Something was done here")
        // launching a new coroutine
        val result = forecastdataApi.getForecastData(
            apiKey = Constants.apiKey,
            location = location,
            days = days,
            aqi = Constants.aqi,
            alerts = Constants.alerts
        )

        Log.d("This", "before the data parse $forecastData.toString()")


        result.enqueue(object: Callback<ForecastData.forecastData>{
            override fun onResponse(call: Call<ForecastData.forecastData>, response: Response<ForecastData.forecastData>) {
                if(response.isSuccessful){
                    Log.d("this", response.body().toString())
                    Log.d("this", response.body()!!.location.name)
                    Log.d("this", response.body()!!.location.country)
                    forecastData = response.body()
                    Log.d("This", "During data Parse $forecastData.toString()")
                }else{
                    Log.d("this", "AINT ASUCCSFSDFSEFA")
                }
            }
            override fun onFailure(call: Call<ForecastData.forecastData>, t: Throwable) {
                Log.d("this", "FAILURE!! ")
            }
        })

        Log.d("this", "Went through that. not happy")

        Log.d("This", "After the data parse $forecastData.toString()")

        return forecastData
    }*/


    /*fun apiCall(url: String){
        Log.d("This", "you are in the real world now")
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                //Log.d("This", response.body()?.string() ?: "NULL")
                val responseData = response.body()?.string()?:"NULL"

                val jsonTokener = JSONTokener(responseData)
                val data = jsonTokener.nextValue() as JSONObject

                Log.d("this", "We are inside the API CALL")
                Log.d("This", data.toString())

                forecastData = Json.decodeFromString<ForecastData.forecastData>(data.toString())
                Log.d("this", forecastData.toString())
                Log.d("this", forecastData!!.location.name)


                val location = Json.decodeFromString<ForecastData.location>(data.getJSONObject("location").toString())
                Log.d("this", location.toString())
                val current = Json.decodeFromString<ForecastData.current>(data.getJSONObject("current").toString())
                Log.d("this", current.toString())
                val forecast = Json.decodeFromString<ForecastData.forecast>(data.getJSONObject("forecast").toString())
                //Log.d("this", data.getJSONObject("forecast").getJSONArray("forecastday").toString())
                Log.d("this", forecast.toString())

            }

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.d("this", "you failed bitch")
                Log.d("This", e.toString())
            }

        })
    }*/

    /*val display: List<Any> = listOf(
            weather_Data?.location?.name?:"No data",
            weather_Data?.location?.country?:"No data",
            weather_Data?.current?.condition?.text?:"No data",
            weather_Data?.current?.condition?.icon?:""
        )*/

    /*override fun onFinished(weather_Data: ForecastData.forecastData?) {
        val display: List<Any> = listOf(
            weather_Data?.location?.name?:"No data",
            weather_Data?.location?.country?:"No data",
            weather_Data?.current?.condition?.text?:"No data",
            weather_Data?.current?.condition?.icon?:""
        )

        mainView.display(display)
    }*/

   /* val locations = arrayOf("Cebu", "Bohol", "Dumagetme", "Argao", "DD")

    val adapter = CustomAdapter(locations)

    location_result.adapter = adapter

    location_result.layoutManager = LinearLayoutManager(this)

    val sec = arrayOf("Dionn", "Panpan", "Mingky", "Caturay", "Josephine")

    val nadapter = CustomAdapter(sec)

    location_result.swapAdapter(nadapter, false)

    location_result.layoutManager = LinearLayoutManager(this)*/



}