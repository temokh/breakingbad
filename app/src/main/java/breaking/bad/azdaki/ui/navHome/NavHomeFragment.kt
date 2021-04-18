package breaking.bad.azdaki.ui.navHome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import breaking.bad.azdaki.R
import breaking.bad.azdaki.databinding.NavHomeScreenBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavHomeFragment:Fragment(), BottomNavigationView.OnNavigationItemSelectedListener {


    private var binding: NavHomeScreenBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NavHomeScreenBinding.inflate(inflater,container,false )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.homeHavTabBar?.setOnNavigationItemSelectedListener(this)
        Navigation.findNavController(binding?.homeNavContainer!!)
                .addOnDestinationChangedListener { controller, destination, arguments ->
                    binding?.homeHavTabBar?.setOnNavigationItemSelectedListener(null)
                    binding?.homeHavTabBar?.selectedItemId = destination.id
                    binding?.homeHavTabBar?.setOnNavigationItemSelectedListener(this@NavHomeFragment)
                }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val controller = Navigation.findNavController(binding?.homeNavContainer!!)
        when(item.itemId){

            R.id.homeFragment ->{

                controller.navigate(R.id.show_home)
            }
            R.id.searchFragment ->{

                controller.navigate(R.id.show_search)

            }
            R.id.savedCardsFragment -> {
                controller.navigate(R.id.show_saved_cards)
            }
            R.id.profileFragment -> {
                controller.navigate(R.id.show_profile)
            }
        }
        return true
    }


}