package cs.luc.edu

import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.UnsupportedEncodingException
import scala.util.parsing.json.JSONObject
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.ClientProtocolException
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.DefaultHttpClient
import java.io.BufferedReader
import scala.util.parsing.json.JSONObject
import org.json.JSONException
import scala.util.parsing.json.JSONObject
import scala.util.parsing.json._

class JSONParser {

  var is: InputStream = null
  var jObj: JSONObject = null
  var json: String = ""

  def JSONParser() {}

  def getJSONFromUrl(url: String): String = {

    //Make http request
    try {
      val httpClient: DefaultHttpClient = new DefaultHttpClient()
      val httpPost: HttpPost = new HttpPost(url)
      val httpResponse: HttpResponse = httpClient.execute(httpPost)
      val httpEntity: HttpEntity = httpResponse.getEntity()
      is = httpEntity.getContent()
    } catch {
      case e: UnsupportedEncodingException => e.printStackTrace()
      case e1: ClientProtocolException => e1.printStackTrace()
      case e2: IOException => e2.printStackTrace()
    }

    try {

      val reader: BufferedReader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8)
      val sb: StringBuilder = new StringBuilder()
      val line: String = null

      val iterator = Iterator.continually(reader.readLine()).takeWhile(_ != null)

      is.close()
       json = sb.toString()
    } catch {
      case e: Exception => e.printStackTrace()
    }

    //try parse the string  to a JSON object
      
json    
     

    

  }
}