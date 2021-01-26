package foit.product.loranalyst.ui.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import foit.product.loranalyst.R
import foit.product.loranalyst.databinding.FragmentCardGalleryBinding
import foit.product.loranalyst.ext.View.hide
import foit.product.loranalyst.ext.View.show
import foit.product.loranalyst.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class CardGalleryFragment : Fragment() {

    private lateinit var binding: FragmentCardGalleryBinding
    private lateinit var adapterCardGallery: CardGalleryAdapter

    private val cardGalleryViewModel: CardGalleryViewModel by viewModel()

    companion object {
        fun newInstance() = CardGalleryFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardGalleryBinding.inflate(inflater)

        initAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserver()
    }

    private fun initAdapter(){
        adapterCardGallery = CardGalleryAdapter{ card ->
            val bundle = bundleOf("card_code" to card.cardCode)
            findNavController().navigate(R.id.nav_cardDetail, bundle)
        }

        binding.listCardGallery.adapter = adapterCardGallery
    }

    private fun setupObserver(){
        cardGalleryViewModel.cardGallery.observe(viewLifecycleOwner, Observer {
            it.let { resource ->
                when(resource.status){
                    Status.SUCCESS -> {
                        binding.listCardGallery.show()
                        binding.progressBar.hide()
                        resource.data.let { listPlaces ->
                            adapterCardGallery.submitList(listPlaces)
                        }
                    }
                    Status.ERROR -> {
                        binding.listCardGallery.show()
                        binding.progressBar.hide()
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        Timber.d(it.message)
                    }
                    Status.LOADING -> {
                        binding.listCardGallery.hide()
                        binding.progressBar.show()
                    }
                }
            }
        })
    }
}