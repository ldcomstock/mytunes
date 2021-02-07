package com.example.mytunes.resultlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mytunes.R
import com.example.mytunes.databinding.FragmentResultListBinding
import com.example.mytunes.hideKeyboard
import java.net.URLEncoder


@Suppress("unused")
class ResultListFragment : Fragment(R.layout.fragment_result_list) {

    private val viewModel: ResultListViewModel by lazy {
        ViewModelProvider(this).get(ResultListViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentResultListBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Give the binding access to the view model
        binding.viewModel = viewModel

        binding.button.setOnClickListener {
            // close the keyboard and reset everything before fetching results
            hideKeyboard()
            binding.resultHeader.text = ""
            viewModel.clearResults()

            // grab text entered into edittext and process it / encode
            val searchTermEntered = binding.artistEditText.text.toString()
            val encodedSearchTerm = URLEncoder.encode(searchTermEntered, "utf-8")
            encodedSearchTerm?.let { term ->
                viewModel.getResults(term)
            }
        }

        binding.resultList.adapter = viewModel.results.value?.let { searchResultItems ->
            SearchResultCardAdapter(searchResultItems)
        }

        return binding.root
    }
}