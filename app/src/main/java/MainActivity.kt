import android.content.Intent
import java.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), DroidListFragment.IListener {
    companion object {
        const val EXTRAS_DROID = "DROID"

        const val TAG_DETAILS = "DETAILS"
        const val TAG_DETAILS_DIALOG = "DETAILS_DIALOG"

        const val DEFAULT_DROID_INDEX = 0


        // Вариант кода, для android:launchMode="singleInstance"
//        fun droidDetailsIntent(context: Context, droid: Droid) = Intent(context, MainActivity::class.java)
//                .putExtra(EXTRAS_DROID, droid)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        val isDual = resources.getBoolean(R.bool.is_dual)


        // Проверяем что эта Activity не имеет сохраненного стейта и вставляем свой фрагмент
        // Если стейт есть, тогда фрагмент будет восстановлен без нашего участия
        if (savedInstanceState == null) {
            if (isDual) {
                // У нас двухпанельный режим, устанавливаем детали по дефолту
                val droid = DroidRepository.instance.item(DEFAULT_DROID_INDEX)
                showDetails(droid)
            }
        } else {
            // Произошло восстановление Activity savedInstanceState
            // Убедимся, что у нас в текущей конфигурации нет лишних объектов
            checkDetails(isDual)
        }
    }


    // Activity имеет специальный флаг в Manifest launchMode="singleInstance", поэтому startActivity пришел сюда
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        // Вариант кода, для android:launchMode="singleInstance"
//        val droid = intent.getSerializableExtra(EXTRAS_DROID) as? Droid
//        showDetails(droid)
    }


    // Вариант кода, для общения с activity без Intent
    override fun onDroidClicked(droid: Droid) {
        showDetails(droid)
    }


    protected fun showDetails(droid: Droid?) {
        if (droid == null) {
            // просто проверка. Непонятно как сюда попали. По хорошему надо залогировать
            // Но, в рамках примера, игнорируем исполнение
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

    protected fun checkDetails(isDual: Boolean) {
        when (isDual) {
            true -> {
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
            }

            false -> {
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
}