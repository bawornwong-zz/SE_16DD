package ac.th.kmitl.it.se.stamper;

import com.parse.Parse;

import android.app.Application;

public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		Parse.initialize(this, "92YN0OM8QuTx9nN5lakWaerTSO7d95mwz4xJR84T",
				"w8zG1Hdz7XF312dG8DYjaTl5fe8eXCLuo0UNn0uE");

	}
}
