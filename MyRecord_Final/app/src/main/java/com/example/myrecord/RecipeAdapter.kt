package com.example.record06

import com.example.bottomnavi.RecipeData
import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecord.R
import com.example.myrecord.RecipeActivity
import com.example.myrecord.RecipeActivity.Companion.RECIPE
import kotlinx.android.synthetic.main.list_item_recipe.view.*

/* 어댑터 클래스 선언
   - 리사이클러뷰에 표시될 뷰 홀더와 각 아이템 뷰(객체)를 생성하고, 데이터를 바인딩
   - 리사이클러뷰의 어댑터는 RecyclerView.Adapter를 상속하고, <제네릭>타입으로  선언된 뷰홀더 선언
 */
class RecipeAdapter (private val context: Context) : RecyclerView.Adapter<RecipeAdapter.ItemViewHolder>() {


    //어앱터에서 관리하는 아이템의 개수를 반환
    override fun getItemCount(): Int {
        Log.d("RECIPE", "getItemCount() 호출")
        return RecipeData.values().size
    }


    //뷰 객체와 뷰홀더를 생성하여 반환
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeAdapter.ItemViewHolder {
        Log.d("RECIPE", "onCreateViewHolder() 호출")
        //아이템 뷰(ist_item_workout)를 인플레이션(view 객체 생성)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_recipe, parent,false)

        //view 객체를 담고 있는 뷰 홀더를 반환
        return ItemViewHolder(view)
    }//end of onCreateViewHolder()

    //뷰홀더에 데이터를 바인딩
    override fun onBindViewHolder(holder: RecipeAdapter.ItemViewHolder, position: Int) {
        holder.bindRecipeData(RecipeData.values()[position])
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //아이템 뷰(itemView)에 배치된 뷰 참조
        //val recipeName = itemView.tv_recipe_name;
        // val recipeIngre = itemView.tv_recipe_info;
        //val imgDetailRecipe = itemView.img_detail_recipe

        fun bindRecipeData(recipeData:RecipeData){
            itemView.tv_recipe_name.text=recipeData.recipeName
            itemView.tv_recipe_info.text=recipeData.ingre
            itemView.img_detail_recipe.setImageResource(recipeData.firstImage)

            itemView.setOnClickListener{
                val intent= Intent(itemView.context, RecipeActivity::class.java)

                Log.i("RECIPE","recipeData:"+recipeData.recipeName)

                intent.putExtra(RECIPE,recipeData.name)
                itemView.context.startActivity(intent)
            }
        }
    }//end of ItemViewHolder


}//end of WorkoutAdapter