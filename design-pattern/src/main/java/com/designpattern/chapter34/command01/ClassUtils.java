package com.designpattern.chapter34.command01;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


/**
 * 工具类，根据一个class找到父类的所有子类
 * 不要使用，有性能问题
 */
public class ClassUtils {

    // 根据父类查找到所有的子类，默认情况是子类和父类都在同一个包名下
    public static List<Class> getSonClass(Class fatherClass) {
        // 定义一个返回值
        List<Class> returnClassList = new ArrayList<>();
        // 获得包名名称
        String packageName = fatherClass.getPackage().getName();
        // 获得包中所有的类
        List<Class> packClasses = getClasses(packageName);
        // 判断是否是子类
        for (Class clazz : packClasses) {
            // isAssignableFrom()方法的调用者和参数都是Class对象，调用者为父类，参数为本身或者其子类
            // 必须不为自身
            if (fatherClass.isAssignableFrom(clazz) && !fatherClass.equals(clazz)) {
                returnClassList.add(clazz);
            }
        }
        return returnClassList;
    }

    // 从一个包中查找出所有的类，在jar包中的不能查找
    private static List<Class> getClasses(String packageName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".", "/");
        Enumeration<URL> resources = null;
        try {
            // 从指定路径中获取资源文件
            resources = classLoader.getResources(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<File> dirs = new ArrayList<>();
        // 当且仅当此枚举对象至少还包含一个可提供的元素时，才返回 true；否则返回 false。
        while (resources.hasMoreElements()) {
            // 相当于先判空，然后加所有的根节点元素加入里面。
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<>();
        for (File directory : dirs) {
            // 迭代找到所有的子节点信息
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    // 找到某个文件夹中，包名为packageName的所有类
    private static List<Class> findClasses(File directory, String packageName) {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                // 断言，避免调用出错
                assert !file.getName().contains(".");
                // 迭代，找到所有的类文件
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                try {
                    // 获取包名+类名，不要后缀.class
                    classes.add(Class.forName(packageName + "." + file.getName().substring(0, file.getName().length() - 6)));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return classes;
    }
}
