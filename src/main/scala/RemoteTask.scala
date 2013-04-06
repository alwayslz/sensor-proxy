package cs.luc.edu

import java.net.URL
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import android.graphics.Color
import android.os.AsyncTask
import android.widget.{ TextView }
import org.apache.http.util.EntityUtils
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.InputStream
import org.apache.http.HttpEntity
import org.apache.http.client.methods.HttpPost
import android.util.Log
import org.apache.http.client.ClientProtocolException
import java.io.UnsupportedEncodingException
import java.io.IOException
import android.util.Log
import scala.util.parsing.json.JSON._
/**
 * Background task for checking remotely whether a number is prime.
 * Expects at the given URL a suitable cloud service such as an instance of
 * https://github.com/webservices-cs-luc-edu/primenumbers-spray-scala
 *
 * Using AnyRef instead of Long as the type parameter to work around
 * a problem with Android not finding our implementation of doInBackground.
 */
class RemoteTask(textview: TextView)
  extends AsyncTask[AnyRef, Void, Boolean] {

  private var request: HttpGet = null
  private var result: String = null
  var is: InputStream = null
  var json: String = ""
  var download: String = "empty"

  override protected def onPreExecute() {
  }

  override protected def doInBackground(params: AnyRef*): Boolean = {
    require { params.length == 1 }
    val url = params(0).asInstanceOf[URL]
    val httpClient: DefaultHttpClient = new DefaultHttpClient()
    val request: HttpGet = new HttpGet(url.toString())
    request.setHeader("Accept", "application/json")
    val httpResponse: HttpResponse = httpClient.execute(request)
    val httpEntity: HttpEntity = httpResponse.getEntity()
    //      is = httpEntity.getContent()
    val status = httpResponse.getStatusLine.getStatusCode
    result = EntityUtils.toString(httpEntity)
    if (status == 200)
      true
    else if (status == 404)
      false
    else
      throw new RuntimeException("unexpected server response")

//    try {
//
//    } catch {
//      case e: UnsupportedEncodingException => e.printStackTrace()
//      case e1: ClientProtocolException => e1.printStackTrace()
//      case e2: IOException => e2.printStackTrace()
//    }
//
//    try {
//
//      val reader: BufferedReader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8)
//      val sb: StringBuilder = new StringBuilder()
//      val line: String = null
//
//      val iterator = Iterator.continually(sb.append(reader.readLine())).takeWhile(_ != null)
//
//      is.close()
//      json = sb.toString()
//      Log.e("reader", json)
//    } catch {
//      case e: Exception => e.printStackTrace()
//    }


  }

  override protected def onPostExecute(result: Boolean) {

   
    val jsonParsed =parseFull(this.result).get
    //    if (result) textview.setText(this.result)
    if (result) textview.setText(jsonParsed.toString())
    
    else textview.setText("Failed")	

  }

}
