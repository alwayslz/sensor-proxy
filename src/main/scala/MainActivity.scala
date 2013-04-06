package cs.luc.edu


import _root_.android.app.Activity
import _root_.android.os.Bundle
import android.widget.TextView
import android.view.View
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.client.methods.HttpGet
import java.net.URL

class MainActivity extends Activity with TypedActivity {

  lazy val textview = findView(TR.textview)
  lazy val result = findView(TR.result)

  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.main)
    findView(TR.textview).setText("hello, world!")
  }

  def onSubmit(view: View) {
    val url = new URL(findView(TR.URL).getText().toString())

    val remoteTask = new RemoteTask(result);
    remoteTask.execute(url)
  }

}
