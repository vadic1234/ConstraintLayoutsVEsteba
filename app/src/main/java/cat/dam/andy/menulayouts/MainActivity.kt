package cat.dam.andy.menulayouts

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import cat.dam.andy.menulayouts.ui.theme.AppTheme

enum class LayoutId { LAYOUT1, LAYOUT2, LAYOUT3, LAYOUT4 }

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        var selectedLayoutId by rememberSaveable { mutableStateOf(LayoutId.LAYOUT1) }

                        MyComposeButtons(onButtonClick = { selectedLayoutId = it })

                        Spacer(modifier = Modifier.height(5.dp))

                        when (selectedLayoutId) {
                            LayoutId.LAYOUT1 -> Layout1()
                            LayoutId.LAYOUT2 -> Layout2()
                            LayoutId.LAYOUT3 -> Layout3()
                            LayoutId.LAYOUT4 -> Layout4()
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun MyComposeButtons(onButtonClick: (LayoutId) -> Unit) {
        val buttonData = listOf(
            Pair(LayoutId.LAYOUT1, R.string.btn_layout1),
            Pair(LayoutId.LAYOUT2, R.string.btn_layout2),
            Pair(LayoutId.LAYOUT3, R.string.btn_layout3),
            Pair(LayoutId.LAYOUT4, R.string.btn_layout4)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            buttonData.forEach { (layoutId, stringResourceId) ->
                Button(
                    onClick = { onButtonClick(layoutId) },
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                ) {
                    Text(
                        text = stringResource(id = stringResourceId),
                    )
                }
            }
        }
    }

    @Composable
    fun Layout1() {
        LayoutContent(LayoutId.LAYOUT1)
    }

    @Composable
    fun Layout2() {
        LayoutContent(LayoutId.LAYOUT2)
    }

    @Composable
    fun Layout3() {
        LayoutContent(LayoutId.LAYOUT3)
    }

    @Composable
    fun Layout4() {
        LayoutContent(LayoutId.LAYOUT4)
    }

    @Composable
    fun LayoutContent(layoutId: LayoutId) {
        val context = LocalContext.current
        val layoutResId = when (layoutId) {
            LayoutId.LAYOUT1 -> R.layout.layout1
            LayoutId.LAYOUT2 -> R.layout.layout2
            LayoutId.LAYOUT3 -> R.layout.layout3
            LayoutId.LAYOUT4 -> R.layout.layout4
        }

        AndroidView(
            factory = { context ->
                LayoutInflater.from(context).inflate(layoutResId, null, false)
            },
            modifier = Modifier.fillMaxSize(),
            update = {
                // Update the view if needed
            }
        )
    }
}
