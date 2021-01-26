package foit.product.loranalyst.base

import android.animation.Animator
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import foit.product.loranalyst.R
import foit.product.loranalyst.codetail.animation.ViewAnimationUtils
import foit.product.loranalyst.databinding.ActivityMainBinding
import foit.product.loranalyst.menu.interfaces.Resourceble
import foit.product.loranalyst.menu.interfaces.ScreenShotable
import foit.product.loranalyst.menu.model.SlideMenuItem
import foit.product.loranalyst.menu.util.ViewAnimator
import foit.product.loranalyst.utils.MenuItem

class MainActivity : AppCompatActivity(), ViewAnimator.ViewAnimatorListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var viewAnimator: ViewAnimator<SlideMenuItem>
    private lateinit var linearLayout: LinearLayout
    private var drawerToggle: ActionBarDrawerToggle? = null
    private val list: ArrayList<SlideMenuItem> = ArrayList()
    private var res = R.drawable.content_music

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val contentFragment: ContainerFragment = ContainerFragment.newInstance(R.drawable.content_music)
        supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame, contentFragment)
            .commit()
        drawerLayout = binding.drawerLayout
        drawerLayout.setScrimColor(Color.TRANSPARENT)

        linearLayout = binding.leftDrawer
        linearLayout.setOnClickListener { drawerLayout.closeDrawers() }


        setActionBar()
        createMenuList()
        viewAnimator = ViewAnimator(this, list, contentFragment, drawerLayout, this)
    }

    private fun createMenuList() {
        val menuItem0 = SlideMenuItem(MenuItem.CLOSE, R.drawable.icn_close)
        list.add(menuItem0)
        val menuItem = SlideMenuItem(MenuItem.CARD_GALLERY, R.drawable.ic_card_gallery)
        list.add(menuItem)
        val menuItem2 = SlideMenuItem(MenuItem.DECK_MANAGER, R.drawable.ic_deck_manager)
        list.add(menuItem2)
        val menuItem3 = SlideMenuItem(MenuItem.DECK_BUILDER, R.drawable.ic_deck_builder)
        list.add(menuItem3)
        val menuItem4 = SlideMenuItem(MenuItem.GUIDE, R.drawable.ic_guide)
        list.add(menuItem4)
        val menuItem5 = SlideMenuItem(MenuItem.LEADER_BOARD, R.drawable.ic_leader_board)
        list.add(menuItem5)
        val menuItem6 = SlideMenuItem(MenuItem.META, R.drawable.ic_meta)
        list.add(menuItem6)
    }

    private fun setActionBar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        drawerToggle = object : ActionBarDrawerToggle(
            this,       /* host Activity */
            drawerLayout,       /* DrawerLayout object */
            toolbar,            /* nav drawer icon to replace 'Up' caret */
            R.string.open,      /* "open drawer" description */
            R.string.close      /* "close drawer" description */
        ) {
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                linearLayout.removeAllViews()
                linearLayout.invalidate()
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                if (slideOffset > 0.6 && linearLayout.childCount == 0) viewAnimator.showMenuContent()
            }

        }
        drawerLayout.addDrawerListener(drawerToggle as ActionBarDrawerToggle)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle?.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle?.onConfigurationChanged(newConfig)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.drawer_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return if (drawerToggle!!.onOptionsItemSelected(item)) {
            true
        } else when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun replaceFragment(
        screenShotable: ScreenShotable,
        topPosition: Int
    ): ScreenShotable? {
        res =
            if (res == R.drawable.content_music) R.drawable.content_films else R.drawable.content_music
        val view: View = findViewById(R.id.content_frame)
        val finalRadius = view.width.coerceAtLeast(view.height)
        val animator: Animator =
            ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0F, finalRadius.toFloat())
        animator.interpolator = AccelerateInterpolator()
        animator.duration = ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION.toLong()
        binding.contentOverlay.background = BitmapDrawable(
            resources,
            screenShotable.bitmap
        )
        animator.start()
        val contentFragment: ContainerFragment = ContainerFragment.newInstance(this.res)
        supportFragmentManager.beginTransaction().replace(R.id.content_frame, contentFragment)
            .commit()
        return contentFragment
    }

    override fun disableHomeButton() {
        supportActionBar!!.setHomeButtonEnabled(false)
    }

    override fun enableHomeButton() {
        supportActionBar!!.setHomeButtonEnabled(true)
        drawerLayout.closeDrawers()
    }

    override fun addViewToContainer(view: View?) {
        linearLayout.addView(view)
    }

    override fun onSwitch(
        slideMenuItem: Resourceble?,
        screenShotable: ScreenShotable?,
        position: Int
    ): ScreenShotable {
        return when (slideMenuItem!!.name) {
            MenuItem.CLOSE -> screenShotable!!
            else -> replaceFragment(screenShotable!!, position)!!
        }
    }
}