package java8.newDateApi;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

/**
 * jdk 8 中日期时间 API 的测试
 *
 * @author: clarity
 * @date: 2022年10月17日 11:40
 */
public class JDK8DateTimeTest {

    @Test
    public void testDate() {
        Date date1 = new Date(2022 - 1900, 10 - 1, 17);
        System.out.println(date1);// Mon Oct 17 00:00:00 CST 2022

    }

    /*
      LocalDate、LocalTime、LocalDateTime 的使用
      LocalDateTime 相较于 LocalTime、LocalDateTime 使用频率更高
     */

    @Test
    public void test1() {
        // now()：获取当前时间、日期、日期 + 时间
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        // of()：设置指定的年、月、日、时、分、秒。没有偏移量
        LocalDateTime localDateTime1 = LocalDateTime.of(2022, 10, 17, 15, 3, 22);
        System.out.println(localDateTime1);

        // getXxx()：获取当前时间的某一部分
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getHour());
        System.out.println(localDateTime.getDayOfWeek());
        System.out.println(localDateTime.getDayOfYear());
        System.out.println(localDateTime.getYear());
        System.out.println(localDateTime.getMonth());

        // 体现不可变性
        // withXxx()：修改日期时间，并且返回新的时间，解决了可变性
        LocalDate localDate1 = localDate.withYear(2000);
        System.out.println(localDate);
        System.out.println(localDate1);

        // plusXxx()：加上时间
        LocalDate localDate2 = localDate.plusYears(1);
        System.out.println(localDate);
        System.out.println(localDate2);

        // minus：减去时间
        LocalDate localDate3 = localDate.minusYears(1);
        System.out.println(localDate);
        System.out.println(localDate3);
    }

    /**
     * Instance 的使用
     * 类似与 java.util.Date 类
     */
    @Test
    public void test2() {

        // 这样得到的时间与我们所在区域的时间差了 8 个小时，也就是本初子午线对应的标准时间
        Instant instant = Instant.now();
        System.out.println(instant); // 2022-10-17T07:30:53.500Z

        // 添加时间偏移量
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);// 2022-10-17T15:32:59.169+08:00

        // toEpochMilli()：获取自 1970 年 1 月 1 日 0 时 0 分 0 秒（UTC）开始的毫秒数 ---> Date 类的 getTime()
        // 2022-10-17T15:35:25.930+08:00
        long milli = instant.toEpochMilli();
        System.out.println(milli);

        // ofEpochMilli()：根据给定毫秒数，获取 Instant 实例 --> Date(Long millis)
        Instant instant1 = Instant.ofEpochMilli(1665992125930L);
        OffsetDateTime offsetDateTime1 = instant1.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime1); // 2022-10-17T15:35:25.930+08:00
    }

    /**
     * DateTimeFormatter；格式化或解析日期、时间
     * 类似于 SimpleDateFormat
     */
    @Test
    public void test3() {
        // 方式一:预定义的标准格式。如: ISO_LOCAL_DATE_TIME;ISO_LOCAL_DATE;ISO_LOCAL_TIME
        DateTimeFormatter formatter1 = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        DateTimeFormatter formatter2 = DateTimeFormatter.ISO_LOCAL_DATE;
        DateTimeFormatter formatter3 = DateTimeFormatter.ISO_LOCAL_TIME;
        // 格式化：日期 -> 字符串
        String str1 = formatter1.format(LocalDateTime.now()); // 2022-10-17T16:06:52.253
        String str2 = formatter2.format(LocalDate.now()); // 2022-10-17
        String str3 = formatter3.format(LocalTime.now()); // 16:06:52.257
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
        // 解析：字符串 -> 日期
        System.out.println(formatter1.parse("2022-10-17T16:06:52.253"));
        System.out.println(formatter2.parse("2022-10-17"));
        System.out.println(formatter3.parse("16:06:52.257"));

        // 方式二：
        // 本地化相关的格式。如: ofLocalizedDateTime()
        // FormatStyle.LONG /FormatStyLe.MEDIUM / FormatStyLe.SHORT :适用于 LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();
        // 格式化：日期 -> 字符串
        DateTimeFormatter formatter4 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        DateTimeFormatter formatter5 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        DateTimeFormatter formatter6 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        System.out.println(formatter4.format(localDateTime)); // 2022年10月17日 下午04时15分10秒
        System.out.println(formatter5.format(localDateTime)); // 2022-10-17 16:15:10
        System.out.println(formatter6.format(localDateTime)); // 22-10-17 下午4:15
        // 解析：字符串 -> 日期
        System.out.println(formatter4.parse("2022年10月17日 下午04时15分10秒"));
        System.out.println(formatter5.parse("2022-10-17 16:15:10"));
        System.out.println(formatter6.parse("22-10-17 下午4:15"));
        // 本地化相关的格式。如: ofLocalizedDate()
        // FormatStyle.FULL / FormatStyLe.LONG / FormatStyLe.MEDIUM/FormatStyLe.SHORT︰适用于LocalLDate
        DateTimeFormatter formatter7 = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        DateTimeFormatter formatter8 = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        DateTimeFormatter formatter9 = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        DateTimeFormatter formatter10 = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        LocalDate localDate = LocalDate.now();
        System.out.println(formatter7.format(localDate)); // 2022年10月17日 星期一
        System.out.println(formatter8.format(localDate)); // 2022年10月17日
        System.out.println(formatter9.format(localDate)); // 2022-10-17
        System.out.println(formatter10.format(localDate)); // 22-10-17

        // 重点 方式三：自定义的格式。如：ofPattern("yyyy-MM-dd hh:mm:ss")
        DateTimeFormatter formatter11 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        // 格式化
        System.out.println(formatter11.format(LocalDateTime.now())); // 2022-10-17 04:24:57
        // 解析
        System.out.println(formatter11.parse("2022-10-17 04:24:57"));
    }

}
