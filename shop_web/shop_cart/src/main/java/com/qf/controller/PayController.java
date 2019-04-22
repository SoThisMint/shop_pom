package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.qf.entity.Orders;
import com.qf.entity.User;
import com.qf.service.IOrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/21 21:11
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Reference
    private IOrdersService ordersService;

    @RequestMapping("/alipay/{oid}/{uid}")
    @ResponseBody
    public void pay(@PathVariable String oid, @PathVariable int uid, Model model, HttpServletRequest httpRequest,
                    HttpServletResponse httpResponse) throws Exception {
        //List<Orders> ordersList = ordersService.getOrdersList(uid);
        Orders orders = ordersService.getOrdersByOid(oid);

        //调用支付宝进行支付,后续的支付请求，退款请求都是基于这个客户端对象的，所以这个对象应该在项目中唯一保存
        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipaydev.com/gateway.do",
                "2016092900624683",
                "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCKvhLOmvD962OTQfhStm2VKyvPLFQohpjwZhfNoy5JDQueRq75hGRPSElaqNluO3TxfCgEr8WI5M0xHVPGRU4tgsKS73js1L26X7VS/D01hhZ4r1fqKsU2vg71YLk/x4QPWA8LNla5Q8dBwdLiWz1FJXh6qE4kLqeqzEEWLfqaVfzAnSy5B3sFESnUAAVnpxO1dRbZvPzn4/qg6FoyBE7OwsgQo/VRI5rEMOmvlxe2geFrJTXaNpqXAW03TjbQUzByy+Jn++xhDChq+o5ssL8CZnXp7FIl4+51T7RihmxaFQapZwR1rDTE17TZGNJ7MKJEtj75/5HXSwfvHchNZoP1AgMBAAECggEAIVfIoUpvPDbDuXITQZ2B/GVho+pdV1fxjWSbt9nNl9TAxArsph6BFOSkbFtD1dAILLjPzUCI0ntaLMGFjpK9VQalyDYw84m1BwNVSlpAQ4qz/9Dz6dzZhGxsn9/c5U0HmcBp7SHj+aYmTtFL4wZWVTXKHIAyN1PXC58GzVPDdC92OPHBW7upsPwcVDuSmUX5M5WDTe0FAzDGt8sYqcodu9pDGQruO6wpnruiXb6VVmZh65+Tgt0DhKCMarDsZ2rBGza1+HJHAOqLffEyM0nQSvQrUDDpAeQBjR38XFoVOMsZzz6WIxR0/Xn7FZgor5Hv/DcRD4ICZxXUQ8R0/LrNAQKBgQDeXoESIG3qRgLHXqtBh585NYlgh73Ngzz8DHE9lgogQLFxOMQEQGfBCaayloGVFbp/2V/ivm3jna0XLriksnqf9PgvYqEnLa5bW6xyfjdFdqF/mygeacxk5zidEgbvVTi7fafttTGw5o2zuSPkHL84JBnjtwvKKgXHb+sQCd04FQKBgQCfuci3Uz5sKhNWQyP1C76a/IMWU0q34/1Fog5+aUjkLWOiReGULi33APbWwSrJDoe5ssZIDHbags3uaRxtq26D2LCJ+iKjVyo8nn3LwlQY89HdCwx68lTKlc1WzAVc6156ZjjGsLPbpA00l4AwyXtFjbC33ge9xtFnev4suHA0YQKBgHLtxSfpOuXqaWVBuKGVxuV6lhYeEis1uT9L7vs1oOYqiYtLG4RXBUjwBAgpYa/8GvgZEUXPxlvuEpf4vdXhqR8jLWLQRxpIseTl7KsAMPDl73JUbtfJNqqwFsKXnp30He5Jc5GYJKaZgoNxHOu5L9uETR00bhN+xJ3fak5PytZBAoGACSgQXpoSEQdUdnSA4jJbKe1h4mzB8srKISzKIesNN692b74L0X/YOLnAbM1EE6SWmUmrXkQAqEQpZAp4i7zxJ/PLIw3I4I5FqJX5Zi3svkVz+XUBsSalP7vYuqoVgJ8pmmCzzoggt22vKB2rRqC2RWV7BP9geDumk4UUfyHPpOECgYA/cx+CKmLThbqwoFQW5R5ULvySsKe+HqpCat0cwJygG7NaKqqDOlI7IK/sfgCT1V5Aak8fq3bY/A1mIKv23RZOktBNStVDlqYMnLlSJpaRHyFOl/YUiIrB6UFIzQx3eAmsMb6FcC9lu5fIHw08ZlT9Bwy12B3w6xiDkg7Bkud7iQ==",
                "json",
                "utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu4THt57UUTuX7anjRbbcTQHpfbvAiPCIwUFPMbOHJOq6dAfJgZYuWtjq4uKFL3iPQh4J5mpQDcLbXs1JGuuplVjFw1Ku+xjI3UMHJogsBXwZlu/wCxQK9ij+KIupouskd90SZ1t1kx/iQla7JI6N4Q+UAVPAtRB+OfUQgiA1eK2EKNYcm9Nq2ZbfY1kJNMsSjzwxb9KSTeYk4+XtvUWQ8I79/v56sa6JZ4eIFcWXL8WcgRIMzwrgL8OG2zA7Y6PjPvhVOl/0P6kY9s5M5nt6umyFR1pEUHGTiPaZIpOuxFWOAcHGB8ECaRSA9BawdgECZDe/Z0NKrKz3cR3OqiL2/QIDAQAB",
                "RSA2"); //获得初始化的AlipayClient
        //创建一个生产交易页面的请求对象
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        //设置交易完成的同步请求地址
        alipayRequest.setReturnUrl("http://www.baidu.com");
        //设置交易完成的异步请求地址
        alipayRequest.setNotifyUrl("http://10.36.137.150:8086/pay/payNotify");//在公共参数中设置回跳和通知地址
        //通过交易请求对象设置业务主体信息（订单号，支付金额）
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"" + orders.getOid() + "\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":" + orders.getAllprice().doubleValue() + "," +
                "    \"subject\":\"" + orders.getOid() + "\"," +
                "    \"body\":\"" + orders.getOid() + "\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }" +
                "  }");//填充业务参数
        //通过sdk的api调用，发送业务主体信息给支付宝服务器，并且返回支付页面
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=utf-8");
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();


//        model.addAttribute("ordersList",ordersList);
    }

    @RequestMapping("/payNotify")
    public String payNotify(String out_trade_no, String trade_status, Model model) {
        Orders orders = ordersService.getOrdersByOid(out_trade_no);
        if ("TRADE_SUCCESS".equals(trade_status)) {
            orders.setStatus(1);
            ordersService.updateOrders(orders);
        }

        User user = ordersService.getUserByOid(out_trade_no);
        List<Orders> ordersList = ordersService.getOrdersList(user.getId());
        model.addAttribute("ordersList", ordersList);
        return "orders";
    }
}
