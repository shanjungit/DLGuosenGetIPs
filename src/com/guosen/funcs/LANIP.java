package com.guosen.funcs;
import com.guosen.entry.LanIPs;

import java.io.*;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;


public class LANIP {
    public static InetAddress[] getAllOnline() {
        // TODO Auto-generated method stub
        Vector v = new Vector(50);
        try {
            Process process = Runtime.getRuntime().exec("arp -a");
            InputStreamReader inputStr = new InputStreamReader(
                    process.getInputStream(), "GBK");
            BufferedReader br = new BufferedReader(inputStr);
            String temp = "";
            br.readLine();
            br.readLine();
            br.readLine();// 此后开始读取IP地址，之前为描述信息，忽略。
            while ((temp = br.readLine()) != null) {
                System.out.println(temp);
                StringTokenizer tokens = new StringTokenizer(temp);
                String x;
                InetAddress add=null;
                try {
                    add = InetAddress.getByName(x = tokens
                            .nextToken());
                    System.out.println(add.hashCode());
                } catch (java.net.UnknownHostException e) {
                    continue;
                }
                v.add(add);
            }

            v.add(InetAddress.getLocalHost());
            process.destroy();
            br.close();
            inputStr.close();
        } catch (Exception e) {
            System.out.println("可能是网络不可用。");
            e.printStackTrace();
        }
        int cap = v.size();
        InetAddress[] addrs = new InetAddress[cap];
        for (int i = 0; i < cap; i++) {
            addrs[i] = (InetAddress) v.elementAt(i);
            System.out.println(addrs[i]);
        }

        return addrs;
    }



    /**
     * 获取局域网内所有的机器情况
     * @return
     */
    public static List<LanIPs> getAllLanMachins() throws IOException {
        // TODO: 2020-7-29 使用 arp -a 获取局域网内所有 ip和 mac地址
        List<LanIPs> iPsList = new ArrayList<>();

        Process process = Runtime.getRuntime().exec("arp -a");
        InputStreamReader inputStr = new InputStreamReader(process.getInputStream(), "GBK");
        BufferedReader br = new BufferedReader(inputStr);
        String inline = "";

        while ((inline = br.readLine()) != null) {
            LanIPs temp_machin = new LanIPs();
            if(inline.contains("接口") || (inline.contains("地址")) || (inline.length() ==0)) {
                continue;
            }
            temp_machin = genMachinInfo(inline);
            iPsList.add(temp_machin);
            temp_machin = null;
        }
        return iPsList;
    }

    public static LanIPs genMachinInfo(String inline) {
        LanIPs temp_machin = new LanIPs();
        System.out.println(inline);
        StringTokenizer tokens = new StringTokenizer(inline," ");
        String[] results = new String[tokens.countTokens()];
        int i = 0;
        while (tokens.hasMoreElements() ) {
            results[i] = tokens.nextToken().toString();
            i++;
        }
        temp_machin.setIp(results[0]); //ip
        temp_machin.setMac(results[1]);//mac
        temp_machin.setIptype(results[2]);//iptype 动态活着静态
        return  temp_machin ;
    }

    public static void getHostName(String ip) throws IOException {
        InetAddress inet2 = InetAddress.getByName(ip);
        // inet1相当于InetAddress.getlocalHost();
        InetAddress inet1 = InetAddress.getLocalHost();
        // 获取属性
        System.out.println("Host Address:"+inet2.getHostAddress());	// 192.168.42.143
        System.out.println("Host Name:"+inet2.getHostName());	// DESKTOP-4JN4PSM
        System.out.println("Address: "+inet2.getAddress());		// [B@7852e922
        System.out.println("Hostname 2: "+inet2.getCanonicalHostName());	// DESKTOP-4JN4PSM

    }

    public static void main(String[] args) {
        try {
            //System.out.println(LANIP.getAllLanMachins().toString());
            //System.out.println("本机IP地址为："+InetAddress.getLocalHost().getHostName());
            List<LanIPs> list = LANIP.getAllLanMachins();
            for (LanIPs i : list) {
                LANIP.getHostName(i.getIp());
                System.out.println("-------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
