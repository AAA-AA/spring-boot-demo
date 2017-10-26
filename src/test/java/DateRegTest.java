import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : hongqiangren.
 * Date: 2017/10/19 15:46
 * Email: renhongqiang1397@gmail.com
 */
public class DateRegTest {

    public static void main(String[] args) {
       String reg = "\\d{4}-(^[0][1-9]$)|(^[1][0-2]$)-(^[0][1-9]$|(^[1-2][0-9]$)|(^3[0-1]$)) (^[0-1]\\d$)|(2[0-4]):(^[0-5]\\d$)|(^60$):(^[0-5]\\d$)|(60)";
       String input = "2014-10-38 00:62:59";
        Matcher matcher = Pattern.compile(reg).matcher(input);
        System.out.println(matcher.find());

    }
    static class ResponseDto {
        private String code;
        private String msg;
        private String seq;
        private String[] billno;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getSeq() {
            return seq;
        }

        public void setSeq(String seq) {
            this.seq = seq;
        }

        public String[] getBillno() {
            return billno;
        }

        public void setBillno(String[] billno) {
            this.billno = billno;
        }

        @Override
        public String toString() {
            return "{code:"+this.code+",msg:"+this.msg+",seq:"+this.seq+",billno:"+this.billno+"}";
        }
    }
}
