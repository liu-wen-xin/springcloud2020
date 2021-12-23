import java.time.ZonedDateTime;

/*用于yml里predicates（断言）时间戳的获取*/
public class T2 {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();   //获取默认时区
        System.out.println(now);  //2021-12-23T14:00:17.941+08:00[Asia/Shanghai]
    }
}
