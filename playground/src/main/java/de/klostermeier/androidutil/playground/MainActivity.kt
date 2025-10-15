@file:OptIn(ExperimentalMaterial3Api::class)

package de.klostermeier.androidutil.playground

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import de.klostermeier.androidutil.compose.DropDownSpinner
import de.klostermeier.androidutil.compose.ErrorText
import de.klostermeier.androidutil.compose.util.UiText
import de.klostermeier.androidutil.playground.ui.theme.AndroidUtilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidUtilTheme(darkTheme = false) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = stringResource(id = R.string.app_name))
                                }
                            )
                        }
                    ) { contentPadding ->
                        Column(modifier = Modifier.padding(contentPadding)) {
                            val context = LocalContext.current
                            val previewItems = listOf(
                                UiText.DynamicString("Hello "),
                                UiText.DynamicString("World"),
                                UiText.DynamicString("!!!")
                            )

                            DropDownSpinner(
                                items = previewItems,
                                onItemSelected = {
                                    showToast(
                                        context,
                                        message = if (it == DropDownSpinner.NO_ITEM_SELECTED) {
                                            "Nothing"
                                        } else {
                                            previewItems[it].asString(context)
                                        }
                                    )
                                },
                                trailingIcon = Icons.Rounded.Star,
                                iconTint = Color(0xffffd740)
                            )

                            ErrorText(error = "This is an error!")
                        }
                    }
                }
            }
        }
    }

    private fun showToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }
}