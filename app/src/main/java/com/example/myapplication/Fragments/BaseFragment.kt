package com.example.myapplication.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment : Fragment() {
    //lady : làm cho cái by bởi nó dùng it đi,LazyThreadSafetyMode.NONE không dùng trong đa luôồng
    private val logTag by lazy(LazyThreadSafetyMode.NONE) {
        this::class.java.simpleName
    }

    @CallSuper // bắt buộc phải có super
    override fun onAttach(context: Context) {
        super.onAttach(context)
        logMessage("onAttach: context=$context")
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logMessage("onCreate: savedInstanceState=$savedInstanceState")
        logMessage("onCreate: arguments=${arguments}")
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logMessage("onViewCreated: view=$view")
        logMessage("onViewCreated: savedInstanceState=$savedInstanceState")
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        logMessage("onStart")
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        logMessage("onResume")
    }

    @CallSuper
    override fun onPause() {
        logMessage("onPause")
        super.onPause()
    }

    @CallSuper
    override fun onStop() {
        logMessage("onStop")
        super.onStop()
    }

    @CallSuper
    override fun onDestroyView() {
        logMessage("onDestroyView")
        super.onDestroyView()
    }

    @CallSuper
    override fun onDestroy() {
        logMessage("onDestroy")
        super.onDestroy()
    }

    @CallSuper
    override fun onDetach() {
        logMessage("onDetach")
        super.onDetach()
    }

    protected fun logMessage(message: String) {
        Log.d(logTag, "$this: $message")
    }
}
// khai báo VB, VB : ViewBinding
//khai báo hàm inflateViewBinding có bi ến inflater,parent,attachToParent trả về VB
//inflateViewBinding: (inflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean) -> VB
abstract class BaseFragment1WithViewBinding<VB : ViewBinding>(
    private val inflateViewBinding: (inflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean) -> VB
) : BaseFragment() {
    private var _binding: VB? = null

    /**
     * Valid only after [onViewCreated] and before [onDestroyView].
     */
    protected val binding: VB get() = _binding!!
        // get(lấy) _binding!! = binding // get() có thể lấy Int,Double,String,Long,.....

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        logMessage("onCreateView")
        _binding = inflateViewBinding(inflater, container, false)
        // <=> _binding = ViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    @CallSuper
    override fun onDestroyView() {
        _binding = null // Avoid memory leaks
        super.onDestroyView()
    }
}
