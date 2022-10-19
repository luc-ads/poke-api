package first.application.by.bentsappfrontend.activities

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import first.application.by.bentsappfrontend.databinding.ActivityWebViewAboutUsBinding

class WebViewAboutUsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {

            binding.toolbar.setOnClickListener {
                finish()
            }
//
//            layoutWebView.webViewClient = object : WebViewClient() {
//                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//                    view.loadUrl(url)
//                    return true
//                }
//
//                override fun onPageFinished(view: WebView, url: String) {
//                    layoutWebView.visibility = View.GONE
//                }
//
//                override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
//                    layoutWebView.visibility = View.GONE
//                }
//            }

            binding.layoutWebView.loadUrl("http://benteats.vercel.app/")
            finish()
        }
    }
}