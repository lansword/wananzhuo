package com.sword.wanandroid

import android.content.Context
import android.net.ParseException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.google.gson.JsonParseException
import com.sword.wanandroid.api.ApiRetrofit
import com.sword.wanandroid.modele.BaseResponse
import com.sword.wanandroid.updater.AppUpdater
import com.sword.wanandroid.updater.net.INetCallBack
import com.sword.wanandroid.updater.net.INetDownLoadCallBack
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import retrofit2.HttpException
import java.io.File
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    val url = "http://59.110.162.30/app_updater_version.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        tet.setOnClickListener {


            //            ApiRetrofit.mInstance.getArticleList(1)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    //                    LogUtils.d(it.data)
//                    LogUtils.d(it.data.toString())
//                }, {
//                    ToastUtils.showShort(it.message)
//                    when (it) {
//                        is NullPointerException -> ToastUtils.showShort("接口挂了")
//                        is HttpException -> ToastUtils.showShort("http错误")
//                        is ConnectException -> ToastUtils.showShort("连接错误")
//                        is UnknownHostException -> ToastUtils.showShort("找不到主机")
//                        is InterruptedException -> ToastUtils.showShort("连接超时")
//                        is SocketTimeoutException -> ToastUtils.showShort("请求超时")
//                        is JsonParseException, is JSONException, is ParseException -> ToastUtils.showShort(
//                            "解析错误"
//                        )
//                        else -> ToastUtils.showShort("接口挂了")
//                    }
//                })


            AppUpdater.getInstance().getiNetManager().get(url, object : INetCallBack {
                override fun success(response: String) {
                    //                1、解析json
                    //                2、版本比较
                    //                3、弹框
                    //                4、点击下载
                    Log.d("llll", response)


                    val targetFile: File = File(cacheDir, "target.apk")
                    AppUpdater.getInstance().getiNetManager().downLoad("", targetFile, object :
                        INetDownLoadCallBack {
                        override fun success(apkFile: File) {
                            //安装
                        }

                        override fun progress(progress: Int) {
                            //显示进度
                        }

                        override fun failed(throwable: Throwable) {

                        }
                    })
                }

                override fun failed(throwable: Throwable) {
                    throwable.printStackTrace()
                    Toast.makeText(this@MainActivity, "版本更新接口请求失败", Toast.LENGTH_SHORT).show()
                }
            })

        }

    }
}
