package com.example.fitfitness.ui.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.fitfitness.R
import com.example.fitfitness.adapters.ExerciseAdapter
import com.example.fitfitness.ui.components.ButtonsState.LEFT_VISIBLE
import com.example.fitfitness.ui.components.ButtonsState.RIGHT_VISIBLE
import com.example.fitfitness.util.drawableToBitmap
import org.jetbrains.annotations.NotNull
import kotlin.math.max
import kotlin.math.min


internal enum class ButtonsState {
    GONE, LEFT_VISIBLE, RIGHT_VISIBLE
}

class SwipeController(private val buttonsActions: SwipeControllerActions?, private val adapter: ExerciseAdapter, private val context: Context) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
    private var swipeBack = false
    private var buttonShowedState = ButtonsState.GONE
    private var buttonInstance: RectF? = null
    private var currentItemViewHolder: RecyclerView.ViewHolder? = null

    //    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
//        val clickedRecommendation: SurveysRecommendation = adapter.getRecommendation(viewHolder.adapterPosition)
//        return if (!clickedRecommendation.dismissible) {
//            makeMovementFlags(0, ItemTouchHelper.ACTION_STATE_IDLE)
//        } else makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
//    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        if (swipeBack) {
            swipeBack = buttonShowedState != ButtonsState.GONE
            return 0
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }

    override fun onChildDraw(c: Canvas, @NotNull recyclerView: RecyclerView, @NotNull viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        var dX = dX
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            if (buttonShowedState != ButtonsState.GONE) {
                if (buttonShowedState == LEFT_VISIBLE) dX = max(dX, buttonWidth)
                if (buttonShowedState == RIGHT_VISIBLE) dX = min(dX, -buttonWidth)
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            } else {
                setTouchListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }
        if (buttonShowedState == ButtonsState.GONE) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
        currentItemViewHolder = viewHolder
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchListener(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        recyclerView.setOnTouchListener { v: View?, event: MotionEvent ->
            swipeBack = event.action == MotionEvent.ACTION_CANCEL || event.action == MotionEvent.ACTION_UP
            if (swipeBack) {
                if (dX < -buttonWidth)
                    buttonShowedState = RIGHT_VISIBLE else if (dX > buttonWidth) buttonShowedState = LEFT_VISIBLE
                if (buttonShowedState != ButtonsState.GONE) {
                    setTouchDownListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    setItemsClickable(recyclerView, false)
                }
            }
            false
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchDownListener(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        recyclerView.setOnTouchListener { _: View?, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                setTouchUpListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
            false
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchUpListener(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        recyclerView.setOnTouchListener { _: View?, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_UP) {
                super@SwipeController.onChildDraw(c, recyclerView, viewHolder, 0f, dY, actionState, isCurrentlyActive)
                recyclerView.setOnTouchListener { _: View?, _: MotionEvent? -> false }
                setItemsClickable(recyclerView, true)
                swipeBack = false
                if (buttonsActions != null && buttonInstance != null && buttonInstance!!.contains(event.x, event.y)) {
                    if (buttonShowedState == LEFT_VISIBLE) {
                        buttonsActions.onLeftClicked(viewHolder.adapterPosition)
                    } else if (buttonShowedState == RIGHT_VISIBLE) {
                        buttonsActions.onRightClicked(viewHolder.adapterPosition)
                    }
                }
                buttonShowedState = ButtonsState.GONE
                currentItemViewHolder = null
            }
            false
        }
    }

    private fun setItemsClickable(recyclerView: RecyclerView, isClickable: Boolean) {
        for (i in 0 until recyclerView.childCount) {
            recyclerView.getChildAt(i).isClickable = isClickable
        }
    }

    private fun drawButtons(c: Canvas, viewHolder: RecyclerView.ViewHolder) {
        val buttonWidthWithoutPadding = buttonWidth - 20
        val corners = 25f
        var imageHeight: Int
        val itemView: View = viewHolder.itemView
        val p = Paint()

//        p.color = Color.parseColor("#38495a")
        val leftButton = RectF((itemView.left + 10).toFloat(), (itemView.top + 20).toFloat(), itemView.left + buttonWidthWithoutPadding - 10, (itemView.bottom - 20).toFloat())
        c.drawRoundRect(leftButton, corners, corners, p)
        imageHeight = drawImage(c, leftButton, p, context.resources.getDrawable(R.drawable.ic_home_black_24dp))
        drawText("Snooze", c, leftButton, p, imageHeight)

//        p.color = Color.parseColor("#38495a")
        val rightButton = RectF(itemView.right - buttonWidthWithoutPadding - 10, (itemView.top + 20).toFloat(), (itemView.right - 10).toFloat(), (itemView.bottom - 20).toFloat())
        c.drawRoundRect(rightButton, corners, corners, p)
        imageHeight = drawImage(c, rightButton, p, context.resources.getDrawable(R.drawable.ic_dashboard_black_24dp))
        drawText("Dismiss", c, rightButton, p, imageHeight)

        buttonInstance = null
        if (buttonShowedState == LEFT_VISIBLE) {
            buttonInstance = leftButton
        } else if (buttonShowedState == RIGHT_VISIBLE) {
            buttonInstance = rightButton
        }
    }

    private fun drawText(text: String, c: Canvas, button: RectF, p: Paint, height: Int) {
        val textSize = 60f
        p.color = Color.WHITE
        p.isAntiAlias = true
        p.textSize = textSize
        val textWidth: Float = p.measureText(text)
        c.drawText(text, button.centerX() - textWidth / 2, button.centerY() + textSize / 2 + height, p)
    }

    private fun drawImage(c: Canvas, button: RectF, p: Paint, drawable: Drawable): Int {
        val bmp = drawableToBitmap(drawable)
        val width = bmp!!.width
        val scale = button.width() / width * .5f
        val matrix = Matrix()
        matrix.postScale(scale, scale)
        val scaledImage = Bitmap.createBitmap(bmp, 0, 0, bmp.width, bmp.height, matrix, false)
        bmp.recycle()
        c.drawBitmap(
            scaledImage,
            button.centerX() - scaledImage.width / 2,
            button.centerY() - scaledImage.height / 2 + 10,
            p
        )
        return scaledImage.height
    }

    fun onDraw(c: Canvas) {
        if (currentItemViewHolder != null) {
            drawButtons(c, currentItemViewHolder!!)
        }
    }

    companion object {
        private const val buttonWidth = 300f
    }
}