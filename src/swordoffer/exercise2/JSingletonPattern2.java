package swordoffer.exercise2;

/**
 * 7.线程安全的懒汉式：枚举
 */
enum JSingleton5 {
    INSTANCE;

    public void whateverMethod() {
        // do something
    }
}

/**
 * 第2题 单例设计模式
 * 设计一个类，只能生成该类的一个实例。
 */
public class JSingletonPattern2 {
}

/**
 * 1.饿汉式：线程安全，耗费资源
 * 优点:这种方法非常简单，因为单例的实例被声明成 static 和 final 变量了，在第一次加载类到内存中时就会初始化，所以创建实例本身是线程安全的.
 * 缺点: 资源利用率不高,可能getInstance()永远不会执行到,但执行该类的其他静态方法或者加载了该类（class.forName),那么这个实例仍然初始化.饿汉式的创建方式在一些场景中将无法使用：譬如 Singleton 实例的创建是依赖参数或者配置文件的,在 getInstance()之前必须调用某个方法设置参数给它,那样这种单例写法就无法使用了
 */
class JHugerSingleton1 {
    //该对象的引用不可修改
    private static final JHugerSingleton1 ourInstance = new JHugerSingleton1();

    private JHugerSingleton1() {
    }

    public static JHugerSingleton1 getInstance() {
        return ourInstance;
    }
}

/**
 * 2.饿汉式：在静态代码块实例对象
 * 优点:这种方法非常简单，因为单例的实例被声明成 static 和 final 变量了，在第一次加载类到内存中时就会初始化，所以创建实例本身是线程安全的.
 * 缺点: 资源利用率不高,可能getInstance()永远不会执行到,但执行该类的其他静态方法或者加载了该类（class.forName),那么这个实例仍然初始化.饿汉式的创建方式在一些场景中将无法使用：譬如 Singleton 实例的创建是依赖参数或者配置文件的,在 getInstance()之前必须调用某个方法设置参数给它,那样这种单例写法就无法使用了
 */
class JHugerSingleton2 {
    private static JHugerSingleton2 ourInstance;

    static {
        ourInstance = new JHugerSingleton2();
    }

    private JHugerSingleton2() {
    }

    public static JHugerSingleton2 getInstance() {
        return ourInstance;
    }
}

/**
 * 3.懒汉式：非线程安全
 * 优点:不调用getInstance()就不会实例化,提高效率.
 * <p>
 * 缺点:在单个线程中没有问题,但多个线程同时访问的时候就可能同时创建多个实例,而且这多个实例不是同一个对象,虽然后面创建的实例会覆盖先创建的实例,但是还是会存在拿到不同对象的情况.
 */
class JSingleton1 {
    private static JSingleton1 ourInstance;

    private JSingleton1() {
    }

    public static JSingleton1 getInstance() {
        if (null == ourInstance) {
            ourInstance = new JSingleton1();
        }
        return ourInstance;
    }
}

/**
 * 4.线程安全的懒汉式：给方法加锁
 * 虽然做到了线程安全,并且解决了多实例的问题,但是它并不高效.因为在任何时候只能有一个线程调用 getInstance()方法.但是同步操作只需要在第一次调用时才被需要,即第一次创建单例实例对象时
 */
class JSingleton2 {
    private static JSingleton2 ourInstance;

    private JSingleton2() {
    }

    public synchronized static JSingleton2 getInstance() {
        if (null == ourInstance) {
            ourInstance = new JSingleton2();
        }
        return ourInstance;
    }
}

/**
 * 5.线程安全的懒汉式：双重检查锁（同步代码块）
 * 这段代码看起来很完美,但还是有问题,主要在于 instance = new Singleton()这句代码.因为这并非一个原子性操作,实际上在JVM里大概做了3件事:
 * <p>
 * 1.给instance分配内存
 * <p>
 * 2.调用Singleton构造完成初始化
 * <p>
 * 3.使instance对象的引用指向分配的内存空间(完成这一步instance就不是null了)
 * <p>
 * 但是在 JVM 的即时编译器中存在指令重排序的优化.也就是说上面的第二步和第三步的顺序是不能保证的,最终的执行顺序可能是 1-2-3 也可能是 1-3-2.如果是后者,则在 3 执行完毕、2 未执行之前,被线程二抢占了,这时 instance 已经是非 null 了（但却没有初始化）,所以线程二会直接返回 instance,然后使用,然后顺理成章地报错.(为什么使用了synchronized同步还会被其他线程抢占?)
 */
class JSingleton3 {
    private static JSingleton3 ourInstance;

    private JSingleton3() {
    }

    public synchronized static JSingleton3 getInstance() {
        if (null == ourInstance) {
            synchronized (JSingleton3.class) {
                if (null == ourInstance) {
                    ourInstance = new JSingleton3();
                }
            }
        }
        return ourInstance;
    }
}

/**
 * 6.线程安全的懒汉式：静态内部类（推荐）
 */
class JSingleton4 {
    private JSingleton4() {
    }

    public static JSingleton4 getInstance() {
        return SingletonHolder.ourInstance;
    }

    private static class SingletonHolder {
        private static JSingleton4 ourInstance = new JSingleton4();
    }
}

