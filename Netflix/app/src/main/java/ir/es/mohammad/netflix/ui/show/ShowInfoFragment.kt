package ir.es.mohammad.netflix.ui.show

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import ir.es.mohammad.netflix.R
import ir.es.mohammad.netflix.ui.UserViewModel
import ir.es.mohammad.netflix.databinding.FragmentShowInfoBinding

class ShowInfoFragment : Fragment(R.layout.fragment_show_info) {

    private lateinit var binding: FragmentShowInfoBinding
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShowInfoBinding.bind(view)

        binding.user = userViewModel

    }
}