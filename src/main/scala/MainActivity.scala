package cs.luc.edu

import java.net.URL

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter

class MainActivity extends Activity with TypedActivity {

  lazy val test = findView(TR.test)
  lazy val result = findView(TR.result)
  lazy val spinner = findView(TR.spinner)
  lazy val configuration = findView(TR.configuration)
  var request = "get"

  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.main)
    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      override def onItemSelected(parent: AdapterView[_], view: View, pos: Int, id: Long) {
        request = parent.getItemAtPosition(pos).toString()
        test.setText("Request: " + request)

      }
      override def onNothingSelected(parent: AdapterView[_]) = {}

    })
  }

  def onSubmit(view: View) {
    val url = new URL(findView(TR.URL).getText().toString())

    val remoteTask = new RemoteTask(result)
    remoteTask.execute(url, request, configuration.getText().toString())
    //    remoteTask.execute(url)

  }

}
