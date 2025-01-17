package com.oxford.sean.pres.widgets

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class EvenSpacingRecyclerViewDecorator(private val space: Int, private val endSpacing: EndSpacing = EndSpacing.EVEN) : RecyclerView.ItemDecoration() {
    enum class EndSpacing { EVEN, NONE, DOUBLE, HALF }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val positionOfFirstItem = 0
        val positionOfLastItem = ((parent.adapter?.itemCount)?.minus(1)) ?: Int.MAX_VALUE
        val orientation = when (parent.layoutManager) {
            is LinearLayoutManager -> (parent.layoutManager as LinearLayoutManager).orientation
            is StaggeredGridLayoutManager -> (parent.layoutManager as StaggeredGridLayoutManager).orientation
            else -> VERTICAL
        }

        val endSpace = when(endSpacing){
            EndSpacing.EVEN -> space
            EndSpacing.NONE -> 0
            EndSpacing.DOUBLE -> space * 2
            EndSpacing.HALF -> space / 2
        }

        when (orientation) {
            VERTICAL -> {
                outRect.top = space / 2
                outRect.bottom = space / 2
                when (position) {
                    positionOfFirstItem -> outRect.top = endSpace
                    positionOfLastItem -> outRect.bottom = endSpace
                }
            }
            HORIZONTAL -> {
                outRect.left = space / 2
                outRect.right = space / 2
                when (position) {
                    positionOfFirstItem -> outRect.left = endSpace
                    positionOfLastItem -> outRect.right = endSpace
                }
            }
            else -> Unit
        }
    }

}