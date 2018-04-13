# Android PagerSlidingTabStrip 自定义导航栏

**PagerSlidingTabStrip** 是Android 平台的开源项目，导航控件。完美兼容 Android 自带库和兼容库的ViewPager组件，和ViewPager配合的页面指示器。

![主页图](https://upload-images.jianshu.io/upload_images/4625401-09a3f54a563f5bea.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

***

#### 1. 属性介绍
1）基本属性（XML）
* **pstsIndicatorColor** 滑动条的颜色
* **pstsIndicatorHeight** 滑动条的高度
* **pstsUnderlineColor** 滑动条所在的那个全宽线的颜色
* **pstsUnderlineHeight** 滑动条所在的那个全宽线的高度
* **pstsDividerColor** 每个标签的分割线的颜色
* **pstsDividerPadding** 分割线底部和顶部的填充宽度
* **pstsTabPaddingLeftRight** 每个标签左右填充宽度
* **pstsScrollOffset** 滑动偏移量
* **pstsTabBackground** 每个标签背景
* **pstsShouldExpand** 若为true，每个标签均匀平分整个屏幕，默认是false
* **pstsTextAllCaps** 若为true，所有标签都是大写字母，默认为true

2）扩展属性（Java）
* **setIconAndText** 设置布局样式，图文展示（**TABICONTEXT** 图标加文本；**TABICON** 图标；**TABTEXT** 文本）
* **setNormalIconRes** 设置未选择的图标
* **setLightIconRes** 设置已选择的图标
* **setTabTexts** 设置文本
* **setSelectedTextColor** 设置选中的Tab文字的颜色
* **setIndicatorinFollower** 设置底部横线与字体宽度一致，默认是false

#### 2. 用法介绍
1）添加库依赖
项目app下**build.gradle**文件中
```
dependencies {
	compile 'com.github.gb-nick:PagerSlidingTabStrip:v1.0.5'
}
```

2）layout里加入PagerSlidingTabStrip控件
```
<com.library.tabstrip.PagerSlidingTabStrip
    android:id="@+id/tabs"
    android:layout_width="match_parent"
    android:layout_height="40dp" />
<android.support.v4.view.ViewPager
    android:id="@+id/pager"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

3）onCreate方法（或者onCreateView对于一个fragment），绑定PagerSlidingTabStrip
控件到ViewPager上
```
// Initialize the ViewPager and set an adapter
ViewPager pager = (ViewPager) findViewById(R.id.pager);
pager.setAdapter(new TestAdapter(getSupportFragmentManager()));

// Bind the tabs to the ViewPager
PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
tabs.setViewPager(pager);
```

* 文本导航栏

![文本导航图](https://upload-images.jianshu.io/upload_images/4625401-830c427f6bb342b8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

控件初始化
```
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
	ViewPager pager = (ViewPager) findViewById(R.id.pager);
	pager.setAdapter(new TextAdapter(getSupportFragmentManager(), titles, list));

    // 设置Tab底部选中的指示器 Indicator的颜色
	tabs.setIndicatorColor(Color.GREEN);
    //设置Tab标题文字的颜色
	tabs.setTextColor(Color.BLACK);
    // 设置Tab标题文字的大小
	tabs.setTextSize(16);
    //设置Tab底部分割线的颜色
	tabs.setUnderlineColor(Color.TRANSPARENT);
    // 设置点击某个Tab时的背景色,设置为0时取消背景色
	tabs.setTabBackground(0);
    // 设置Tab是自动填充满屏幕的
	tabs.setShouldExpand(true);
    //!!!设置选中的Tab文字的颜色!!!
	tabs.setSelectedTextColor(Color.GREEN);
    //tab间的分割线
	tabs.setDividerColor(Color.GRAY);
    //底部横线与字体宽度一致
	tabs.setIndicatorinFollower(true);
    //与ViewPager关联，这样指示器就可以和ViewPager联动
	tabs.setViewPager(viewPager);
}
```
文本适配器
```
class TextAdapter extends FragmentPagerAdapter {
	String[] titles;
	List<Fragment> lists = new ArrayList<>();

	public TextAdapter(FragmentManager fm, String[] titles, List<Fragment> list) {
		super(fm);
		this.titles = titles;
		this.lists = list;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return titles[position];
	}

	@Override
	public Fragment getItem(int position) {
		return lists.get(position);
	}

	@Override
	public int getCount() {
		return list.size();
	}
}
```

* 图标导航栏

![图标导航图](https://upload-images.jianshu.io/upload_images/4625401-d8ee334b02a4fbc9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

控件初始化
```
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
	ViewPager pager = (ViewPager) findViewById(R.id.pager);
	pager.setAdapter(new IconAdapter(getSupportFragmentManager(), icon1, list));

	// 设置Tab底部选中的指示器 Indicator的颜色
	tabs.setIndicatorColor(Color.GREEN);
	//设置Tab标题文字的颜色
	tabs.setTextColor(Color.BLACK);
	// 设置Tab标题文字的大小
	tabs.setTextSize(16);
	//设置Tab底部分割线的颜色
	tabs.setUnderlineColor(Color.TRANSPARENT);
	// 设置点击某个Tab时的背景色,设置为0时取消背景色
	tabs.setTabBackground(0);
	//设置Tab是自动填充满屏幕的
	tabs.setShouldExpand(true);
	//!!!设置未选择的图标!!!
	tabs.setNormalIconRes(icon0);
	//!!!设置已选择的图标!!!
	tabs.setLightIconRes(icon1);
	//tab间的分割线
	tabs.setDividerColor(Color.GRAY);
	//底部横线与字体宽度一致
	tabs.setIndicatorinFollower(true);
	//与ViewPager关联，这样指示器就可以和ViewPager联动
	tabs.setViewPager(viewPager);
}
```
图标适配器
```
class IconAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {
	int[] icons;
	List<Fragment> lists = new ArrayList<>();
	public MyPageAdapter(FragmentManager fm, int[] icons, List<Fragment> list) {
		super(fm);
		this.icons = icons;
		this.lists = list;
	}

	@Override
	public Fragment getItem(int position) {
		return lists.get(position);
	}

	@Override
	public int getCount() {
		return list.size();
	}
	@Override
	public int getPageIconResId(int position) {
		return icons[position];
	}

}
```

* 图文导航栏

![图文导航图](https://upload-images.jianshu.io/upload_images/4625401-94067d2a01551052.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

控件初始化
```
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
	ViewPager pager = (ViewPager) findViewById(R.id.pager);
	pager.setAdapter(new IconTextAdapter(getSupportFragmentManager(), list));

    //!!!设置布局样式（必须要设置，TABICONTEXT图标加文本；TABICON图标；TABTEXT文本）!!!
    tabs.setIconAndText(PagerSlidingTabStrip.TABICONTEXT);

	// 设置Tab底部选中的指示器 Indicator的颜色
	tabs.setIndicatorColor(Color.GREEN);
	//设置Tab标题文字的颜色
	tabs.setTextColor(Color.BLACK);
	// 设置Tab标题文字的大小
	tabs.setTextSize(16);
	//设置Tab底部分割线的颜色
	tabs.setUnderlineColor(Color.TRANSPARENT);
	// 设置点击某个Tab时的背景色,设置为0时取消背景色
	tabs.setTabBackground(0);
	// 设置Tab是自动填充满屏幕的
	tabs.setShouldExpand(true);
	//!!!设置未选择的图标!!!
	tabs.setNormalIconRes(icon0);
	//!!!设置已选择的图标!!!
	tabs.setLightIconRes(icon1);
	//!!!设置文本!!!
	tabs.setTabTexts(titles);
	//!!!设置选中的Tab文字的颜色!!!
	tabs.setSelectedTextColor(Color.GREEN);
	//去除tab间的分割线
	tabs.setDividerColor(Color.TRANSPARENT);
	//底部横线与字体宽度一致
	tabs.setIndicatorinFollower(true);
	//与ViewPager关联，这样指示器就可以和ViewPager联动
	tabs.setViewPager(viewPager);
}
```
图文适配器
```
class IconTextAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.ViewTabProvider {
	List<Fragment> lists = new ArrayList<>();
	Context context;
	public MyPageAdapter( Context context,FragmentManager fm,  List<Fragment> list) {
		super(fm);
		this.lists = list;
		this.context = context;
	}

	@Override
	public Fragment getItem(int position) {
		return lists.get(position);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public View getTabView(int position) {
		View view = LayoutInflater.from(context).inflate(R.layout.view_tab_menu, null);
		return view;
	}

}
```
自定义布局
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:gravity="center">

<ImageView
    android:id="@id/iv_tab_icon"
    android:layout_width="50dp"
    android:layout_height="30dp"
    android:scaleType="fitCenter"
    android:layout_gravity="center"/>

<TextView
    android:id="@id/tv_tab_name"
    android:layout_marginTop="2dp"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="center"
    android:gravity="center"
    android:textColor="#666666"
    android:textSize="14sp" />

</LinearLayout>
```
注意ImageView 和 TextView 控件ID必须为`@id/iv_tab_icon`和`@id/tv_tab_name`

#### 3. 源码分析
* **IconTabProvider**

接口`IconTabProvider`，在ViewPager对应的Adapter实现该方法`getPageIconResId(int position)`并返回每个ViewPager对应的图标，即可实现图标指示器。
```
public interface IconTabProvider {
        public int getPageIconResId(int position);
}
```

* **ViewTabProvider**

接口`ViewTabProvider`，在ViewPager对应的Adapter实现该方法` public View getTabView(int position)`并返回每个ViewPager对应的视图，即可实现自定义视图指示器。
```
public interface ViewTabProvider {
        public View getTabView(int position);
}
```

* **setViewPager， setOnPageChangeListener**

一个用来与ViewPager联动，一个用来处理自定义的OnPagerListener逻辑
```
public void setViewPager(ViewPager pager) {
	this.pager = pager;

	if (pager.getAdapter() == null) {
		throw new IllegalStateException("ViewPager does not have adapter instance.");
	}

	pager.setOnPageChangeListener(pageListener);

	notifyDataSetChanged();
}

 public void setOnPageChangeListener(OnPageChangeListener listener) {
        this.delegatePageListener = listener;
 }
```
* **PageListener**
```
private class PageListener implements OnPageChangeListener {

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //存储当前位置信息
		currentPosition = position;
		currentPositionOffset = positionOffset;

        //滑动到子视图
		scrollToChild(position, (int) (positionOffset * tabsContainer.getChildAt(position).getWidth()));

		//重新绘制
		invalidate();

        //用户自定义的OnPagerChangeListener事件之onPagerScrolled
		if (delegatePageListener != null) {
			delegatePageListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
		}
	}

	@Override
	public void onPageScrollStateChanged(int state) {
		// 滑动结束
		if (state == ViewPager.SCROLL_STATE_IDLE) {
			scrollToChild(pager.getCurrentItem(), 0);
		}
		// 自定义ViewPager的OnPageChangeListener实现
		if (delegatePageListener != null) {
			delegatePageListener.onPageScrollStateChanged(state);
		}
	}

	@Override
	public void onPageSelected(int position) {
		selectedPosition = position;
		updateTabStyles();
		// 自定义ViewPager的OnPageChangeListener实现
		if (delegatePageListener != null) {
			delegatePageListener.onPageSelected(position);
		}
	}

}
```

* **notifyDataSetChanged**

自定义方法notifyDataSetChanged，用来先移除现有的childView，再遍历addView，设置更新每个Tab的属性
```
public void notifyDataSetChanged() {
	tabsContainer.removeAllViews();

	tabCount = pager.getAdapter().getCount();
	for (int i = 0; i < tabCount; i++) {
		if (pager.getAdapter() instanceof IconTabProvider) {//Adapter中获取图标
			addIconTab(i, ((IconTabProvider) pager.getAdapter()).getPageIconResId(i));
		} else if (pager.getAdapter() instanceof ViewTabProvider) {//Adapter中获取自定义布局
			addViewTab(i, ((ViewTabProvider) pager.getAdapter()).getTabView(i));
		} else {//Adapter中获取文本
			addTextTab(i, pager.getAdapter().getPageTitle(i).toString());
		}
	}

	updateTabStyles();

	getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
		@SuppressWarnings("deprecation")
		@SuppressLint("NewApi")
		@Override
		public void onGlobalLayout() {
			if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
				getViewTreeObserver().removeGlobalOnLayoutListener(this);
			} else {
				getViewTreeObserver().removeOnGlobalLayoutListener(this);
			}

			currentPosition = pager.getCurrentItem();
			scrollToChild(currentPosition, 0);
		}
	});
}
```

* **scrollToChild**

滑动指定子视图
```
private void scrollToChild(int position, int offset) {
	if (tabCount == 0) {
		return;
	}
	int newScrollX = tabsContainer.getChildAt(position).getLeft() + offset;
	if (position > 0 || offset > 0) {
		newScrollX -= scrollOffset;
	}
	if (newScrollX != lastScrollX) {
		lastScrollX = newScrollX;
		scrollTo(newScrollX, 0);
	}
}
```
* **addTextTab，addIconTab，addViewTab，addTab**

*addTextTab* 创建Tab文本；*addIconTab* 创建Tab图标；*addViewTab* 创建Tab自定义视图；*addTab* 设置视图属性
```
private void addTextTab(final int position, String title) {
	TextView tab = new TextView(getContext());
	tab.setText(title);//设置文本
	tab.setGravity(Gravity.CENTER);
	tab.setSingleLine();

	addTab(position, tab);
}

private void addIconTab(final int position, int resId) {
	ImageButton tab = new ImageButton(getContext());
	tab.setImageResource(resId);//设置图标

	addTab(position, tab);
}

private void addViewTab(final int position, View tab) {
	tab.setFocusable(true);
	tab.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			pager.setCurrentItem(position);//设置当前页
			selectedPosition = position;
			updateTabStyles();
		}
	});
	tab.setPadding(tabPadding, 0, tabPadding, 0);
	tabsContainer.addView(tab, position, shouldExpand ? expandedTabLayoutParams : defaultTabLayoutParams);
}

private void addTab(final int position, View tab) {
	tab.setFocusable(true);
	tab.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			pager.setCurrentItem(position);
		}
	});
	tab.setPadding(tabPadding, 0, tabPadding, 0);
	tabsContainer.addView(tab, position, shouldExpand ? expandedTabLayoutParams : defaultTabLayoutParams);
}
```

* **updateTabStyles**

设置更新每个Tab的属性
```
private void updateTabStyles() {
	for (int i = 0; i < tabCount; i++) {
		View v = tabsContainer.getChildAt(i);
		v.setBackgroundResource(tabBackgroundResId);//设置背景色

		if (v instanceof TextView) {//文本
			TextView tab = (TextView) v;
			tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSize);
			tab.setTypeface(tabTypeface, tabTypefaceStyle);
			tab.setTextColor(tabTextColor);
			// setAllCaps() is only available from API 14, so the upper case is made manually if we are on a
			// pre-ICS-build
			if (textAllCaps) {//设置文本大写
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
					tab.setAllCaps(true);
				} else {
					tab.setText(tab.getText().toString().toUpperCase(locale));
				}
			}
			if (i == selectedPosition) {//设置选中文本字体颜色
				tab.setTextColor(selectedTabTextColor);
			}
		} else if(v instanceof ImageButton) {//图标
			ImageButton imageButton = (ImageButton) v;
			if (i == selectedPosition) {//设置选中图标
				imageButton.setImageResource(this.lightIconRes[i]);
			} else {//设置未选中图标
				imageButton.setImageResource(this.normalIconRes[i]);
			}
		} else if (v instanceof ViewGroup) {//自定义视图
			//适配器中getTabView回调方法中加载的布局控件
			ImageView tabIcon = null;
			TextView tabTxt = null;
			if (iconAndText==TABICONTEXT) {//自定义图标+文本视图
				tabIcon = (ImageView) v.findViewById(R.id.iv_tab_icon);
				tabTxt = (TextView) v.findViewById(R.id.tv_tab_name);
				tabTxt.setText(tabTexts[i]);
			} else if(iconAndText==TABICON) {//自定义图标视图
				tabIcon = (ImageView) v.findViewById(R.id.iv_tab_icon);
			} else if(iconAndText==TABTEXT) {//自定义文本视图
				tabTxt = (TextView) v.findViewById(R.id.tv_tab_name);
				tabTxt.setText(tabTexts[i]);
			}
			if (i == selectedPosition) {
				if (iconAndText==TABICONTEXT) {//设置选中图标和文本字体颜色
					tabIcon.setImageResource(this.lightIconRes[i]);
					tabTxt.setTextColor(selectedTabTextColor);
				} else  if (iconAndText==TABICON) {//设置选中图标
					tabIcon.setImageResource(this.lightIconRes[i]);
				} else  if (iconAndText==TABTEXT) {//设置选中文本字体颜色
					tabTxt.setTextColor(selectedTabTextColor);
				}
			} else {
				if (iconAndText==TABICONTEXT) {//设置未选中图标和文本字体颜色
					tabTxt.setTextColor(tabTextColor);
					tabIcon.setImageResource(this.normalIconRes[i]);
				} else  if (iconAndText==TABICON) {//设置未选中图标
					tabIcon.setImageResource(this.normalIconRes[i]);
				} else  if (iconAndText==TABTEXT) {//设置未选中文本字体颜色
					tabTxt.setTextColor(tabTextColor);
				}
			}
		}
	}

}
```
* **onDraw**

对滑动indicator、底部分割线和tab之间的分割线进行绘制，实现了View的滑动。
```
@Override
protected void onDraw(Canvas canvas) {
	super.onDraw(canvas);

	if (isInEditMode() || tabCount == 0) {
		return;
	}
	final int height = getHeight();

	// 设置Indicator颜色
	rectPaint.setColor(indicatorColor);

	//  获取当前选中Tab
	View currentTab = tabsContainer.getChildAt(currentPosition);

	View curTab = null;
	if (iconAndText ==TABEMPTY) {//非自定义视图
		if (currentTab instanceof TextView) {//文本
			curTab = (TextView) currentTab;
		} else if(currentTab instanceof ImageButton) {//图标
			curTab = (ImageButton) currentTab;
		}
	} else if (iconAndText==TABICONTEXT||iconAndText==TABTEXT) {//获取自定义文本视图
		curTab = currentTab.findViewById(R.id.tv_tab_name);
	} else if(iconAndText==TABICON) {//获取自定义图标视图
		curTab = currentTab.findViewById(R.id.iv_tab_icon);
	}
     //获取Left,Right值
	float lineLeft = currentTab.getLeft();
	float lineRight = currentTab.getRight();

	// if there is an offset, start interpolating left and right coordinates between current and next tab
	if (currentPositionOffset > 0f && currentPosition < tabCount - 1) {
        //结合下一个Tab获取当前要绘制的indicator的位置
		View nextTab = tabsContainer.getChildAt(currentPosition + 1);

		final float nextTabLeft = nextTab.getLeft();
		final float nextTabRight = nextTab.getRight();

		lineLeft = (currentPositionOffset * nextTabLeft + (1f - currentPositionOffset) * lineLeft);
		lineRight = (currentPositionOffset * nextTabRight + (1f - currentPositionOffset) * lineRight);
	}

	float width = 0;
	if(iconAndText==TABEMPTY) {
		if (curTab instanceof TextView) {//获取文本内容宽度
			TextView tab = (TextView) curTab;
			width = tab.getPaint().measureText(tab.getText().toString() + "");
		} else if (curTab instanceof ImageButton) {//获取图标宽度
			ImageButton tab = (ImageButton) curTab;
			int w = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
			int h = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
			tab.measure(w, h);
			width = tab.getMeasuredWidth() - tabPadding*2;
		}
	} else {
		if (curTab instanceof TextView) {//获取自定义文本视图内容宽度
			TextView tab = (TextView) currentTab.findViewById(R.id.tv_tab_name);
			width = tab.getPaint().measureText(tab.getText().toString() + "");
		}
		if (curTab instanceof ImageView) {//获取自定义图标视图宽度
			ImageView tab = (ImageView)currentTab.findViewById(R.id.iv_tab_icon);
			int w = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
			int h = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
			tab.measure(w, h);
			width = tab.getMeasuredWidth();
		}
	}

	if (indicatorinFollower) { //设置底部横线与文字宽度一致
		canvas.drawRect(lineLeft + (lineRight - lineLeft - width) / 2, height - indicatorHeight, lineLeft + width + (lineRight - lineLeft - width) / 2, height, rectPaint);
	} else { //默认设置，底部横线填满宽度
		canvas.drawRect(lineLeft, height - indicatorHeight, lineRight, height, rectPaint);
	}

	// 绘制underline
	rectPaint.setColor(underlineColor);
	canvas.drawRect(0, height - underlineHeight, tabsContainer.getWidth(), height, rectPaint);

	// 绘制分割线
	dividerPaint.setColor(dividerColor);
	for (int i = 0; i < tabCount - 1; i++) {
		View tab = tabsContainer.getChildAt(i);
		canvas.drawLine(tab.getRight(), dividerPadding, tab.getRight(), height - dividerPadding, dividerPaint);
	}

}
```
#### 4. 注意事项
1）如果在XML中使用PagerSlidingTabStrip属性，记得添加`xmlns:psts="http://schemas.android.com/apk/res-auto"`
```
<com.library.tabstrip.PagerSlidingTabStrip
	xmlns:psts="http://schemas.android.com/apk/res-auto"
    android:id="@+id/tabs"
    android:layout_width="match_parent"
    android:layout_height="40dp"
	psts:pstsShouldExpand="true"/>
```
2）使用自定义视图，必须要设置视图类型`setIconAndText(int int stateVaule)`
TABICONTEXT图标加文本；TABICON图标；TABTEXT文本

3）使用自定义视图，自定义布局图标控件（ImageView）ID必须为` android:id="@id/iv_tab_icon"`，文本控件（TextView）ID必须为` android:id="@id/tv_tab_name"`；
两个控件ID已封装，在PagerSlidingTabStrip类中使用，如果使用其他ID会找不到资源报错。

***

#### 简书地址
https://www.jianshu.com/p/ccb4ced85165

