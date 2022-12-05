import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup


import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.task.trendingrepos.databinding.ItemTrendingRepoBinding
import com.task.trendingrepos.ui.model.TrendingRepositoryUiModel

class TrendingRepoAdapter : Adapter<TrendingRepoAdapter.TrendingRepoViewHolder>() {

    private var repoList: List<TrendingRepositoryUiModel> = emptyList()

    var onItemClick: OnItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingRepoViewHolder {
        return TrendingRepoViewHolder(
            ItemTrendingRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TrendingRepoViewHolder, position: Int) {
        val repo = repoList[position]
        holder.itemTrendingRepoBinding.run {
            val color = if (repo.isSelected) {
                Color.CYAN
            } else {
                Color.WHITE
            }
            root.setCardBackgroundColor(color)
            tvTitle.text = repo.name
            tvLanguage.text = repo.language
            Glide.with(root).load(repo.avatar).into(ivProfilePic)
            root.setOnClickListener {
                val selectedRepo = repoList[holder.adapterPosition]
                onItemClick?.onItemClick(holder.adapterPosition, selectedRepo.isSelected.not())
            }
        }
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    fun updateRepoList(list: List<TrendingRepositoryUiModel>) {
        this.repoList = list
        notifyItemRangeChanged(0, list.size)
    }

    inner class TrendingRepoViewHolder(val itemTrendingRepoBinding: ItemTrendingRepoBinding) :
        ViewHolder(itemTrendingRepoBinding.root)

    interface OnItemClick {
        fun onItemClick(position: Int, isSelected: Boolean)
    }
}
