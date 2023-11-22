package cat.dam.andy.menulayouts

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private lateinit var layout1: View
    private lateinit var layout2: View
    private lateinit var layout3: View
    private lateinit var layout4: View
    private var selectedLayoutId: Int = R.id.layout1 // Initially selected layout ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initMenu()
    }

    private fun initMenu() {
        // Initialize layouts here
        layout1 = layoutInflater.inflate(R.layout.layout1, null)
        layout2 = layoutInflater.inflate(R.layout.layout2, null)
        layout3 = layoutInflater.inflate(R.layout.layout3, null)
        layout4 = layoutInflater.inflate(R.layout.layout4, null)

        val btn1 = findViewById<Button>(R.id.btnLayout1)
        val btn2 = findViewById<Button>(R.id.btnLayout2)
        val btn3 = findViewById<Button>(R.id.btnLayout3)
        val btn4 = findViewById<Button>(R.id.btnLayout4)

        btn1.setOnClickListener {
            selectedLayoutId = R.id.layout1
            setContent(layout1)
        }
        btn2.setOnClickListener {
            selectedLayoutId = R.id.layout2
            setContent(layout2)
        }
        btn3.setOnClickListener {
            selectedLayoutId = R.id.layout3
            setContent(layout3)
        }
        btn4.setOnClickListener {
            selectedLayoutId = R.id.layout4
            setContent(layout4)
        }

        // Show the first layout by default
        setContent(layout1)
    }

    private fun setContent(layout: View) {
        val frameLayout = findViewById<FrameLayout>(R.id.frameLayout)
        frameLayout.removeAllViews()
        frameLayout.addView(layout)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Save the selected layout ID
        outState.putInt("selectedLayoutId", selectedLayoutId)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        // Restore the selected layout ID
        selectedLayoutId = savedInstanceState.getInt("selectedLayoutId")

        // Restore the layout
        setContent(getLayoutById(selectedLayoutId))
    }

    private fun getLayoutById(id: Int): View {
        return when (id) {
            R.id.layout1 -> layout1
            R.id.layout2 -> layout2
            R.id.layout3 -> layout3
            else -> layout4
        }
    }
}
