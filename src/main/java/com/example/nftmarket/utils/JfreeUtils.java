package com.example.nftmarket.utils;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class JfreeUtils{
    public static String imagePath = "D:\\SpringCloudAlibaba\\NFT-Market\\src\\main\\resources\\static\\images";

    public static void testPie(){
        //如 果不使用Font,中文将显示不出来
        Font font = new Font("新宋体", Font.BOLD, 15);
        String[] name = {"绿色恐龙","红色恐龙","黄色恐龙","灰色恐龙","黑色恐龙"};
        double[] value = {1000,700,600,400,150};
        // 创建数据：饼状图就是名称和值（比例）
        Map<String, Double> map=new HashMap<String, Double>();
        for (int i=0;i<name.length;i++) {
            map.put(name[i],value[i]);
        }
        // 创建JFreeChart
        JFreeChart chart = createPieChart("Dinosaur-NFT市场占比", map, font);
        //在D盘目录下生成图片
        File p = new File(imagePath);
        if (!p.exists()) {
            p.mkdirs();
        }
        String imageName ="饼图.jpeg";
        File file = new File(p.getPath() + "/" + imageName);
        try {
            if(file.exists()) {
                file.delete();
            }
            ChartUtilities.saveChartAsJPEG(file, chart, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("饼图构建完成");
    }

    public static void testLine(){
        //如 果不使用Font,中文将显示不出来
        Font font = new Font("新宋体", Font.BOLD, 15);
        // 创建数据
        Map<String, Map<String, Double>> datas =new HashMap<String, Map<String,Double>>();
        String[] monthArray = {"一月","二月","三月","四月","五月","六月","七月","八月"};
        double[] value = {20,30,25,50,40,25,50,40};

        for (int i=0; i<monthArray.length;i++) {
            Map<String, Double> map =new HashMap<String, Double>();
            map.put("NFT售卖数量", value[i]);
            datas.put(monthArray[i],map);
        }

        JFreeChart chart = createLineChart("市场NFT-时间曲线", datas, "月份", "价格", font);
        //在D盘目录下生成图片
        File p = new File(imagePath);
        if (!p.exists()) {
            p.mkdirs();
        }
        String imageName ="折线图.jpeg";
        File file = new File(p.getPath() + "/" + imageName);
        try {
            if(file.exists()) {
                file.delete();
            }
            ChartUtilities.saveChartAsJPEG(file, chart, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("折线图构建完毕");
    }

    /**
     * 生成饼图
     * @param title
     * @param data
     * @param font
     * @return
     */
    public static JFreeChart createPieChart(String title, Map<String, Double> data, Font font) {
        try {
            Set<Map.Entry<String, Double>> set = data.entrySet();
            DefaultPieDataset pds = new DefaultPieDataset();
            Iterator iterator = set.iterator();
            Map.Entry entry;
            while (iterator.hasNext()) {
                entry = (Map.Entry) iterator.next();
                pds.setValue(entry.getKey().toString(), Double.parseDouble(entry.getValue().toString()));
            }
            // 生成一个饼图的图表：显示图表的标题、组装的数据、是否显示图例、是否生成贴士以及是否生成URL链接
            JFreeChart chart = ChartFactory.createPieChart(title, pds, true, false, true);
            // 设置图片标题的字体
            chart.getTitle().setFont(font);
            // 得到图块,准备设置标签的字体
            PiePlot plot = (PiePlot) chart.getPlot();
            //设置分裂效果,需要指定分裂出去的key
//            plot.setExplodePercent("摄像机", 0.1);  分裂效果，可选
            // 设置标签字体
            plot.setLabelFont(font);
            // 设置图例项目字体
            chart.getLegend().setItemFont(font);
            // 设置开始角度
//            plot.setStartAngle(new Float(3.14f / 2f));  开始角度，意义不大
            //设置plot的前景色透明度
            plot.setForegroundAlpha(0.7f);
            //设置plot的背景色透明度
            plot.setBackgroundAlpha(0.0f);
            //设置标签生成器(默认{0})
            //{0}:key {1}:value {2}:百分比 {3}:sum
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({1})/{2}"));  // 一般在{1}后面加单位，如：{0}({1}次)/{2}
            //将内存中的图片写到本地硬盘
//            ChartUtilities.saveChartAsJPEG(new File("H:/a.png"), chart, 600, 300);
            // 标注位于上侧
            // chart.getLegend().setPosition(RectangleEdge.TOP);
            // 设置标注无边框
            chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
            return chart;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成折线图
     * @param title
     * @param data
     * @param type
     * @param unit
     * @param font
     * @return
     */
    public static JFreeChart createLineChart(String title, Map<String, Map<String, Double>> data, String type, String unit, Font font) {
        try {
            DefaultCategoryDataset ds = new DefaultCategoryDataset();
            Set<Map.Entry<String, Map<String, Double>>> set1 = data.entrySet();
            Iterator iterator1 = set1.iterator();
            Iterator iterator2;
            HashMap<String, Double> map;
            Set<Map.Entry<String, Double>> set2;
            Map.Entry entry1;
            Map.Entry entry2;
            while (iterator1.hasNext()) {
                entry1 = (Map.Entry) iterator1.next();
                map = (HashMap<String, Double>) entry1.getValue();
                set2 = map.entrySet();
                iterator2 = set2.iterator();
                while (iterator2.hasNext()) {
                    entry2 = (Map.Entry) iterator2.next();
                    ds.setValue(Double.parseDouble(entry2.getValue().toString()), entry2.getKey().toString(), entry1.getKey().toString());
                }
            }

            //创建折线图,折线图分水平显示和垂直显示两种
            // //2D折线图
            JFreeChart chart = ChartFactory.createLineChart(title, type, unit, ds, PlotOrientation.VERTICAL, true, true, true);
            // //3D折线图
            JFreeChart chart2 = ChartFactory.createLineChart3D(title, type, unit, ds, PlotOrientation.VERTICAL, true, true, false);

            //设置整个图片的标题字体
            chart.getTitle().setFont(font);

            //设置提示条字体
            font = new Font("宋体", Font.BOLD, 15);
            chart.getLegend().setItemFont(font);

            //得到绘图区
            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            //得到绘图区的域轴(横轴),设置标签的字体
            plot.getDomainAxis().setLabelFont(font);

            // 设置背景透明度
            plot.setBackgroundAlpha(0.1f);
            // 设置网格横线颜色
            plot.setRangeGridlinePaint(Color.gray);
            // 设置网格横线大小
            plot.setDomainGridlineStroke(new BasicStroke(0.2F));
            plot.setRangeGridlineStroke(new BasicStroke(0.2F));

            //设置横轴标签项字体
            plot.getDomainAxis().setTickLabelFont(font);

            // 生成折线图上的数字
            //绘图区域(红色矩形框的部分)
            LineAndShapeRenderer lineAndShapeRenderer=(LineAndShapeRenderer)plot.getRenderer();
            lineAndShapeRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
            //设置图表上的数字可见
            lineAndShapeRenderer.setBaseItemLabelsVisible(true);
            //设置图表上的数字字体
            lineAndShapeRenderer.setBaseItemLabelFont(new Font("宋体",Font.BOLD,15));

            //设置折线图拐角上的正方形
            //创建一个正方形
            Rectangle  shape=new Rectangle(4,4);
            lineAndShapeRenderer.setSeriesShape(0, shape);
            //设置拐角上图形可见
            lineAndShapeRenderer.setSeriesShapesVisible(0, true);

            /*// 获取显示线条的对象
            LineAndShapeRenderer lasp = (LineAndShapeRenderer) plot.getRenderer();
            // 设置拐点是否可见/是否显示拐点
            lasp.setBaseShapesVisible(true);
            // 设置拐点不同用不同的形状
            lasp.setDrawOutlines(true);
            // 设置线条是否被显示填充颜色
            lasp.setUseFillPaint(true);
            // 设置拐点颜色
            lasp.setBaseFillPaint(Color.blue);//蓝色*/


            //设置范围轴(纵轴)字体
            font = new Font("宋体", Font.BOLD, 18);
            plot.getRangeAxis().setLabelFont(font);
//            plot.setForegroundAlpha(1.0f);
            return chart;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}