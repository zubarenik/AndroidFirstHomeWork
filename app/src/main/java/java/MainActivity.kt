package java

import Droid
import DroidDetailsFragment
import DroidListFragment
import DroidRepository
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), DroidListFragment.IListener {
    companion object {
        const val EXTRAS_DROID = "DROID"
        const val TAG_DETAILS = "DETAILS"
        const val TAG_DETAILS_DIALOG = "DETAILS_DIALOG"
        const val DEFAULT_DROID_INDEX = 0
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val isDual = resources.getBoolean(R.bool.is_dual)

        if (savedInstanceState == null) {
            if (isDual) {
                val droid = DroidRepository.instance.item(DEFAULT_DROID_INDEX)
                showDetails(droid)
            }
        } else {
            checkDetails(isDual)
        }
    }

    override fun onDroidClicked(droid: Droid) {
        showDetails(droid)
    }

    protected fun showDetails(droid: Droid?) {
        if (droid == null) {
            return
        }

        val detailsFragment = DroidDetailsFragment.newInstance(droid)

        val isDual = resources.getBoolean(R.bool.is_dual)
        when(isDual) {
            true -> {
                // Отображаем детали Дроида во второй панели
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details, detailsFragment, TAG_DETAILS)        // заменить фрагмент
                        .commitAllowingStateLoss()
            }

            false -> {
                // Отображаем детали Дроида, как диалог
                detailsFragment.show(supportFragmentManager, TAG_DETAILS_DIALOG)
            }
        }
    }

    private fun checkDetails(isDual: Boolean) {
        if (isDual) {
            val dialog = supportFragmentManager.findFragmentByTag(TAG_DETAILS_DIALOG) as? DroidDetailsFragment
            when (dialog == null) {
                // Диалога не оказалось в стэке
                true -> {
                    val details = supportFragmentManager.findFragmentByTag(TAG_DETAILS) as? DroidDetailsFragment

                    if (details == null) {
                        // В стэке его нет. Значит устанавливаем детали по дефолту
                        val droid = DroidRepository.instance.item(DEFAULT_DROID_INDEX)
                        showDetails(droid)
                    }
                }

                // Диалог есть в стэке
                false -> {
                    // Закрываем диалог, т.к. у нас двухпанельный режим
                    dialog.dismissAllowingStateLoss()

                    // показываем панель с тем Дроидом, который был в диалоге
                    val droid = dialog.droid()
                    showDetails(droid)
                }
            }
        } else {
            val details = supportFragmentManager.findFragmentByTag(TAG_DETAILS) as? DroidDetailsFragment
            if (details != null) {
                supportFragmentManager
                        .beginTransaction()
                        .remove(details)                // удалим фрагмент
                        .commitAllowingStateLoss()


                val droid = details.droid()
                showDetails(droid)
            }
        }
    }
}