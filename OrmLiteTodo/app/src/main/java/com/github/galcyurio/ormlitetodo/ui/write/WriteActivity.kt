package com.github.galcyurio.ormlitetodo.ui.write

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.github.galcyurio.ormlitetodo.R
import com.github.galcyurio.ormlitetodo.misc.DatabaseHelper
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.j256.ormlite.android.apptools.OpenHelperManager
import kotlinx.android.synthetic.main.activity_write.*
import org.jetbrains.anko.toast

class WriteActivity : MvpActivity<WriteContract.View, WriteContract.Presenter>(), WriteContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)
    }

    override fun onDestroy() {
        super.onDestroy()
        OpenHelperManager.releaseHelper()
    }

    override fun createPresenter(): WriteContract.Presenter {
        /* TODO: kodein 을 통해 의존성 주입 */
        val taskDao = OpenHelperManager.getHelper(this, DatabaseHelper::class.java).taskDao
        return WritePresenter(taskDao)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        title = "글쓰기"
        menuInflater.inflate(R.menu.menu_write, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.complete -> presenter.write(txtTitle.text.toString(), txtDescription.text.toString())
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showTitleEmptyMessage() {
        toast("타이틀을 입력해주세요")
    }

    override fun showErrorMessage() {
        toast("에러 발생")
    }

    override fun close() {
        finish()
    }

}
