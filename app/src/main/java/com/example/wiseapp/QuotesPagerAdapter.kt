package com.example.wiseapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * @author CHOI
 * @email vviian.2@gmail.com
 * @created 2021-08-03
 * @desc
 */

class QuotesPagerAdapter(
    private val quotes: List<Quote>,
    private val isNameRevealed: Boolean
) : // 리사이클러뷰 어뎁터를 상속받으며 제너릭으로 뷰홀더를 설정해
    RecyclerView.Adapter<QuotesPagerAdapter.QuoteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder =
       QuoteViewHolder(
           LayoutInflater.from(parent.context)
               .inflate(R.layout.item_quote, parent, false)
       )

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val actualPosition = position % quotes.size // 무한 스크롤을 할 것 이므로 사이즈로 나눈 나머지로 위치 정함
        holder.bind(quotes[actualPosition], isNameRevealed)
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // itemView : 내가 정의 해준 레이아웃 뷰
        private val quoteTextView : TextView = itemView.findViewById(R.id.quoteTextView)
        private val nameTextView : TextView = itemView.findViewById(R.id.nameTextView)

        @SuppressLint("SetTextI18n")
        fun bind(quote : Quote, isNameRevealed : Boolean) {
            // 어떻게 랜더링 할 것 인가
            quoteTextView.text = "\"${quote.quote}\"" // 명언내용

            // 원격 isNameRevealed 에 따라 분기 (Firebase 에서 설정된 불값을 가져와서 처리)
            if (isNameRevealed) {
                nameTextView.text = "-${quote.name}" // 작가
                nameTextView.visibility = View.GONE
            }

        }
    }
}

