package com.github.galcyurio.shot

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.test.platform.app.InstrumentationRegistry
import com.github.galcyurio.shot.databinding.ItemMemoBinding
import com.karumi.shot.ScreenshotTest
import org.junit.Test

class ItemMemoTest : ScreenshotTest {
    @Test
    fun test1() {
        val view = createView()
        view.findViewById<TextView>(R.id.tv_title).text = "foo"
        view.findViewById<TextView>(R.id.tv_content).text = "bar"
        compareScreenshot(view)
    }

    @Test
    fun test2() {
        runOnUi {
            val binding = createBinding()
            binding.title = "foo"
            binding.content = "bar"
            compareScreenshot(binding.root)
        }
    }

    //region Fixture
    private fun createView(): View {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val inflater = LayoutInflater.from(context)
        return inflater.inflate(R.layout.item_memo, null, false)
    }

    private fun createBinding(): ItemMemoBinding {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        return ItemMemoBinding.inflate(LayoutInflater.from(context))
    }
    //endregion
}
