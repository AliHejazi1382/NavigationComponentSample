package com.example.navigationcomponentsample.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.navigationcomponentsample.R
import com.example.navigationcomponentsample.adapter.FlowerAdapter
import com.example.navigationcomponentsample.databinding.FragmentListBinding
import com.example.navigationcomponentsample.event.OnItemClickListener
import com.example.navigationcomponentsample.extensions.getFlowerList
import com.example.navigationcomponentsample.extensions.getImageResource
import com.example.navigationcomponentsample.model.Flowers

class ListFragment : Fragment(R.layout.fragment_list), OnItemClickListener {
    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding get() = _binding!!
    private lateinit var adapter: FlowerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)
        adapter = FlowerAdapter(
            requireContext(),
            FlowerAdapter.FlowerCallback(),
            this
        )
        adapter.submitList(getFlowerList(requireContext()))
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),
            2,
            GridLayoutManager.VERTICAL,
            false)
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onItemClick(position: Int) {
        val currentFlower = adapter.getFlowerAt(position)
        val action = ListFragmentDirections.actionListFragmentToFlowerFragment(
            getImageResource(
                requireContext(),
                currentFlower.photo
            ),
            currentFlower.name,
            currentFlower.category,
            currentFlower.instructions
        )

        findNavController().navigate(action)
    }
}