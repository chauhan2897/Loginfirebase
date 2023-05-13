package com.example.loginfirebase

import android.app.Activity
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONArray
import org.json.JSONObject

class Detail :Activity(), PaymentResultListener {
    lateinit var text5:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        var imageView: ImageView =findViewById(R.id.image2)
        var intent: Intent = getIntent()
        var text5:TextView=findViewById(R.id.text5)
        var text4:TextView=findViewById(R.id.text4)


        var data="{\"college\":[{\"name\":\"Pizza\",\"id\":\"101\"},{\"name\":\"Pancake\",\"id\":\"102\"}," +
                "{\"name\":\"Pastry\",\"id\":\"103\"},{\"name\":\"kbaab\",\"id\":\"104\"},{\"name\":\"Toast\",\"id\":\"105\"},{\"name\":\"Egg\",\"id\":\"106\"},{\"name\":\"Chowmin\",\"id\":\"107\"}]}"
        var jsonObject:JSONObject= JSONObject(data)
        var jsonArray: JSONArray =jsonObject.getJSONArray("college")
        var cdata=""
        for (i in 0..jsonArray.length()-1)
        {
            jsonObject=jsonArray.getJSONObject(i)
            var name=jsonObject.getString("name")
            var id=jsonObject.getInt("id")
            cdata+="\nname:"+name+"\n"+"id:"+ id
        }
        text4.setText(cdata)

        var position=intent.getIntExtra("image",0)
        imageView.setImageResource(position)
        var button7:Button=findViewById(R.id.button7)
        button7.setOnClickListener {
            var intent:Intent= Intent(ACTION_VIEW)
            intent.setData(Uri.parse("https://rzp.io/i/cXTcvEH"))
            startActivity(intent)
        }
        var button6:Button=findViewById(R.id.button6)
        Checkout.preload(applicationContext)
        button6.setOnClickListener {
            startPayment()
        }
    }

    private fun startPayment() {
            val activity: Activity = this
            val co = Checkout()

            try {
                val options = JSONObject()
                options.put("name","jaddu")
                options.put("description","Demoing Charges")
                //You can omit the image option to fetch the image from dashboard
                options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
                options.put("theme.color", "#3399cc");
                options.put("currency","INR");
                options.put("order_id", "order_DBJOWzybf0sJbb");
                options.put("amount","500")//pass amount in currency subunits

                val retryObj = JSONObject();
                retryObj.put("enabled", true);
                retryObj.put("max_count", 4);
                options.put("retry", retryObj);

                val prefill = JSONObject()
                prefill.put("email","devilll2897@gmail.com")
                prefill.put("contact","8130523899")

                options.put("prefill",prefill)
                co.open(activity,options)
            }catch (e: Exception){
                Toast.makeText(activity,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
        }

    override fun onPaymentSuccess(p0: String?) {
        text5.setText("payment successfull")
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        text5.setText("payment error")
    }
}
