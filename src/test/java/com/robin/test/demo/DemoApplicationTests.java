package com.robin.test.demo;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.robin.test.demo.entiry.*;
import com.robin.test.demo.service.StudentService;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class DemoApplicationTests {

    @Test
    public static List<User> users(){
        List<User> list = Arrays.asList(
                new User("李星云", 18, 0, "渝州",new BigDecimal(1000)),
                new User("陆林轩", 16, 1, "渝州",new BigDecimal(500)),
                new User("姬如雪", 17, 1, "幻音坊",new BigDecimal(800)),
                new User("袁天罡", 99, 0, "藏兵谷",new BigDecimal(100000)),
                new User("张子凡", 19, 0, "天师府",new BigDecimal(900)),
                new User("陆佑劫", 45, 0, "不良人",new BigDecimal(600)),
                new User("张天师", 48, 0, "天师府",new BigDecimal(1100)),
                new User("张天师", 48, 0, "天师府",new BigDecimal(1100)),
                new User("蚩梦", 18, 1, "万毒窟",new BigDecimal(800))
        );
        return list;
    }
    //过滤
    @Test
    public void show1(){
        List<User> list = users();
        List<User> collect = list.stream().filter(x -> x.getAge() > 20).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
    }
    //去重
    @Test
    public void show2(){
        List<User> list = users();
        List<User> collect = list.stream().distinct().collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
    }
    //排序
    @Test
    public void show3(){
        List<User> list = users();
        List<User> collect = list.stream().sorted(Comparator.comparing(User::getAge).reversed()).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
    }
    //限制个数
    @Test
    public void show4(){
        List<User> list = users();
        List<User> collect = list.stream().sorted(Comparator.comparing(User::getAge).reversed()).limit(3).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
    }
    //去除前n个
    @Test
    public void show5(){
        List<User> list = users();
        List<User> collect = list.stream().sorted(Comparator.comparing(User::getAge).reversed()).skip(2).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
    }
    //映射
    @Test
    public void show6(){
        List<User> list = users();
        List<String> collect = list.stream().map(User::getName).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
    }
    //转化成stream
    @Test
    public void show7(){
        List<String> flatmap = new ArrayList<>();
        flatmap.add("常宣灵,常昊灵");
        flatmap.add("孟婆,判官红,判官蓝");

        List<String> collect = flatmap.stream().map(s -> s.split(",")).flatMap(Arrays::stream).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
    }

    //通过反射进行方法的调用
    @Test
    public void show8() throws Exception{

        //获取类的Class对象实例
        Class<?> aClass = Class.forName("com.robin.test.demo.entiry.User");
        //获取constructor对象
        Constructor<?> constructor = aClass.getConstructor();
        //获取反射对象
        Object user = constructor.newInstance();

        //获取反射方法
        Method setName = aClass.getMethod("setName", String.class);
        Method getName = aClass.getMethod("getName");
        //使用invoke调用方法
        setName.invoke(user,"aaa");
        String username =(String) getName.invoke(user);

        System.out.println("username = " + username);

    }

    //获取class对象的三种方式
    @Test
    public void show10() throws Exception{
        //使用 Class.forName 静态方法
        Class<?> aClass = Class.forName("com.robin.test.demo.entiry.User");
        System.out.println("aClass = " + aClass);
        //使用 .class 方法
        Class aClass1 = User.class;
        System.out.println("aClass2 = " + aClass1);
        //使用类对象的 getClass() 方法
        User user = new User();
        Class<? extends User> aClass2 = user.getClass();
        System.out.println("aClass2 = " + aClass2);
    }
    //通过反射创建类对象
    @Test
    public void show11() throws Exception{
        //1.只能进行无参构造
        Class aClass1 = Student.class;
        Student st = (Student)aClass1.newInstance();
        System.out.println("st = " + st);
        //2.可以选择有参或者无参构造
        //2.1 无参构造
        Constructor constructor = aClass1.getConstructor();
        Student st1 = (Student)constructor.newInstance();
        System.out.println("st1 = " + st1);
        //2.2 有参构造
        Constructor constructor1 = aClass1.getConstructor(String.class,Integer.class);
        Student st2 = (Student)constructor1.newInstance("aaa",18);
        System.out.println("st2 = " + st2);
    }
    //通过反射获取类属性，方法，构造器
    @Test
    public void show12() throws Exception{
        //获取类属性
        Class aClass = Student.class;
        Field[] fields = aClass.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        System.out.println("------------------------------------------");
        //获取私有的必须使用有 declared 关键字的方法
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
        }

        //获取所有的方法
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            System.out.println("method = " + method);
        }
        //获取构造
        Constructor constructor = aClass.getConstructor();
    }

    @Autowired
    private StudentService studentService;

    @Test
    public void show13() throws Exception{
        Student st =new Student();
        st.setName("bbb");
        st.setAge(20);
        //st.setTypeEnum(TypeEnum.ONE);
        st.setType(TypeEnum.TWO);

        Student st2 = studentService.queryById(1);
        studentService.addStudent(st);
        System.out.println("st2 = " + st2);
    }

































    @Test
    public void show9() throws Exception{
        String[] chinese={"零", "壹", "貳", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[] unit={"圆","拾", "佰", "仟","万","拾万","佰万","仟万","亿"};
        // 123 一百二十三 壹佰贰拾叁圆

        String num = "5000";
        StringBuilder sum=new StringBuilder();
        int n=0;
        int m=0;
        char[] chars = num.toCharArray();
        for (int length = chars.length-1; length >= 0; length--) {
            char s = chars[length];
            String a1 = chinese[Integer.valueOf(String.valueOf(s))];
            String b1 = unit[n];
            String c1=a1+b1;
           if (s==48){
               if (m==1){
                   n++;
                   continue;
               }else {
                   if (b1.equals("圆")){
                       c1=b1;
                   }else {
                       c1="零";
                       m=1;
                   }
               }
           }
            sum.insert(0,c1);
            n++;
        }
        System.out.println("sum = " + sum);
    }

    @Test
    public void testExportExcel() throws Exception {

        List<CourseEntity> courseEntityList = new ArrayList<>();
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId("1");
        courseEntity.setName("测试课程");
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setName("张老师");
        teacherEntity.setSex(1);
        courseEntity.setMathTeacher(teacherEntity);

        List<StudentEntity> studentEntities = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setName("学生" + i);
            studentEntity.setSex(i);
            studentEntity.setBirthday(new Date());
            studentEntities.add(studentEntity);
        }
        courseEntity.setStudents(studentEntities);
        courseEntityList.add(courseEntity);
        Date start = new Date();
        Workbook workbook = ExcelExportUtil.exportExcel( new ExportParams("导出测试", null, "测试"),
                CourseEntity.class, courseEntityList);
        System.out.println(new Date().getTime() - start.getTime());
        File savefile = new File("D:/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D:/excel/教师课程学生导出测试.xls");
        workbook.write(fos);
        fos.close();
    }


}
