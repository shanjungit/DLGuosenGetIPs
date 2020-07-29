package com.guosen.entry;

/**
 * 实体类,每台主机需要的属性为
 * IP, MAC地址， 主机名称
 */
public class LanIPs {
    private String ip;
    private String mac;
    private String hostname;

    public String getIptype() {
        return iptype;
    }

    public void setIptype(String iptype) {
        this.iptype = iptype;
    }

    private String iptype;//动态或静态
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Override
    public String toString() {
        return "LanIPs{" +
                "ip='" + ip + '\'' +
                ", mac='" + mac + '\'' +
                ", hostname='" + hostname + '\'' +
                ", iptype='" + iptype + '\'' +
                '}';
    }
}
