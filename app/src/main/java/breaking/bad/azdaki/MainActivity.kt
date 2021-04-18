package breaking.bad.azdaki

import android.os.Bundle
import breaking.bad.azdaki.base.LanguageAwareActivity
import breaking.bad.azdaki.databinding.MainActivityBinding
import breaking.bad.azdaki.ui.login.LoginFragment

class MainActivity: LanguageAwareActivity() {

    private val binding: MainActivityBinding by lazy { MainActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }


}