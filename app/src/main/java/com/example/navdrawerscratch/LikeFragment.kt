package com.example.navdrawerscratch

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LikeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_like, container, false)


        val backButton: ImageButton = view.findViewById(R.id.back_button)
        backButton.setOnClickListener {

            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }


        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val productList = getProductList()
        val adapter = LikeProductAdapter(requireContext(), productList)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
            }
        })

        return view
    }

    // Sample product list data
    private fun getProductList(): List<ProductsData> {
        return listOf(
            ProductsData("Python", 10.0,"https://img-c.udemycdn.com/course/750x422/692188_9da7_34.jpg" ),
            ProductsData("javascript", 20.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQEXp6X8q2P_kqVsrGvcqPg_uvDopQB-zmBlA&s"),
            ProductsData("graphic design", 12.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQN7J0JAOJwXBWf1BL8ENNxECoFcOV5OoSi8g&s"),
            ProductsData("DSA", 12.0,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTSdGdwG3ViAJUKxWP-QOofMSfNb7Heb22oVg&s" ),
            ProductsData("React", 12.0,"https://img-c.udemycdn.com/course/750x422/4471614_361e_8.jpg" ),
            ProductsData("c++", 12.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRHJAr0Fb4tQukGm9tW7CdRrzdnDAAnLDcCOQ&s"),
            ProductsData("Java", 12.0,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQv1Iv9TNR__Ru5iXrcBATB7ptVbmXcKS2Uvw&s"),
            ProductsData("spring boot", 12.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT8P09mT9qRviGIM6-y7j9SLl2Njiqp6SSGEg&s"),
            ProductsData("android", 12.0,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCapSN0Ew8nO1ZOGF2Pdps9Z1d79rKk1f0XQ&s"),
            ProductsData("java", 12.0,"https://img-c.udemycdn.com/course/750x422/1535678_0ce9_7.jpg"),
            ProductsData("figma", 12.0,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTnBghTqgqwmDkBOUPKFmMbAEgAHqrshbMsfA&s"),
            ProductsData("Node js", 12.0,"https://img-c.udemycdn.com/course/750x422/1879018_95b6_3.jpg" ),
            ProductsData("next js", 12.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ5oEbV16UhwRkmdZAT-zxkniTmDbusn8Bt-Q&s"),
            ProductsData("Angular", 12.0,"https://img-c.udemycdn.com/course/480x270/289230_1056_16.jpg"),
            ProductsData("php", 12.0,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTCR4J8EvHxvEMSyvmp0GrRzOw7oC8V5f5hLg&s" ),
            ProductsData("data science", 12.0,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRD0MX67lfol9KIJ6pa6GGA3hWmmi-VFazX-Q&s" ),
            ProductsData("digital marketing", 12.0,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTV2VLSPSyH-UtdeZKLnXUKcRF9NmOnsyLXgw&s" ),
            ProductsData("django", 12.0,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzyMdWmRMxe7SuLcAJ6VNUmyg8gCjHkX3I_g&s"),
            ProductsData("machine learning", 12.0, "https://img-c.udemycdn.com/course/750x422/950390_270f_3.jpg"),
            ProductsData("rust", 12.0,"https://img-c.udemycdn.com/course/750x422/624252_3505_3.jpg" ),
            ProductsData("Devops", 12.0,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTn2EDnAVF6T4p8dclEQRz9BlNJ_EL4rl-7mA&s" )
        )
    }
}





