package com.petopedia.app.ui.bottomSheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.petopedia.app.R
import com.petopedia.app.databinding.StatisticsBottomSheetBinding
import java.util.Locale

class BottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: StatisticsBottomSheetBinding
    private lateinit var statistic: List<Map.Entry<Char, Int>>
    private var totalCount = 0

    companion object {
        private const val ARG_PETS = "pets"
        private const val TOTAL_COUNT = "counts"

        fun newInstance(
            pets: List<Map.Entry<Char, Int>>,
            totalCounts: Int,
        ): BottomSheetFragment {
            val fragment = BottomSheetFragment()
            val bundle = bundleOf(ARG_PETS to ArrayList(pets), TOTAL_COUNT to totalCounts)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            statistic = it.getSerializable(ARG_PETS) as List<Map.Entry<Char, Int>>
            totalCount = it.getInt(TOTAL_COUNT)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.background = AppCompatResources.getDrawable(requireContext(), R.drawable.rounded_top_corners)
        }
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = StatisticsBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        binding.tvTitle.text = requireContext().getString(R.string.total_count, totalCount.toString())
        val views =
            listOf(
                binding.inclTopOne to statistic.getOrNull(0),
                binding.inclTopTwo to statistic.getOrNull(1),
                binding.inclTopThree to statistic.getOrNull(2),
            )

        views.forEach { (viewBinding, stat) ->
            stat?.let { (char, count) ->
                viewBinding.tvChar.text = char.toString().uppercase(Locale.getDefault())
                viewBinding.tvCount.text = count.toString()
            }
        }
    }
}
