package com.cengiztoru.kotlinexample

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {


    private val requestSendSms  = 2;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSend.setOnClickListener{



            if(message.text.trim().length>0 &&
                number.text.trim().length>0 &&
                total.text.trim().length>0  ){

                if(ActivityCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){

                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS),requestSendSms)
                }
                else {
                    sendSms();
                }


            }
            else{
                Toast.makeText(this,"VERİLERİ GİRİNİZ. ",Toast.LENGTH_SHORT).show();

            }

        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode==requestSendSms)
            sendSms();
    }

    private fun sendSms() {

        val message = message.text.toString();
        val number = number.text.toString();
        var total: Int = total.text.toString().toInt();

        for (i in 0 until total){
            SmsManager.getDefault().sendTextMessage(number,null,message,null,null)
        }

        Toast.makeText(this,"SENDED " + total,Toast.LENGTH_SHORT).show();

    }
}

