package com.petopedia.app.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.petopedia.app.R
import com.petopedia.app.databinding.ActivityMainBinding
import com.petopedia.app.domain.model.PetDetails
import com.petopedia.app.ui.base.BaseActivity
import com.petopedia.app.ui.bottomSheet.BottomSheetFragment
import com.petopedia.app.ui.viewModel.PetViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_main

    private val viewModel: PetViewModel by viewModels<PetViewModel>()
    private var pageIndex: Int = 0
    private lateinit var statistics: List<Map.Entry<Char, Int>>
    private lateinit var breedsList: List<PetDetails.Species.Breeds>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        initUI()
        eventListener()
        setUpViews()
    }

    private fun eventListener() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.petData.collectLatest {
                        if (it.speciesList.isNotEmpty()) {
                            breedsList = it.speciesList[pageIndex].breedsList
                            viewModel.getStatisticCount(breedsList)
                            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                            }.attach()
                        }
                    }
                }

                launch {
                    viewModel.topCharCount.collectLatest {
                        statistics = it
                    }
                }
            }
        }
    }

    private fun initUI() {
        viewModel.getPetList("")
        binding.index = 0
        binding.viewModel = viewModel
    }

    private fun setUpViews() {
        binding.fab.setOnClickListener {
            if (::statistics.isInitialized && statistics.isNotEmpty()) {
                val bottomSheet = BottomSheetFragment.newInstance(statistics, breedsList.size)
                bottomSheet.show(supportFragmentManager, bottomSheet.tag)
            } else {
                viewModel.getStatisticCount(breedsList)
            }
        }

        binding.viewPager.registerOnPageChangeCallback(
            object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.search.setQuery("", false)
                    binding.index = position
                    pageIndex = position
                }
            },
        )

        binding.search.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean = false

                override fun onQueryTextChange(input: String?): Boolean {
                    viewModel.getFilteredData(pageIndex, input ?: "")
                    return true
                }
            },
        )
    }
}
