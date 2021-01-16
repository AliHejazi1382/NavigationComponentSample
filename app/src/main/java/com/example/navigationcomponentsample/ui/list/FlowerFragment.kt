package com.example.navigationcomponentsample.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.navigationcomponentsample.R
import com.example.navigationcomponentsample.databinding.FragmentFlowerBinding

class FlowerFragment : Fragment(R.layout.fragment_flower) {
    private val args: FlowerFragmentArgs by navArgs()
    private var _binding: FragmentFlowerBinding? = null
    private val binding: FragmentFlowerBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFlowerBinding.bind(view)
        binding.imgPhotoFlower.setImageResource(args.photo)
        binding.tvCategory.text = args.category
        binding.tvInstructions.text = args.instrcutions
        binding.tvName.text = args.name
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}