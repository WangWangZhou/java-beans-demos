package cc.flybb.ico.java.beans;

import org.junit.Test;

import java.beans.*;
import java.util.stream.Stream;

/**
 * {@link java.beans.BeanInfo} 示例
 *
 */
public class BeanInfoDemo {

    @Test
    public void tset1() throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Persion.class);
        Stream.of(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor ->{
            System.out.println(propertyDescriptor);
        });
    }

    @Test
    public void tset2() throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Persion.class,Object.class);
        Stream.of(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor ->{
            System.out.println(propertyDescriptor);
        });
    }

    @Test
    public static void tset3() throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Persion.class,Object.class);
        Stream.of(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor ->{
            // PropertyDescriptor 允许添加属性编辑器 PropertyEditor
            Class<?> propertyType = propertyDescriptor.getPropertyType();
            String propertyName = propertyDescriptor.getName();
            if("age".equals(propertyName)){
                propertyDescriptor.setPropertyEditorClass(StringToIntegerPropertyEditorClass.class);
            }

        });
    }


    static class StringToIntegerPropertyEditorClass extends PropertyEditorSupport {

        public void setAsText(String text) throws java.lang.IllegalArgumentException {
            Integer value = Integer.valueOf(text);
            setValue(value);
        }
    }


    /**
     * 其它例子：https://blog.csdn.net/zhonglin_li/article/details/51571093
     * Spring之PropertyEditorSupport_浅谈
     */






//    public static void main(String[] args) throws IntrospectionException {
//        BeanInfo beanInfo = Introspector.getBeanInfo(Persion.class);
//        Stream.of(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor ->{
//            System.out.println(propertyDescriptor);
//        });
//    }
}