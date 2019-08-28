# 背景
Android中，很常见的功能就是弹框，而弹框又有许多不同的需求，比如视频全屏播放时屏幕四周弹出的控制栏、可以随意拖动的悬浮框、上拉/下拉列表等等。而这各类弹框，都可以用PopUpWindow来实现。接下来就分享一款使用indow实现的弹框Demo（PopUpDialog）。

# 用法
我们首先来看看该项目的效果：
<img src="https://github.com/cjfu/PopUpDialog/blob/master/popupdialog.gif" width="270" height="540">
上述图片中，共展示了四种弹框方式，分别为屏幕左右两端弹出及控件上下方弹出。

好，接下来我们来看看该框架的用法：
首先新建PopUpDialog对象：

```
    PopUpDialog popUpDialog1;
    PopUpDialog popUpDialog2;
    PopUpDialog popUpDialog3;
    PopUpDialog popUpDialog4;
```
接下来初始化PopUpDialog：

```
popUpDialog1 = new PopUpDialog(R.id.main_layout,
                R.layout.dialog_layout,
                button1,
                PopUpDialog.DIALOG_LEFT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                this);
```
该构造方法的参数释义如下：

```
   /**
     * 用于构造一个弹框（点击控件后显示在该视图正上方）
     *
     * @param rootViewId   activity的id，即activity跟节点的id。需要在xml中定义（R.id.xxx）
     * @param dialogViewId 弹框布局文件id（R.layout.xxx）
     * @param clickView    与弹框关联的控件（弹框将显示在该控件的上方）,若不为上弹框(DIALOG_UP)，则可传入null
     * @param mode         弹出模式，用于决定打开方式。
     * @param context      上下文
     */
```
该构造方法可以确认弹框的弹出方式及绑定控件的状态等（弹出时，绑定的控件处于selected状态）
我们也可以对弹框中的布局进行事件的绑定，此处不再赘述，有兴趣可以下载查看，注释很详细。

接下来，就可以显示我们的弹框了：

```
popUpDialog1.showPopUpWindow();
```
也可以关闭弹框：

```
popUpDialog2.dismiss();
```
# 后续规划
本Demo中的PopUpDialog构造函数中参数较多，后续会改为链式调用来创建Dialog，且完善弹框方式（如屏幕其它位置弹框、可拖动的弹框等）。
# 总结
这个Demo使用PopUpDialog实现，做得比较完善。PopUpDialog可以实现屏幕任意位置的弹框，也可以在显示的时候改变自身位置（例如：仿悬浮框效果）。这些例子在本Demo中没有展示，后续会完善。大家可以把本Demo作为练手项目，或在项目中使用。
