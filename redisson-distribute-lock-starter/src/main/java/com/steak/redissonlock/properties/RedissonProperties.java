package com.steak.redissonlock.properties;
import com.steak.redissonlock.enums.RedisPatternEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/10/14:34
 * @Description:
 */
@ConfigurationProperties(prefix = "u2-lock")
public class RedissonProperties {

    /**
     * 模式
     */
    private String pattern;

    private String password;
    private Boolean enabled;
    private String address;
    private Integer database;
    private Integer connectionPoolSize;
    private Integer idleSize;
    private Integer idleConnectionTimeout;
    private Integer connectionTimeout;
    private Integer timeout;
    private String masterName;
    private String masterAddress;
    private String[] slaveAddress;
    private String[] nodeAddress;
    private String[] sentinelAddress;
    private Boolean keepAlive;
    /**
     * 红锁
     */
    private String[] redLock;

    public RedissonProperties() {
        this.pattern = RedisPatternEnum.SINGLE.getPattern();
        this.enabled = Boolean.FALSE;
        this.address = "redis://127.0.0.1:6379";
        this.database = 0;
        this.idleSize = 10;
        this.idleConnectionTimeout = 60000;
        this.connectionTimeout = 30000;
        this.timeout = 10000;
        this.connectionPoolSize = 10;
        this.keepAlive = Boolean.TRUE;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDatabase() {
        return database;
    }

    public void setDatabase(Integer database) {
        this.database = database;
    }

    public Integer getConnectionPoolSize() {
        return connectionPoolSize;
    }

    public void setConnectionPoolSize(Integer connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
    }

    public Integer getIdleSize() {
        return idleSize;
    }

    public void setIdleSize(Integer idleSize) {
        this.idleSize = idleSize;
    }

    public Integer getIdleConnectionTimeout() {
        return idleConnectionTimeout;
    }

    public void setIdleConnectionTimeout(Integer idleConnectionTimeout) {
        this.idleConnectionTimeout = idleConnectionTimeout;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getMasterAddress() {
        return masterAddress;
    }

    public void setMasterAddress(String masterAddress) {
        this.masterAddress = masterAddress;
    }

    public String[] getSlaveAddress() {
        return slaveAddress;
    }

    public void setSlaveAddress(String[] slaveAddress) {
        this.slaveAddress = slaveAddress;
    }

    public String[] getNodeAddress() {
        return nodeAddress;
    }

    public void setNodeAddress(String[] nodeAddress) {
        this.nodeAddress = nodeAddress;
    }

    public String[] getSentinelAddress() {
        return sentinelAddress;
    }

    public void setSentinelAddress(String[] sentinelAddress) {
        this.sentinelAddress = sentinelAddress;
    }

    public Boolean getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public String[] getRedLock() {
        return redLock;
    }

    public void setRedLock(String[] redLock) {
        this.redLock = redLock;
    }
}
