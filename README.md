# Refreshlayout
SmartRefreshLayout使用



SmartRefreshLayout 刷新布局核心实现，自带ClassicsHeader（经典）、BezierRadarHeader（贝塞尔雷达）两个 Header.
SmartRefreshHeader 各种Header的集成，除了Layout自带的Header，其它都在这个包中.
SmartRefreshFooter 各种Footer的集成，除了Layout自带的Footer，其它都在这个包中.
简单用例

1.在 buld.gradle 中添加依赖

compile 'com.android.support:appcompat-v7:25.3.1'//版本随意
compile 'com.scwang.smartrefresh:SmartRefreshLayout:1.0.2'
compile 'com.scwang.smartrefresh:SmartRefreshHeader:1.0.2'//没有使用特殊Header，可以不加这行
2.在XML布局文件中添加 SmartRefreshLayout

<?xml version="1.0" encoding="utf-8"?>
<com.scwang.smartrefresh.layout.SmartRefreshLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/refreshLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <android.support.v7.widget.RecyclerView
        android:id="@+id/recyclerview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:overScrollMode="never"
        android:background="#fff" />
</com.scwang.smartrefresh.layout.SmartRefreshLayout>
3.在 Activity 或者 Fragment 中添加代码

RefreshLayout refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
refreshLayout.setOnRefreshListener(new OnRefreshListener() {
    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh(2000);
    }
});
refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        refreshlayout.finishLoadmore(2000);
    }
});
使用指定的 Header 和 Footer

1.方法一 全局设置

public class App extends Application {
    public void onCreate() {
        super.onCreate();
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
                @Override
                public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                    //指定为经典Header，默认是 贝塞尔雷达Header
                    return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);
                }
            });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
                @Override
                public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                    //指定为经典Footer，默认是 BallPulseFooter
                    return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
                }
            });
    }
}
注意：方法一 设置的Header和Footer的优先级是最低的，如果同时还使用了方法二、三，将会被其它方法取代

2.方法二 XML布局文件指定

<com.scwang.smartrefresh.layout.SmartRefreshLayout
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/refreshLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#444444"
    app:srlPrimaryColor="#444444"
    app:srlAccentColor="@android:color/white"
    app:srlEnablePreviewInEditMode="true">
    <!--srlAccentColor srlPrimaryColor 将会改变 Header 和 Footer 的主题颜色-->
    <!--srlEnablePreviewInEditMode 可以开启和关闭预览功能-->
    <com.scwang.smartrefresh.layout.header.ClassicsHeader
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:padding="@dimen/padding_common"
        android:background="@android:color/white"
        android:text="@string/description_define_in_xml"/>
    <com.scwang.smartrefresh.layout.footer.ClassicsFooter
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
</com.scwang.smartrefresh.layout.SmartRefreshLayout>
注意：方法二 XML设置的Header和Footer的优先级是中等的，会被方法三覆盖。而且使用本方法的时候，Android Studio 会有预览效果，如下图：



不过不用担心，只是预览效果，运行的时候只有下拉才会出现~

3.方法三 Java代码设置

final RefreshLayout refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
//设置 Header 为 Material风格
refreshLayout.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));
//设置 Footer 为 球脉冲
refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
项目地址在：https://github.com/scwang90/SmartRefreshLayout


