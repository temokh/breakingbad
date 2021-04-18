package breaking.bad.azdaki.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import breaking.bad.azdaki.data.storage.DataStore
import breaking.bad.azdaki.databinding.LanguageBottomSheetFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LanguagePickerBottom: BottomSheetDialogFragment() {

    lateinit var binding: LanguageBottomSheetFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LanguageBottomSheetFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.languageButtonEng.setOnClickListener{
            DataStore.language = "en"
            dismiss()
            activity?.recreate()
        }

        binding.languageButtonGeo.setOnClickListener{
            DataStore.language = "ka"
            dismiss()
            activity?.recreate()
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
            .apply { (this as BottomSheetDialog).behavior.expandedOffset = 300 }
    }



}