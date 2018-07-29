package tk.andivinu.mvvmkotlin.ui.search

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import tk.andivinu.mvvmkotlin.R
import tk.andivinu.mvvmkotlin.databinding.ItemResultBinding
import tk.andivinu.mvvmkotlin.model.Result

class ResultListAdapter: RecyclerView.Adapter<ResultListAdapter.ViewHolder>() {
    private lateinit var postList:ArrayList<Result>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultListAdapter.ViewHolder {
        val binding: ItemResultBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_result, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultListAdapter.ViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int {
        return if(::postList.isInitialized) postList.size else 0
    }

    fun updatePostList(postList:ArrayList<Result>){
        this.postList = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemResultBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = ResultViewModel()

        fun bind(post:Result){
            viewModel.bind(post)
            binding.viewModel = viewModel
        }
    }
}