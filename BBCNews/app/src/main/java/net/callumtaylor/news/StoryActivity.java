package net.callumtaylor.news;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import net.callumtaylor.model.Story;

public class StoryActivity extends Activity
{
	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.story_view);

		if (getIntent().getExtras() != null)
		{
			Story story = (Story)getIntent().getExtras().get("story");

			WebView webview = (WebView)findViewById(R.id.web_view);
			webview.getSettings().setJavaScriptEnabled(true);
			webview.setWebViewClient(new WebViewClient()
			{
				@Override public boolean shouldOverrideUrlLoading(WebView view, String url)
				{
					view.loadUrl(url);
					return true;
				}

				@Override public void onPageFinished(WebView view, String url)
				{
					super.onPageFinished(view, url);
					view.loadUrl("javascript:document.getElementById('orb-banner').style.display = 'none'; document.getElementsByClassName('site-brand')[0].style.display = 'none'; document.getElementsByClassName('secondary-navigation')[0].style.display = 'none';");
				}
			});

			webview.loadUrl(story.getLink());
		}
	}
}
